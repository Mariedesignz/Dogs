package com.example.dogs.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogs.Model.DogsApi
import com.example.dogs.R
import com.squareup.picasso.Picasso


class DogsAdapter (val context: Context?, private val dogsImages: ArrayList<DogsApi>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.dogs_rv_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // This where I am binding views

        Picasso.get().load(dogsImages[position].message).into(holder.itemView.dogImage)

    }

    override fun getItemCount(): Int {
        return dogsImages.size
    }

    class ViewHolder(v: View?) : RecyclerView.ViewHolder(v!!), View.OnClickListener {
        override fun onClick(p0: View?) {

            // Onclick function here
        }

        init {
            itemView.setOnClickListener(this)
        }

        val dogImage = itemView.dogImage!!

    }
}