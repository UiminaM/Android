package hometask.myapplication

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import hometask.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        val timePick: TimePicker = binding.AlarmTime
        val btnSet: Button = binding.AlarmButton
        val alarmStatus: TextView = binding.AlarmStatus

        btnSet.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, timePick.hour)
            calendar.set(Calendar.MINUTE, timePick.minute)
            calendar.set(Calendar.SECOND, 0)

            if (calendar.timeInMillis <= System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_YEAR, 1)
            }

            val sharedPreferences = getSharedPreferences("AlarmPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putLong("alarmTime", calendar.timeInMillis)
            editor.apply()

            alarm(calendar.timeInMillis)
            alarmStatus.text = "Установлен будильник на ${timePick.hour}:${timePick.minute}"
        }
    }
    @SuppressLint("ScheduleExactAlarm")
    fun alarm(time: Long) {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent)
    }
}