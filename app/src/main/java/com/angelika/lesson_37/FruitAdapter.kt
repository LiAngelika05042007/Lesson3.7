package com.angelika.lesson_37

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.angelika.lesson_37.databinding.ItemFruitBinding
import com.bumptech.glide.Glide

class FruitAdapter(private val onItemClick: (fruitModel: Fruit) -> Unit) :
    RecyclerView.Adapter<FruitAdapter.FruitViewHolder>() {

    private val fruits = arrayListOf<Fruit>()

    fun addImage(fruit: Fruit) {
        fruits.add(fruit)
        notifyDataSetChanged()
    }

    inner class FruitViewHolder(private val binding: ItemFruitBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onItemClick(fruits[adapterPosition])
            }
        }

        fun onBind(fruit: Fruit) = with(binding) {
            Glide.with(ivPictureFruit.context)
                .load(fruit.image)
                .placeholder(R.drawable.ic_launcher_background)
                .into(ivPictureFruit)
            tvNameFruit.text = fruit.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val item = ItemFruitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FruitViewHolder(item)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        holder.onBind(fruits[position])
    }

    override fun getItemCount(): Int {
        return fruits.size
    }
}