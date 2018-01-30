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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        recyclerView.adapter = categoryAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        val selectedCategories = categoryAdapter.categories.filter { it.selected }
        val categoriesIntent = Intent()
        categoriesIntent.putExtra("selectedCategories", selectedCategories.toTypedArray())
        setResult(RESULT_OK, categoriesIntent)

        super.onBackPressed()
    }
}