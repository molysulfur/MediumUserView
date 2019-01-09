package com.example.molys.mediumuserviewapplication.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.molys.mediumuserviewapplication.R
import com.example.molys.mediumuserviewapplication.items.ProfileItem

class ProfileHolder(val view : View) : RecyclerView.ViewHolder(view){
    private val imgProfile : ImageView = view.findViewById(R.id.img_profile)
    private val tvUsername : TextView = view.findViewById(R.id.tv_username)
    private val tvName : TextView = view.findViewById(R.id.tv_name)

    fun onBind(profile: ProfileItem){
        Glide.with(view.context).load(profile.imageUrl).into(imgProfile)
        tvUsername.text = profile.username
        tvName.text = profile.name
    }
}