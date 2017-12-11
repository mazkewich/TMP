package photomap.com.richard.photomap.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import photomap.com.richard.photomap.R
import photomap.com.richard.photomap.presentation.authorization.activities.AuthorizationActivity
import photomap.com.richard.photomap.presentation.tabHolder.TabHolderActivity
import photomap.com.richard.photomap.services.AuthorizationService

class SplashActivity : AppCompatActivity() {

    private val authorizationService = AuthorizationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = authorizationService.user

        if (currentUser == null) {
            val authorizationIntent = Intent(this, AuthorizationActivity::class.java)
            startActivity(authorizationIntent)
        } else {
            val holderIntent = Intent(this, TabHolderActivity::class.java)
            startActivity(holderIntent)
        }

        finish()
    }

}
