package photomap.com.richard.photomap.authorization.utils

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import photomap.com.richard.photomap.R

fun AppCompatActivity.addFragment(fragment: Fragment, containerID: Int) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(containerID, fragment)
    transaction.commit()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, containerID: Int) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
    transaction.addToBackStack(null)
    transaction.replace(containerID, fragment)
    transaction.commit()
}
