package com.example.allinone.page2.contentProvider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.allinone.R
import com.example.allinone.main.MainAdapter

class ContentProviderAdapter(private val dataSet: ArrayList<String>) :
    RecyclerView.Adapter<ContentProviderAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tx)
        val cardView: CardView = view.findViewById(R.id.cv)
//        val cardView: CardView = view.findViewById(R.id.cv)

    }


    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ContentProviderAdapter.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.contacts_cardview, viewGroup, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position]
    }

    override fun getItemCount(): Int = dataSet.size


}