package com.example.simplemusicplayer

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder

class MusicPlayerService : Service() {

    var mMediaPlayer : MediaPlayer ?= null
    var mBinder : MusicPlayerBinder = MusicPlayerBinder()

    inner class MusicPlayerBinder : Binder() {
        fun getService() : MusicPlayerService {
            return this@MusicPlayerService
        }
    }

    override fun onCreate() { // 1. 서비스가 실행될 때 딱 한 번만 실행
        super.onCreate()
        startForegroundService() // 2. 포그라운드 서비스 시작
    }

    // 3.
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    // 4. 시작된 상태 & 백그라운드
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    fun startForegroundService() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val mChannel = NotificationChannel("CHANNEL_ID", "CHANNEL_NAME", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(mChannel)
        }

        // 알림 생성
        var notification: Notification = Notification.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.baseline_play_arrow_24)
            .setContentTitle("뮤직 플레이어 앱")
            .setContentText("앱이 실행 중입니다.")
            .build()

        startForeground(1, notification)
    }

    // 5. 서비스 종료
    override fun onDestroy() {
        super.onDestroy()
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            stopForeground(true)
        }
    }

    fun isPlaying() : Boolean{ // 재생 중인지 확인
        return (mMediaPlayer != null && mMediaPlayer?.isPlaying ?: false)
    }

    fun play() {} // 재생
    fun pause() {} // 일시정지
    fun stop() {} // 완전 정지

}