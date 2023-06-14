package com.example.test_radio_france.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.BrandsQuery
import com.example.test_radio_france.R
import com.example.test_radio_france.activity.RadioActivity

class RadioAdapter(private val RadioAffiche: BrandsQuery.Data?, private val contexte:  android.content.Context) : RecyclerView.Adapter<RadioAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val radios = LayoutInflater.from(parent.context).inflate(R.layout.recycleview, parent, false)
        return ViewHolder(radios)
    }

    override fun getItemCount(): Int = RadioAffiche?.brands?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val radio = RadioAffiche?.brands?.get(position)!!
        holder.bind(radio)
        holder.itemView.setOnClickListener {
            val intent = Intent(contexte, RadioActivity::class.java)
            intent.putExtra("id_radio", radio.id)
            intent.putExtra("title_radio", radio.title)
            contexte.startActivity(intent)
        }
    }

    class ViewHolder(elementList: View) : RecyclerView.ViewHolder(elementList) {
        private val titre = itemView.findViewById(R.id.Titre) as TextView
        private val baseline = itemView.findViewById(R.id.Baseline) as TextView
        private val description = itemView.findViewById(R.id.Description) as TextView
        fun bind(radio: BrandsQuery.Brand){
            titre.text = radio.title
            baseline.text = radio.baseline
            description.text = radio.description
            if (radio.description == ""){
                description.visibility = View.GONE
            }
        }
    }
}