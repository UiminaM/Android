package hometask.myapplication

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder


class MusicPlayer : Service() {

    private var mediaPlayer: MediaPlayer? = null

    private val trackNames = listOf(
        "Adele - Hello",
        "OneRepublic - Counting star",
        "Maneskin - I wanna be your slave",
        "Feel it still - Feel it still",
        "Adele - Rolling in the deep",
        "Maneskin - Beggin"
    )
    private val tracks = listOf(
        R.raw.adele_hello,
        R.raw.counting_star,
        R.raw.maneskin_wanna,
        R.raw.feel_it_still,
        R.raw.adele_rolling,
        R.raw.maneskin_beggin
    )

    private var trackIndex = 0

    inner class MusicBinder : Binder() {
        fun service(): MusicPlayer = this@MusicPlayer
    }

    private val binder = MusicBinder()

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        initializeMediaPlayer()
    }

    private fun initializeMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, tracks[trackIndex])
        mediaPlayer?.setOnCompletionListener {
            nextTrack()
        }
    }

    fun playMusic() {
        if (mediaPlayer?.isPlaying == false) {
            mediaPlayer?.start()
        }
    }

    fun pauseMusic() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }

    fun nextTrack() {
        trackIndex = (trackIndex + 1) % tracks.size
        changeTrack()
    }

    fun previousTrack() {
        trackIndex = if (trackIndex - 1 < 0) {
            tracks.size - 1
        } else {
            trackIndex - 1
        }
        changeTrack()
    }

    private fun changeTrack() {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, tracks[trackIndex])
        mediaPlayer?.start()
        mediaPlayer?.setOnCompletionListener {
            nextTrack()
        }
    }

    fun getCurrentTrackName(): String {
        return trackNames[trackIndex]
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }
}