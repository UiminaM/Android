package hometask.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BrReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            val sharedPreferences = p0.getSharedPreferences("AlarmPrefs", Context.MODE_PRIVATE)
            val alarmTime = sharedPreferences.getLong("alarmTime", -1)

            if (alarmTime != -1L) {
                val alarmIntent = Intent(p0, AlarmReceiver::class.java)
                val pendingIntent = PendingIntent.getBroadcast(
                    p0,
                    0,
                    alarmIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                val alarmManager = p0.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent)
            }
        }
    }
}