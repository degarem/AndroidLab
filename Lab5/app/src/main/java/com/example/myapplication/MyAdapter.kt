package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter:RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private val data = mutableListOf<User>()

    var listener: OnListItemClickListener? = null


    interface OnListItemClickListener {
        fun onClick(user: User)
    }

    fun addItem(item: User) {
        data.add(item)
        notifyItemInserted(data.size - 1)
    }

    fun setData(items: List<User>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            listener,
            LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size


    inner class MyViewHolder(private val listener: OnListItemClickListener?,itemView: View):RecyclerView.ViewHolder(itemView){

        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
        private val textViewTime: TextView = itemView.findViewById(R.id.textViewTime)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(user: User){
            Glide.with(imageView).load(user.imageUrl).into(imageView)
        }
    }
}