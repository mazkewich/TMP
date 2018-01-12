package photomap.com.richard.photomap.presentation.tabHolder

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_tab_holder.*
import photomap.com.richard.photomap.R
import photomap.com.richard.photomap.presentation.map.MapFragment
import photomap.com.richard.photomap.presentation.photoList.PhotoListFragment

class TabHolderActivity : AppCompatActivity(), MapFragment.OnMapFragmentListener, PhotoListFragment.OnPhotoListFragmentListener {

    private val mapFragment = MapFragment.newInstance()
    private val photoListFragment = PhotoListFragment.newInstance()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_map -> {
                Log.d("Photo Map", "Map tab")
                supportFragmentManager.beginTransaction().replace(R.id.fragmentsContainer, mapFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_photo_list -> {
                Log.d("Photo Map", "Photo list tab")
                supportFragmentManager.beginTransaction().replace(R.id.fragmentsContainer, photoListFragment).commit()
                return@OnNavigationItemSelectedListener true
            }
        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_holder)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        supportFragmentManager.beginTransaction().add(R.id.fragmentsContainer, mapFragment).commit()
    }

}
