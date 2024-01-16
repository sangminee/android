package com.example.todolist

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Telephony.Mms.Intents
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.db.AppDatabase
import com.example.todolist.db.ToDoDao
import com.example.todolist.db.ToDoEntity

class MainActivity : AppCompatActivity() , OnItemLongClickListener{

    private lateinit var binding: ActivityMainBinding

    private lateinit var db : AppDatabase
    private lateinit var todoDao : ToDoDao
    private lateinit var todoList : ArrayList<ToDoEntity>
    private lateinit var adapter: TodoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener{
            val intent = Intent(this, AddTodoActivity::class.java)
            startActivity(intent)
        }

        // db 인스턴스를 가져오고 DB 작업을 할 수 있는 DAO를 가져옴
        db = AppDatabase.getInstance(this)!!
        todoDao = db.getTodoDao()

        getAllTodoList() // 할 일 리스트 가져오기
    }

    private fun getAllTodoList() {
        Thread{
            todoList = ArrayList(todoDao.getAll())
            setRecyclerView()
        }.start()
    }

    private fun setRecyclerView() {
        // 리사이클러뷰 설정
        runOnUiThread{
            adapter = TodoRecyclerViewAdapter(todoList, this) // 어댑터 객체 할당
            binding.recyclerView.adapter = adapter
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
        }
    }

    override fun onRestart() {
        super.onRestart()
        getAllTodoList()
    }

    override fun onLongClick(position: Int) {
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("할 일 삭제")
        builder.setMessage("정말 삭제하시겠습니까?")
        builder.setNegativeButton("취소", null)
        builder.setPositiveButton("네",
            object : DialogInterface.OnClickListener{
                override fun onClick(p0 : DialogInterface?, p1: Int) {
                    deleteTodo(position)
                }
            })

        builder.setNeutralButton("수정",
            object : DialogInterface.OnClickListener{
                override fun onClick(p0 : DialogInterface?, p1: Int) {
                    updateTodo(position)
                }
            })

        builder.show()
    }

    private fun updateTodo(position: Int){

    }

    private fun deleteTodo(position: Int){
        Thread {
            todoDao.deleteTodo(todoList[position]) // DB에서 삭제
            todoList.removeAt(position) // 리스트에서 삭제
            runOnUiThread{
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }

}