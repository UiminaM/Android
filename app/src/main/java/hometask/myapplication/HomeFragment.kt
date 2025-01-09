package hometask.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hometask.myapplication.databinding.FragmentHomeBinding
import com.bumptech.glide.Glide
import hometask.myapplication.QuestsRepository.QST
import androidx.recyclerview.widget.LinearLayoutManager

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var binding: FragmentHomeBinding? = null
    private var adapter: QuestsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = QuestsListAdapter(Glide.with(this)) {
            onClickQ(it)
        }

        binding?.rvQuests?.adapter = adapter

        binding?.swipe?.setOnRefreshListener {
            refresh()
        }

        adapter?.submitList(QST) {
            binding?.rvQuests?.scrollToPosition(0)
        }

        binding?.rvQuests?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun refresh() {
        binding?.swipe?.isRefreshing = false
    }

    fun onClickQ(quest: QuestItem.QuestUiModel) {
        val index = QuestsRepository.quests.indexOfFirst { it.id == quest.id }
        if (index != -1) {
            val originalQuest = QuestsRepository.quests[index]
            val newQuest = originalQuest.copy(
                isFav = !originalQuest.isFav
            )
            QuestsRepository.quests.removeAt(index)
            QuestsRepository.quests.add(index, newQuest)
            adapter?.submitList(QST)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}