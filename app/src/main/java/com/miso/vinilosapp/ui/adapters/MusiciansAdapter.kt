package com.miso.vinilosapp.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.miso.vinilosapp.R
import com.miso.vinilosapp.databinding.MusicianItemBinding
import com.miso.vinilosapp.models.Album
import com.miso.vinilosapp.models.Musician
import com.miso.vinilosapp.ui.musiciansDirections
import com.squareup.picasso.Picasso

class MusiciansAdapter : RecyclerView.Adapter<MusiciansAdapter.MusicianViewHolder>(){

    var musicians :List<Musician> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianViewHolder {
        Log.d("Musicians adapter" , "On create view")
        val withDataBinding: MusicianItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            MusicianViewHolder.LAYOUT,
            parent,
            false)
        return MusicianViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: MusicianViewHolder, position: Int) {
        Log.d("Musicians" , "adapter - on bind view holder")
        holder.viewDataBinding.also {
            it.musician = musicians[position]
        }
        holder.bind(musicians[position])
        holder.viewDataBinding.root.setOnClickListener {
            val action = musiciansDirections.actionMusicianFragmentToFragmentMusiciansDetail(
                musicians[position].Id,
                musicians[position].name,
                musicians[position].image,
                musicians[position].description,
                musicians[position].albums!!.toTypedArray()
            )
            // Navigate using that action
            holder.viewDataBinding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return musicians.size
    }


    class MusicianViewHolder(val viewDataBinding: MusicianItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.musician_item
        }
        fun bind(musician: Musician){
            Picasso.get().load(musician.image).into(viewDataBinding.iVCoverMusician)
        }
    }


}