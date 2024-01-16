package com.example.helloworld

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class NoXMLActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ConstraintLayout 생성
        val layout = ConstraintLayout(this).apply {
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT
            )
            id = ConstraintSet.PARENT_ID
        }

        // 버튼 생성
        val button = Button(this).apply {
            id = View.generateViewId()
            text = "클릭하세요"
            layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )
            setOnClickListener {
                Toast.makeText(this@NoXMLActivity, "안녕하세요", Toast.LENGTH_SHORT).show()
                //Toast.makeText(applicationContext, "안녕하세요", Toast.LENGTH_SHORT).show()
            }
        }

        // 레이아웃에 버튼 추가
        layout.addView(button)

        // 버튼을 레이아웃 중앙에 배치
        ConstraintSet().apply {
            clone(layout)
            connect(button.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0)
            connect(button.id, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0)
            connect(button.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 0)
            connect(button.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 0)
            applyTo(layout)
        }

        // 생성된 레이아웃을 액티비티의 콘텐츠 뷰로 설정
        setContentView(layout)
    }
}