package hometask.myapplication


import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hometask.myapplication.databinding.ActivityMainBinding
import hometask.myapplication.MainFragment.Companion.newInstance
import hometask.myapplication.MainFragment.Companion.TAG

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.rv_container, newInstance("WELCOME"), TAG)
            .commit()
    }

    fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.rv_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}