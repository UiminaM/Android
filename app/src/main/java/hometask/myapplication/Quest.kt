package hometask.myapplication
import androidx.annotation.ColorRes

data class Quest(
    val id: Int,
    val title: String,
    val age_limit: String,
    val description: String,
    val url: String,
    val isFav: Boolean = false
)
sealed class QuestItem {
    data class QuestUiModel(
        val id: Int,
        val title: String,
        val age_limit: String,
        val description: String,
        val url: String,
        val isFav: Boolean = false,
        @ColorRes val titleColor: Int
    ) : QuestItem()

    data class Title(
        val title: String
    ): QuestItem()


}