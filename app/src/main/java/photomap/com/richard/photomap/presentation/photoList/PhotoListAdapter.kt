package photomap.com.richard.photomap.presentation.photoList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import photomap.com.richard.photomap.R

/**
 * Created by rychardmatskevich on 12/19/17.
 */

class PhotoListAdapter(context: Context, items: List<String>) : BaseAdapter() {

    private var items: List<String> = items
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = convertView ?: inflater.inflate(R.layout.photo_item, parent, false)

        val titleTextView = rowView.findViewById<TextView>(R.id.titleTextView)
        titleTextView.text = items[position]

        val dateTextView = rowView.findViewById<TextView>(R.id.dateTextView)
        dateTextView.text = items[position]

        return rowView
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }

}