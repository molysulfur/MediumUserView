package com.example.molys.mediumuserviewapplication.holder

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.example.molys.mediumuserviewapplication.items.PublicationItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_publication.btnPage
import kotlinx.android.synthetic.main.layout_publication.imgPublication
import kotlinx.android.synthetic.main.layout_publication.tvPublicationDescription
import kotlinx.android.synthetic.main.layout_publication.tvPublicationName

class PublicationHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),LayoutContainer{

    fun onBind(publication: PublicationItem){
        Glide.with(containerView.context).load(publication.imageUrl).into(imgPublication)
        tvPublicationName.text = publication.name
        tvPublicationDescription.text = publication.description
        btnPage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(publication.url)
            containerView.context.startActivity(intent)
        }
    }
}