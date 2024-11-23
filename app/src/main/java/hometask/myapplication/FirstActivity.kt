package hometask.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hometask.myapplication.databinding.ActivityFirstBinding

class FirstActivity: AppCompatActivity() {

    private var binding: ActivityFirstBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        binding = ActivityFirstBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.let {
            with(it) {
                back.setOnClickListener {
                    val intent = Intent(this@FirstActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                share.setOnClickListener {
                    shareImage()
                }
            }
        }
    }
    private fun shareImage() {
        val imageUri = Uri.parse("android.resource://hometask.myapplication/drawable/task1")
        val intent = Intent()
        intent.setAction(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_STREAM, imageUri)
        intent.setType("image/*")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}