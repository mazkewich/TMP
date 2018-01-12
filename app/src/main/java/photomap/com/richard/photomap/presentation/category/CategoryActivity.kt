package photomap.com.richard.photomap.presentation.category

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_category.*
import photomap.com.richard.photomap.R
import photomap.com.richard.photomap.services.DataService

/**
 * @author richard on 1/12/18.
 */

class CategoryActivity: Activity() {

    private val categoryAdapter: CategoryAdapter = CategoryAdapter(DataService.categories)

    companion object {
        fun start(context: Context?) {
            val intent = Intent(context, CategoryActivity::class.java)
            context?.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        recyclerView.adapter = categoryAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter.notifyDataSetChanged()
    }
}