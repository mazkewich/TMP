package photomap.com.richard.photomap.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseUser
import photomap.com.richard.photomap.R

class SplashActivity : AppCompatActivity() {

    private lateinit var currentUser: FirebaseUser

    override fun onStart() {
        super.onStart()

//        currentUser =
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}
