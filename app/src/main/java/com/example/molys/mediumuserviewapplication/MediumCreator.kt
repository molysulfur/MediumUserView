package com.example.molys.mediumuserviewapplication

import com.example.molys.mediumuserviewapplication.data.Profile
import com.example.molys.mediumuserviewapplication.data.Publication
import com.example.molys.mediumuserviewapplication.items.BaseItem
import com.example.molys.mediumuserviewapplication.items.ProfileItem
import com.example.molys.mediumuserviewapplication.items.PublicationItem
import com.example.molys.mediumuserviewapplication.items.TitleItem

class MediumCreator {
    companion object {
        const val TYPE_PROFILE = 0
        const val TYPE_PUBLICATION = 1
        const val TYPE_TITLE_PUBLICATION = 3

        fun toBaseItem(
            profile: Profile,
            publication: ArrayList<Publication>
        ) : List<BaseItem>{
            val listItem = ArrayList<BaseItem>()
            listItem.add(ProfileItem(profile))
            listItem.add(TitleItem())
            publication.forEach {
                listItem.add(PublicationItem(it))
            }
            return listItem
        }
    }
}