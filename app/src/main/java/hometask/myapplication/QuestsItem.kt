package hometask.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import hometask.myapplication.databinding.ItemQuestBinding
import hometask.myapplication.QuestsListAdapter.Companion.TITLE
import hometask.myapplication.QuestsListAdapter.Companion.AGE_LIMIT
import hometask.myapplication.QuestsListAdapter.Companion.FAV

class QuestsItem(
    private val binding: ItemQuestBinding,
    private val listener: (QuestItem.QuestUiModel) -> Unit,
    private val glide: RequestManager
): RecyclerView.ViewHolder(binding.root) {

    private val roption = RequestOptions
        .diskCacheStrategyOf(DiskCacheStrategy.ALL)
        .priority(Priority.HIGH)

    fun onBind(quest: QuestItem.QuestUiModel) {
        Log.d("QuestItem", "Binding quest: ${quest.title}, isFav: ${quest.isFav}")
        with(binding) {
            tvTitle.text = quest.title
            tvAgeLimit.text = quest.age_limit

            root.setOnClickListener {
                listener.invoke(quest)
            }
            isFav.setFav(quest.isFav)

            glide.load(quest.url)
                .error(R.drawable.error)
                .placeholder(R.drawable.baseline_change_circle_24)
                .apply(roption)
                .into(ivImage)
        }
    }
    private fun ImageView.setFav(isFav: Boolean) {
        setImageResource(
            if (isFav) {
                R.drawable.baseline_star_24
            }
            else {
                R.drawable.baseline_star_outline_24
            }
        )
    }

    fun update(bundle: Bundle){
        if (bundle.containsKey(TITLE)) {
            bundle.getString(TITLE).also {
                binding.tvTitle.text = it
            }
        }
        if (bundle.containsKey(AGE_LIMIT)) {
            bundle.getString(AGE_LIMIT).also {
                binding.tvAgeLimit.text = it
            }
        }

        if (bundle.containsKey(FAV)) {
            bundle.getBoolean(FAV).also {
                binding.isFav.setFav(it)
            }
        }
    }
}