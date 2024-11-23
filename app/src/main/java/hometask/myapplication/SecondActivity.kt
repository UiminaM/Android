package hometask.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hometask.myapplication.databinding.ActivitySecondBinding

class SecondActivity: AppCompatActivity() {

    private var binding: ActivitySecondBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = ActivitySecondBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.let {
            with(it) {
                back.setOnClickListener {
                    val intent = Intent(this@SecondActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                share.setOnClickListener {
                    shareImage()
                }
            }
        }
    }
    private fun shareImage() {
        val imageUri = Uri.parse("android.resource://hometask.myapplication/drawable/task2")
        val intent = Intent()
        intent.setAction(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, imageUri)
        intent.setType("image/*")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}