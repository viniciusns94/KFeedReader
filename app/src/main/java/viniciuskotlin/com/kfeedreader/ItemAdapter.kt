package viniciuskotlin.com.kfeedreader

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter(val list: ArrayList<MainActivity.Item>, val context: Context) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.itemlist_textview_titulo)
        val autor: TextView = view.findViewById(R.id.itemlist_textview_autor)
        val data: TextView = view.findViewById(R.id.itemlist_textview_data)
        val imagem: ImageView = view.findViewById(R.id.itemlist_imageview_imagem)
        val botaoVerMais: Button = view.findViewById(R.id.itemlist_button_vermais)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_list, parent, false)
        val holder = ItemViewHolder(v)
        return holder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder?.titulo?.text = list[position].titulo
        holder?.autor?.text = list[position].autor
        holder?.data?.text = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).format(Date(list[position].data))
        holder?.botaoVerMais?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, list[position].link)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size
}