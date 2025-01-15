package hometask.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hometask.myapplication.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private var binding: ActivitySecondBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding?.root)
    }
}