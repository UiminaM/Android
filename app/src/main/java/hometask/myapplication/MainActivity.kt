package hometask.myapplication

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import hometask.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private var musicPlayer: MusicPlayer? = null
    private var servBound = false

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val binder = p1 as MusicPlayer.MusicBinder
            musicPlayer = binder.service()
            servBound = true

            binding?.trackTitle?.text = musicPlayer?.getCurrentTrackName()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            servBound = false
            musicPlayer = null
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val intent = Intent(this, MusicPlayer::class.java)
        startService(intent)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

        binding?.btnPlay?.setOnClickListener {
            if (servBound) {
                musicPlayer?.playMusic()
                binding?.trackTitle?.text = musicPlayer?.getCurrentTrackName()
            }
        }

        binding?.btnPause?.setOnClickListener {
            if (servBound) {
                musicPlayer?.pauseMusic()
                binding?.trackTitle?.text = musicPlayer?.getCurrentTrackName()
            }
        }

        binding?.btnNext?.setOnClickListener {
            if (servBound) {
                musicPlayer?.nextTrack()
                binding?.trackTitle?.text = musicPlayer?.getCurrentTrackName()
            }
        }

        binding?.btnPrevious?.setOnClickListener {
            if (servBound) {
                musicPlayer?.previousTrack()
                binding?.trackTitle?.text = musicPlayer?.getCurrentTrackName()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (servBound) {
            unbindService(serviceConnection)
            servBound = false
        }
        binding = null
    }
}