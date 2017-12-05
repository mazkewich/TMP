package photomap.com.richard.photomap.authorization

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import photomap.com.richard.photomap.R
import photomap.com.richard.photomap.authorization.Fragments.SignInFragment
import photomap.com.richard.photomap.authorization.Fragments.SignUpFragment

class AuthorizationActivity : AppCompatActivity(), SignInFragment.OnSignInFragmentListener, SignUpFragment.OnSignUpFragmentListener {

    private lateinit var signUpFragment: SignUpFragment
    private lateinit var signInFragment: SignInFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        configureSignInFragment()
        configureSignUpFragment()

        addFragment(signInFragment, R.id.fragmentContainer)
    }

    private fun configureSignInFragment() {
        signInFragment = SignInFragment.newInstance()

    }

    private fun configureSignUpFragment() {
        signUpFragment = SignUpFragment.newInstance()
    }

    override fun showSignUpFragment() {
        replaceFragment(signUpFragment, R.id.fragmentContainer)
    }

    override fun signIn() {
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }

    override fun signUp() {

    }

}
