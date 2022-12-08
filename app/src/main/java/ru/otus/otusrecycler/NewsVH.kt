package ru.otus.otusrecycler

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleTv: TextView = itemView.findViewById(R.id.titleTv)
    val subtitleTv: TextView = itemView.findViewById(R.id.subtitleTv)
    val image: ImageView = itemView.findViewById(R.id.image)

    fun bind(item: NewsItem, clickListener: NewsAdapter.NewsClickListener) {
        titleTv.text = item.title
        subtitleTv.text = item.subTitle

        if (item.color == Color.BLUE) {
            titleTv.setTextColor(Color.GREEN)
        } else {
            titleTv.setTextColor(Color.GRAY)
        }

        image.setBackgroundColor(item.color)
        itemView.setOnClickListener {
            clickListener.onNewsClick(item)
        }
        image.setOnClickListener {
            clickListener.onFavoriteClick(item)
        }
        titleTv.setOnClickListener {
            clickListener.onDeleteClick(item)
        }
    }
}