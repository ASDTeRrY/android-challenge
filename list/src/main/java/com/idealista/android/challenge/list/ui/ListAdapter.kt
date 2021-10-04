package com.idealista.android.challenge.list.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.idealista.android.challenge.list.R
import com.squareup.picasso.Picasso

class ListAdapter(private val context: Context) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    interface AdListener {
        fun onAdClicked(ad: AdModel)
    }

    private var ads: List<AdModel> = emptyList()
    private lateinit var listener: AdListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_ad, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = ads.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        with (ads[position]) {
            if (thumbnail.isNotEmpty()) Picasso.with(holder.image.context).load(thumbnail).into(holder.image)
            holder.title.text = title
            holder.price.text = price
            holder.parent.setOnClickListener{
                //Toast.makeText(context, ads[position].title, Toast.LENGTH_LONG).show()
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("keyImage", ads[position].thumbnail)
                intent.putExtra("keyPrice", ads[position].price)
                intent.putExtra("keyDescription", ads[position].title)
               context.startActivity(intent)
            }
        }
    }

    fun set(listModel: ListModel) {
        ads = listModel.ads
        notifyDataSetChanged()
    }

    fun listener(listener: AdListener) {
        this.listener = listener
    }
    fun listenerClick(){

    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.ivAd)
        var title: TextView = view.findViewById(R.id.tvTitle)
        var price: TextView = view.findViewById(R.id.tvPrice)
        var parent: View = view.findViewById(R.id.parent)

    }

}