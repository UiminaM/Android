package hometask.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import hometask.myapplication.databinding.ItemQuestBinding
import androidx.recyclerview.widget.DiffUtil

class QuestsListAdapter(
    private val glide: RequestManager,
    private val listener: (QuestItem.QuestUiModel) -> Unit
) : ListAdapter<QuestItem.QuestUiModel, QuestsItem>(object : DiffUtil.ItemCallback< QuestItem.QuestUiModel>() {
    override fun areItemsTheSame(
        oldItem: QuestItem.QuestUiModel,
        newItem: QuestItem.QuestUiModel
    ): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(
        oldItem: QuestItem.QuestUiModel,
        newItem: QuestItem.QuestUiModel
    ): Boolean = oldItem == newItem

    override fun getChangePayload(
        oldItem: QuestItem.QuestUiModel,
        newItem: QuestItem.QuestUiModel): Any? {
        val bundle = Bundle()

        if (oldItem.title != newItem.title) bundle.putString(TITLE, newItem.title)
        if (oldItem.age_limit != newItem.age_limit) bundle.putString(AGE_LIMIT, newItem.age_limit)
        if (oldItem.isFav != newItem.isFav) bundle.putBoolean(FAV, newItem.isFav)

        return if (!bundle.isEmpty) bundle else null
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestsItem {
        val binding = ItemQuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestsItem(binding, listener, glide)
    }

    override fun onBindViewHolder(holder: QuestsItem, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onBindViewHolder(holder: QuestsItem, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            holder.update(payloads.last() as Bundle)
        }
    }

    companion object {
        const val TITLE = "title"
        const val AGE_LIMIT = "age_limit"
        const val FAV = "fav"
    }
}