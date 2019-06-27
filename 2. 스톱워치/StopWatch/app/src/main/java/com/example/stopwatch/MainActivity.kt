package com.example.stopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var time = 0; // 시간 계산 변수
    private var timerTask: Timer? = null; // Null 허용 Timer 타입 변수
    private var isRunning = false; // 현재 실행 중인지

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fab.setOnClickListener{
            isRunning = !isRunning // 실행 중인 것을 일시정지하고

            if(isRunning) // 타이머의 상태에 따라 시작 / 일시중지 결정
            {
                start()
            } else {
                pause()
            }
        }
    }

    private fun start() {
        fab.setImageResource(R.drawable.ic_pause_black_24dp)

        timerTask = timer(period = 10) {
            time++
            val sec = time / 100
            val milli = time % 100
            runOnUiThread {
                if (isRunning) {
                    secTextView.text = "$sec"
                    milliTextView.text = "$milli"
                }
            }
        }
    }

    private fun pause() // 일시정지
    {
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        timerTask?.cancel() // 실행중인 타이머가 있다면 취소시킨다
    }

}
