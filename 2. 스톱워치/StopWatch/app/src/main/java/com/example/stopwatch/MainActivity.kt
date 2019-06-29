package com.example.stopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var time = 0; // 시간 계산 변수
    private var timerTask: Timer? = null; // Null 허용 Timer 타입 변수
    private var isRunning = false; // 현재 실행 중인지
    private var lap = 1 // 몇 번째 랩인지

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

        lapButton.setOnClickListener {
            recordLapTime()
        }

        resetFab.setOnClickListener {
            reset()
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

    private fun recordLapTime() // 랩 타임 기록
    {
        val lapTime = this.time // 현재 시간을 지역변수에 저장
        val textView = TextView(this) // 동적으로 TextView 생성
        textView.text = "$lap LAB : ${lapTime / 100}.${lapTime % 100}"

        // 맨 위에 랩타임 추가
        lapLayout.addView(textView, 0)
        lap++
    }

    private fun reset() // 타이머 초기화
    {
        timerTask?.cancel() // 실행 중 타이머가 있으면 취소

        // 모든 변수 초기화
        time = 0
        isRunning = false
        fab.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        secTextView.text = "0"
        milliTextView.text="00"

        // 랩 타임 초기화
        lapLayout.removeAllViews()
        lap = 1
    }

}
