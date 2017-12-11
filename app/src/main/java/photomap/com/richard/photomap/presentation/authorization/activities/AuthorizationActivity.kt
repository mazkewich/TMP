package photomap.com.richard.photomap.presentation.authorization.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import photomap.com.richard.photomap.R
import photomap.com.richard.photomap.presentation.authorization.fragments.SignInFragment
import photomap.com.richard.photomap.presentation.authorization.fragments.SignUpFragment
import photomap.com.richard.photomap.presentation.authorization.utils.addFragment
import photomap.com.richard.photomap.presentation.authorization.utils.replaceFragment
import photomap.com.richard.photomap.presentation.tabHolder.TabHolderActivity
import photomap.com.richard.photomap.services.AuthorizationService

class AuthorizationActivity : AppCompatActivity(), SignInFragment.OnSignInFragmentListener, SignUpFragment.OnSignUpFragmentListener {

    private lateinit var signUpFragment: SignUpFragment
    private lateinit var signInFragment: SignInFragment

    private val authorizationService = AuthorizationService

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

    override fun signIn(email: String, password: String) {
        if (!isValidCredentials(email, password)) {
            showValidateToast(email, password)
            return
        }

        val progressDialog = MaterialDialog.Builder(this).title("Sign In..").progress(true, 0).show()
        authorizationService.signIn(email, password).addOnCompleteListener {
            task: Task<AuthResult> ->
            if (task.isSuccessful) {
                Log.d("Photo Map", "signInWithEmail:success")
                progressDialog.hide()
                showTabHolder()
            } else {
                Log.w("Photo Map", "signInWithEmail:failure", task.exception)
                showFailedToast()
            }
        }
    }

    private fun showTabHolder() {
        val holderIntent = Intent(this, TabHolderActivity::class.java)
        startActivity(holderIntent)
        finish()
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }

    override fun signUp(email: String, password: String) {
        if (!isValidCredentials(email, password)) {
            showValidateToast(email, password)
            return
        }

        val progressDialog = MaterialDialog.Builder(this).title("Sign UP..").progress(true, 0).show()
        authorizationService.signUp(email, password).addOnCompleteListener {
            task: Task<AuthResult> ->
            progressDialog.hide()
            if (task.isSuccessful) {
                Log.d("Photo Map", "createUserWithEmail:success")
                signUpFragment.cleanTextEdit()
                back()
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
