package com.example.tiltsensor

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager

class MainActivity : AppCompatActivity(), SensorEventListener {

    private val sensorManager by lazy {
        // 상수의 늦은 초기화에 사용되는 부분임(lazy)
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
        //시스템의 서비스를 얻어서 센서매니저로 형변환.
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) { //센서 정밀도가 변경되면 호출됨.

    }

    override fun onSensorChanged(event: SensorEvent?) { // 센서값이 변경될 떄
        event?.let {
            // null이 아니라면 아래 {} 실행

            // ${event.values[0]} : x축 값 : 위로 기울이면 -10~0, 아래로 기울이면 0~10
            // ${event.values[1]} : y축 값 : 왼쪽으로 기울이면 -10~0, 오른쪽으로 기울이면 0~10
            // ${event.values[2]} : z축 값 : 미사용

            Log.d(
                "MainActivity",
                "onSensorChanged: x :" + "${event.values[0]}, y: ${event.values[1]}, z : ${event.values[2]}"
            )

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() { // 센서 등록
        super.onResume()
        sensorManager.registerListener(
            this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), // 기울기 센서
            SensorManager.SENSOR_DELAY_NORMAL
        )
        //가속도 센서를 보통의 빈도로 현재 액티비티에서 사용함.
    }

    override fun onPause() { // 센서 해제
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}


