package hometask.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hometask.myapplication.QuestsRepository.QST
import hometask.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var adapter: QuestsListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        adapter = QuestsListAdapter(Glide.with(this)) {
            onClickQ(it)
        }

        binding.let {
            it?.rvQuests?.adapter = adapter

            it?.swipe?.setOnRefreshListener {
                refresh()
            }

            adapter?.submitList(QST) {
                it?.rvQuests?.scrollToPosition(0)
            }

        }

        binding?.rvQuests?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun refresh() {
        binding?.swipe?.isRefreshing = false
    }

    fun onClickQ(quest: QuestItem.QuestUiModel) {
        val index = QuestsRepository.quests.indexOfFirst { it.id == quest.id }
        Log.d("QuestItem", "Binding quest: ${quest.title}, isFav: ${quest.isFav}")
        if (index != -1) {
            val originalQuest = QuestsRepository.quests[index]
            val newQuest = originalQuest.copy(
                isFav = !originalQuest.isFav
            )
            Log.d("QuestItem", "Binding quest: ${newQuest.title}, isFav: ${newQuest.isFav}")
            QuestsRepository.quests.removeAt(index)
            QuestsRepository.quests.add(index, newQuest)
            adapter?.submitList(QST)
        }
    }
}