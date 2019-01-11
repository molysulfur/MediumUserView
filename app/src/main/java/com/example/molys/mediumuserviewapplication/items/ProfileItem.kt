package com.example.molys.mediumuserviewapplication.items

import com.example.molys.mediumuserviewapplication.MediumCreator
import com.example.molys.mediumuserviewapplication.data.Profile

class ProfileItem(profile: Profile) : BaseItem(MediumCreator.TYPE_PROFILE){
    var imageUrl: String? = profile.imageUrl
    var username: String? = profile.username
    var name : String? = profile.name
    var url: String? = profile.url
}