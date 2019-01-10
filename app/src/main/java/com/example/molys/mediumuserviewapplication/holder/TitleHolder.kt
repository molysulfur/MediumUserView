package com.example.molys.mediumuserviewapplication.holder

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.molys.mediumuserviewapplication.R
import com.example.molys.mediumuserviewapplication.items.PublicationItem

class TitleHolder(val view : View) : RecyclerView.ViewHolder(view){

    private val tvTitlePublication : TextView = view.findViewById(R.id.tv_title_publication)
}