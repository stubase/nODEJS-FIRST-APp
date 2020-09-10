
package com.capstone.hibykes.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.hibykes.data.local.entity.PredictionEntity
import com.capstone.hibykes.databinding.ItemBookmarksBinding

class BookmarkAdapter(private val listBookmark: ArrayList<PredictionEntity>) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: PredictionEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    fun setList(predictions: ArrayList<PredictionEntity>) {
        listBookmark.clear()
        listBookmark.addAll(predictions)
        notifyDataSetChanged()
    }

    class BookmarkViewHolder(private val binding: ItemBookmarksBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bookmark: PredictionEntity) {
            with(binding) {
                tvDatetime.text = bookmark.datetime
                tvDemand.text = bookmark.demandCount.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val itemBookmarksBinding = ItemBookmarksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(itemBookmarksBinding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark = listBookmark[position]
        holder.bind(bookmark)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listBookmark[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listBookmark.size
}