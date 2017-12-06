package photomap.com.richard.photomap.authorization.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import photomap.com.richard.photomap.R
import photomap.com.richard.photomap.authorization.fragments.SignInFragment
import photomap.com.richard.photomap.authorization.fragments.SignUpFragment
import photomap.com.richard.photomap.authorization.utils.addFragment
import photomap.com.richard.photomap.authorization.utils.replaceFragment

class AuthorizationActivity : AppCompatActivity(), SignInFragment.OnSignInFragmentListener, SignUpFragment.OnSignUpFragmentListener {

    private lateinit var signUpFragment: SignUpFragment
    private lateinit var signInFragment: SignInFragment

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        configureSignInFragment()
        configureSignUpFragment()

        auth = FirebaseAuth.getInstance()
        addFragment(signInFragment, R.id.fragmentContainer)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        Log.d("Photo Map", currentUser.toString())
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

    override fun signIn(email: String, password: String) {
        if (!isValidCredentials(email, password)) {
            showValidateToast(email, password)
            return
        }
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            task: Task<AuthResult> ->
            if (task.isSuccessful) {
                Log.d("Photo Map", "signInWithEmail:success")
            } else {
                Log.w("Photo Map", "signInWithEmail:failure", task.exception)
                showFailedToast()
            }
        }
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }

    override fun signUp(email: String, password: String) {
        if (!isValidCredentials(email, password)) {
            showValidateToast(email, password)
            return
        }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            task: Task<AuthResult> ->
            if (task.isSuccessful) {
                Log.d("Photo Map", "createUserWithEmail:success")
            } else {
                Log.w("Photo Map", "createUserWithEmail:failure", task.exception)
                showFailedToast()
            }
        }
    }

    private fun isValidCredentials(email: String, password: String): Boolean {
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || password.isEmpty() || password.length < 6) {
            return false
        }

        return true
    }

    private fun showValidateToast(email: String, password: String) {
        if (email.isEmpty()) {
            Toast.makeText(this, "Enter an email address!", Toast.LENGTH_SHORT).show()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.isEmpty() || password.length < 6) {
            Toast.makeText(this, "Password should be at least 6 characters", Toast.LENGTH_SHORT).show()
            return
        }
    }

    private fun showFailedToast() {
        Toast.makeText(this,"Authentication failed.", Toast.LENGTH_SHORT).show()
    }

}
