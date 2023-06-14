package com.example.test_radio_france.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ShowsQuery
import com.example.test_radio_france.R

class ShowAdapter(private val ShowAffiche: ShowsQuery.Data?) : RecyclerView.Adapter<ShowAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val shows = LayoutInflater.from(parent.context).inflate(R.layout.recycleviewshow, parent, false)
        return ViewHolder(shows)
    }

    override fun getItemCount(): Int = ShowAffiche?.shows?.edges?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val show = ShowAffiche?.shows?.edges?.get(position)!!
        holder.bind(show)
    }

    class ViewHolder(elementList: View) : RecyclerView.ViewHolder(elementList) {
        private val titre = itemView.findViewById(R.id.title) as TextView
        fun bind(show: ShowsQuery.Edge) {
            titre.text = show.node?.title

        }
    }
}