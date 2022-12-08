package ru.otus.otusrecycler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    private val items = mutableListOf(
        NewsItem("Title 1", "subtitle 1", Color.BLUE),
        NewsItem("Title 2", "subtitle 2", Color.YELLOW),
        NewsItem("Title 3", "subtitle 3", Color.GRAY),
        NewsItem("Title 1", "subtitle 1", Color.BLUE),
        NewsItem("Title 2", "subtitle 2", Color.YELLOW),
        NewsItem("Title 3", "subtitle 3", Color.GRAY),
        NewsItem("Title 1", "subtitle 1", Color.BLUE),
        NewsItem("Title 2", "subtitle 2", Color.YELLOW),
        NewsItem("Title 3", "subtitle 3", Color.GRAY),
        NewsItem("Title 1", "subtitle 1", Color.BLUE),
        NewsItem("Title 2", "subtitle 2", Color.YELLOW),
        NewsItem("Title 3", "subtitle 3", Color.GRAY),
        NewsItem("Title 1", "subtitle 1", Color.BLUE),
        NewsItem("Title 2", "subtitle 2", Color.YELLOW),
        NewsItem("Title 3", "subtitle 3", Color.GRAY)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
        initClickListeners()
    }

    private fun initClickListeners() {
        findViewById<View>(R.id.addBtn).setOnClickListener {
            items.add(2, NewsItem("New item", "new item", Color.BLACK))
            recyclerView.adapter?.notifyItemInserted(2 +1)
        }

        findViewById<View>(R.id.removeBtn).setOnClickListener {
            items.removeAt(2)
            recyclerView.adapter?.notifyItemRemoved(2+1)
        }
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = NewsAdapter(items, object : NewsAdapter.NewsClickListener {
            override fun onNewsClick(newsItem: NewsItem) {
                // startActivity
            }

            override fun onFavoriteClick(newsItem: NewsItem) {
                // change element -> notifyChange
            }

            override fun onDeleteClick(newsItem: NewsItem) {
                // change element -> notifyDelete
            }
        })

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() == items.size) {
                    // load data from server
                    repeat(4) {
                        items.add(NewsItem("---", "---", Color.BLACK))
                    }

                    recyclerView.adapter?.notifyItemRangeInserted(items.size + 1, 4)
                }
            }
        })

        val itemDecoration = CustomItemDecoration(this, DividerItemDecoration.VERTICAL)
//        ContextCompat.getDrawable(this, R.drawable.black_line_5dp)
        ResourcesCompat.getDrawable(resources, R.drawable.black_line_5dp, theme)
            ?.let { itemDecoration.setDrawable(it) }
//        itemDecoration.setDrawable(getDrawable(R.drawable.black_line_5dp)!!)
        recyclerView.addItemDecoration(itemDecoration)
    }

    class CustomItemDecoration(context: Context, orientation: Int) :
        DividerItemDecoration(context, orientation) {
        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            super.onDraw(c, parent, state)
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
        }
    }
}