package viniciuskotlin.com.kfeedreader

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS

class MainActivity : AppCompatActivity(), Callback {

    lateinit var recyclerViewlistView: RecyclerView
    lateinit var itemAdapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>
    val listItens = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = LinearLayoutManager(this)
        recyclerViewlistView = findViewById(R.id.activitymain_recyclerview)
        recyclerViewlistView.layoutManager = layout

        itemAdapter = ItemAdapter(listItens, this)
        recyclerViewlistView.adapter = itemAdapter

        PkRSS.with(this).load("https://rss.tecmundo.com.br/feed").callback(this).async()
    }

    override fun onPreload() {

    }

    override fun onLoaded(newArticles: MutableList<Article>?) {
        listItens.clear()
        newArticles?.mapTo(listItens) {
            Item(it.title, it.author, it.date, it.source, it.enclosure.url)
        }

        itemAdapter.notifyDataSetChanged()
    }

    override fun onLoadFailed() {

    }

    data class Item(val titulo: String, val autor: String, val data: Long, val link: Uri, val imagem: String)
}