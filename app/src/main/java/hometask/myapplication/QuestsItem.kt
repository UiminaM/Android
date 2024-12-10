package hometask.myapplication

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import hometask.myapplication.databinding.ItemQuestBinding

class QuestsItem(
    private val binding: ItemQuestBinding,
    private val listener: (Quest) -> Unit,
    private val glide: RequestManager
): RecyclerView.ViewHolder(binding.root) {

    private val roption = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    fun onBind(quest: Quest) {
        with(binding) {
            tvTitle.text = quest.title
            tvAgeLimit.text = quest.age_limit

            root.setOnClickListener {
                listener.invoke(quest)
            }

            glide.load(quest.url)
                .error(R.drawable.error)
                .load(quest.url)
                .placeholder(R.drawable.baseline_change_circle_24)
                .apply(roption)
                .into(ivImage)
        }
    }
}