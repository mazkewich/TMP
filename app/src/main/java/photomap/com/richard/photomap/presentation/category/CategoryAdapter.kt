package photomap.com.richard.photomap.presentation.category

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import photomap.com.richard.photomap.R
import kotlinx.android.synthetic.main.item_category.*

/**
 * @author richard on 1/12/18.
 */

class CategoryAdapter(private val categories: List<Category>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CategoryViewHolder =
            CategoryViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_category,parent, false))

    override fun onBindViewHolder(holder: CategoryViewHolder?, position: Int) {
        holder?.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

       fun bind(category: Category) {
           val categoryCheckBox = itemView.findViewById<CategoryCheckBox>(R.id.categoryCheckBox)
           val categoryTextView = itemView.findViewById<TextView>(R.id.categoryTextView)
           categoryCheckBox.color = category.color
           categoryTextView.text = category.name
           categoryTextView.setTextColor(category.color)
           categoryCheckBox.isChecked = category.selected

           itemView.setOnClickListener {
               category.selected = !category.selected
               categoryCheckBox.isChecked = category.selected
           }

       }

    }
}