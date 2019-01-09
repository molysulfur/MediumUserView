package com.example.molys.mediumuserviewapplication.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.molys.mediumuserviewapplication.MediumCreator
import com.example.molys.mediumuserviewapplication.R
import com.example.molys.mediumuserviewapplication.holder.ProfileHolder
import com.example.molys.mediumuserviewapplication.holder.PublicationHolder
import com.example.molys.mediumuserviewapplication.items.BaseItem
import com.example.molys.mediumuserviewapplication.items.ProfileItem
import com.example.molys.mediumuserviewapplication.items.PublicationItem

class MediumAdapter(private val listBaseItem: List<BaseItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(viewGroup: ViewGroup, type: Int): RecyclerView.ViewHolder {
        when(type){
            MediumCreator.TYPE_PROFILE -> {
                val profile = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_profile,viewGroup,false)
                return ProfileHolder(profile)
            }
            MediumCreator.TYPE_PUBLICATION -> {
                val publication = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout_publication,viewGroup,false)
                return PublicationHolder(publication)
            }
            else -> return super.createViewHolder(viewGroup,type)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return listBaseItem[position].type
    }

    override fun getItemCount(): Int = listBaseItem.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        when{
            getItemViewType(position) == MediumCreator.TYPE_PROFILE ->{
                val profile = viewHolder as ProfileHolder
                viewHolder.onBind(listBaseItem[position] as ProfileItem)
            }
            getItemViewType(position) == MediumCreator.TYPE_PUBLICATION ->{
                val profile = viewHolder as PublicationHolder
                viewHolder.onBind(listBaseItem[position] as PublicationItem)
            }
        }
    }

}