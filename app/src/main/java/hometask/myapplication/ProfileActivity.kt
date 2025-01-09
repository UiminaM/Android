package hometask.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hometask.myapplication.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private var binding: ActivityProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val your_name = intent.extras?.getString(NAME) ?: "ERROR"

        binding?.skreenpofile?.text = "${binding?.skreenpofile?.text} : \n $your_name"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {
        private const val NAME = "NAME"

        fun bundle(your_name: String): Bundle = Bundle().apply {
            putString(NAME, your_name)
        }
    }
}