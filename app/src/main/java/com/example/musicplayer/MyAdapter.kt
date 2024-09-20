package com.example.musicplayer

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context:Activity, val musicList: List<Data>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    //to implement clicking function of Recycler View
    //S1
    interface onItemClickListener{
        fun onItemClicking(position: Int){
        }
    }

    //S2
    private lateinit var myListener: onItemClickListener


    //S3 then in viewHolder
    fun setOnClickListener(listener: onItemClickListener){
        myListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.each_card_inrv,parent,false)
        return MyViewHolder(itemView,myListener)
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currSong=musicList[position]
        holder.mHeading.text=currSong.title
        holder.mSinger.text=currSong.artist.name
        //picasso
        Picasso.get().load(currSong.album.cover).into(holder.mImage);

    }
    class MyViewHolder(itemView:View,listener:onItemClickListener):RecyclerView.ViewHolder(itemView) {
        val mHeading=itemView.findViewById<TextView>(R.id.tVSong)
        val mSinger=itemView.findViewById<TextView>(R.id.tVSinger)
        val mImage=itemView.findViewById<ImageView>(R.id.songImage)

        //to impl  clicking fun og rv
        init {
            itemView.setOnClickListener {
                listener.onItemClicking(adapterPosition)
            }
        }
    }

}
