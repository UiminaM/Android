package hometask.myapplication

object QuestsRepository {

    val quests = arrayListOf(
            Quest(
                id = 0,
                title = "Алиса в стране чудес",
                age_limit = "10+",
                description = "Страна чудес в беде, и только случайные странники могут вернуть Белую королеву.",
                url = "https://www.kvestinfo.ru/upload/iblock/926/926d6ef01bc6ad355c09e5f232126ee6_750x500.jpg"
            ),
            Quest(
                id = 1,
                title = "Шерлок Холмс",
                age_limit = "12+",
                description = "Расследуйте преступления вместе с легендарным детективом.",
                url = "https://www.kvestinfo.ru/upload/iblock/e99/e99acf13bd11371f10141494aca29200_750x500.jpg"
            ),
            Quest(
                id = 3,
                title = "Коллекционер",
                age_limit = "13+",
                description = "Разгадайте тайны коллекционера и его загадочные экспонаты.",
                url = "https://www.kvestinfo.ru/upload/iblock/d43/1635770475-performans-kollektsioner-v-ekaterinburge_750x500.jpg"
            ),
            Quest(
                id = 4,
                title = "Полтергейст",
                age_limit = "18+",
                description = "Погрузитесь в мир призраков и разгадок таинственных явлений.",
                url = "https://www.kvestinfo.ru/upload/iblock/75d/1636016786-kvest-poltergejst-v-ekaterinburge_750x500.png"
            ),
            Quest(
                id = 5,
                title = "Чумной доктор",
                age_limit = "13+",
                description = "Сразитесь с тайной чумного доктора и спасите город.",
                url = "https://www.kvestinfo.ru/upload/iblock/cf0/1718272394-performans-cumnoj-doktor-v-ekaterinburge_750x500.jpg"
            ),
            Quest(
                id = 6,
                title = "Дом страшных сказок",
                age_limit = "10+",
                description = "Погружайтесь в мир страшных историй и загадок.",
                url = "https://www.kvestinfo.ru/upload/iblock/3ac/3aca057b7ac1017e18d62da65abb8ce6_750x500.jpg"
            ),
            Quest(
                id = 7,
                title = "Алиса в стране чудес",
                age_limit = "10+",
                description = "Страна чудес в беде, и только случайные странники могут вернуть Белую королеву.",
                url = "https://www.kvestinfo.ru/upload/iblock/926/926d6ef01bc6ad355c09e5f232126ee6_750x500.jpg"
            ),
            Quest(
                id = 8,
                title = "Шерлок Холмс",
                age_limit = "12+",
                description = "Расследуйте преступления вместе с легендарным детективом.",
                url = "https://www.kvestinfo.ru/upload/iblock/e99/e99acf13bd11371f10141494aca29200_750x500.jpg"
            ),
            Quest(
                id = 9,
                title = "Коллекционер",
                age_limit = "13+",
                description = "Разгадайте тайны коллекционера и его загадочные экспонаты.",
                url = "https://www.kvestinfo.ru/upload/iblock/d43/1635770475-performans-kollektsioner-v-ekaterinburge_750x500.jpg"
            ),
            Quest(
                id = 10,
                title = "Полтергейст",
                age_limit = "18+",
                description = "Погрузитесь в мир призраков и разгадок таинственных явлений.",
                url = "https://www.kvestinfo.ru/upload/iblock/75d/1636016786-kvest-poltergejst-v-ekaterinburge_750x500.png"
            ),
            Quest(
                id = 11,
                title = "Чумной доктор",
                age_limit = "13+",
                description = "Сразитесь с тайной чумного доктора и спасите город.",
                url = "https://www.kvestinfo.ru/upload/iblock/cf0/1718272394-performans-cumnoj-doktor-v-ekaterinburge_750x500.jpg"
            ),
            Quest(
                id = 12,
                title = "Дом страшных сказок",
                age_limit = "10+",
                description = "Погружайтесь в мир страшных историй и загадок.",
                url = "https://www.kvestinfo.ru/upload/iblock/3ac/3aca057b7ac1017e18d62da65abb8ce6_750x500.jpg"
            )
        )
    val QST: MutableList<QuestItem.QuestUiModel>
        get() = quests.map {
            val titleColor = if (it.id % 2 != 0) {
                R.color.pink
            }
            else {R.color.orange}

            QuestItem.QuestUiModel(
                id = it.id,
                title = it.title,
                age_limit = it.age_limit,
                description = it.description,
                url = it.url,
                isFav = it.isFav,
                titleColor = titleColor
            )
        }.toMutableList()
}