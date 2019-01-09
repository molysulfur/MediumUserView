package com.example.molys.mediumuserviewapplication.holder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.molys.mediumuserviewapplication.R
import com.example.molys.mediumuserviewapplication.items.PublicationItem

class PublicationHolder(val view : View) : RecyclerView.ViewHolder(view){

    private val imgPublication : ImageView = view.findViewById(R.id.img_publication)
    private val tvPublicationName : TextView = view.findViewById(R.id.tv_publication_name)
    private val tvPublicationDescription : TextView = view.findViewById(R.id.tv_publication_description)
    private val btnPage : Button = view.findViewById(R.id.btn_page)

    fun onBind(publication: PublicationItem){
        Glide.with(view.context).load(publication.imageUrl).into(imgPublication)
        tvPublicationName.text = publication.name
        tvPublicationDescription.text = publication.description
    }
}