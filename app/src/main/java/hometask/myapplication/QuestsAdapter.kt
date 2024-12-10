package hometask.myapplication

import android.animation.Animator
import android.animation.AnimatorInflater
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import hometask.myapplication.QuestsRepository.quests
import hometask.myapplication.databinding.ItemQuestBinding

class QuestsAdapter(
    private val list: MutableList<Quest>,
    private val glide: RequestManager,
    private val listener: (Quest) -> Unit
) : RecyclerView.Adapter<QuestsItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuestsItem = QuestsItem(
        binding = ItemQuestBinding.inflate(LayoutInflater.from(parent.context), parent, false),
        glide = glide,
        listener = listener
    )

    override fun onBindViewHolder(holder: QuestsItem, position: Int) {
        if (position == 2) {
            val animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fade_in)
            holder.itemView.startAnimation(animation)
        }
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int = list.size

    fun addQuest(quest: Quest) {
        list.add(0, quest)
        notifyItemInserted(0)
    }
}