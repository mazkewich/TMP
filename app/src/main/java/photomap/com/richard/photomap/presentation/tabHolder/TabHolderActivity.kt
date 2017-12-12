package photomap.com.richard.photomap.presentation.tabHolder

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_tab_holder.*
import photomap.com.richard.photomap.R

class TabHolderActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_map -> {
                message.setText(R.string.title_map)
                Log.d("Photo Map", "Map tab")
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_photo_list -> {
                message.setText(R.string.title_photo_list)
                Log.d("Photo Map", "Photo list tab")
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_holder)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
