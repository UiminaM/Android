package hometask.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import hometask.myapplication.databinding.ActivityThirdBinding

class ThirdActivity: AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleIncomingIntent(intent)

        binding.let {
            with(it) {
                back.setOnClickListener {
                    val intent = Intent(this@ThirdActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun handleIncomingIntent(intent: Intent?) {
        if (intent?.action == Intent.ACTION_SEND) {
            intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)?.let { imageUri ->
                binding.task3.setImageURI(imageUri)
            }
        }
    }
}