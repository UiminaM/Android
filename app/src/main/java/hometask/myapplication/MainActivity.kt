package hometask.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hometask.myapplication.QuestsRepository.quests
import hometask.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var adapter: QuestsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        adapter = QuestsAdapter(quests, Glide.with(this)) { quest ->
            binding?.root?.showShackBar("${quest.description}")
        }

        binding?.rvQuests?.adapter = adapter
        binding?.swipe?.setOnRefreshListener {
            refresh()
        }

        binding?.rvQuests?.apply {
            addItemDecoration(itemDecorator(this@MainActivity, 15f))
            addItemDecoration(DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL))
            layoutManager = LinearLayoutManager(this@MainActivity)
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun refresh() {
        val newQuest = Quest(3, "Новый квест", "10+", "Описание нового квеста", "url3")
        adapter.addQuest(newQuest)
        binding?.rvQuests?.scrollToPosition(0)
        binding?.swipe?.isRefreshing = false
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null // Clean up binding to avoid memory leaks
    }
}