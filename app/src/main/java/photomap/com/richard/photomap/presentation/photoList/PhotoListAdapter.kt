package photomap.com.richard.photomap.presentation.photoList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import photomap.com.richard.photomap.R

/**
 * Created by rychardmatskevich on 12/19/17.
 */

class PhotoListAdapter(context: Context, private var items: List<String>) : BaseAdapter() {

    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = convertView ?: inflater.inflate(R.layout.photo_item, parent, false)
        val rowHolder = PhotoListRowHolder(rowView)

        rowHolder.titleLabel.text = items[position]
        rowHolder.dateLabel.text = items[position]

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

    private class PhotoListRowHolder(row: View) {
        val titleLabel: TextView = row.findViewById<TextView>(R.id.titleTextView)
        val dateLabel: TextView = row.findViewById<TextView>(R.id.dateTextView)
        val imageView: ImageView = row.findViewById<ImageView>(R.id.photoImageView)
    }

}