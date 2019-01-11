package com.example.molys.mediumuserviewapplication.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.molys.mediumuserviewapplication.items.ProfileItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_profile.imgProfile
import kotlinx.android.synthetic.main.layout_profile.tvName
import kotlinx.android.synthetic.main.layout_profile.tvUsername

class ProfileHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),LayoutContainer{

    fun onBind(profile: ProfileItem){
        Glide.with(containerView.context).load(profile.imageUrl).into(imgProfile)
        tvUsername.text = profile.username
        tvName.text = profile.name
    }
}