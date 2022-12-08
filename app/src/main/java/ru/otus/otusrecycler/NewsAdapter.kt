package ru.otus.otusrecycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(private val items: List<NewsItem>, private val clickListener: NewsClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val TAG = "NewsAdapter"

        const val VIEW_TYPE_NEWS = 0
        const val VIEW_TYPE_HEADER = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d(TAG, "onCreateViewHolder $viewType")
        // xml -> View = inflate
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_NEWS) {
            val view = layoutInflater.inflate(R.layout.item_news, parent, false)
            NewsVH(view)
        } else {
            val view = layoutInflater.inflate(R.layout.item_header, parent, false)
            NewsHeaderVH(view)
        }
    }

    override fun getItemViewType(position: Int)
            = if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_NEWS


    override fun getItemCount() = items.size + 1 // +1 = header

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d(TAG, "onBindViewHolder $position")

        if (holder is NewsVH) {
            val item = items[position - 1]
            holder.bind(item, clickListener)
        }
    }

    interface NewsClickListener {
        fun onNewsClick(newsItem: NewsItem)
        fun onFavoriteClick(newsItem: NewsItem)
        fun onDeleteClick(newsItem: NewsItem)
    }
}