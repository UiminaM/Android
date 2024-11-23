package hometask.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hometask.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding?.let {
            with(it) {
                button1.setOnClickListener{
                    val intent = Intent(this@MainActivity, FirstActivity::class.java)
                    startActivity(intent)
                }
                button2.setOnClickListener{
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    startActivity(intent)
                }
                button3.setOnClickListener{
                    val intent = Intent(this@MainActivity, ThirdActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}