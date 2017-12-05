package photomap.com.richard.photomap.authorization

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import photomap.com.richard.photomap.R
import photomap.com.richard.photomap.authorization.Fragments.SignInFragment
import photomap.com.richard.photomap.authorization.Fragments.SignUpFragment

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

    override fun signIn() {
        auth.signInWithEmailAndPassword("qwe@ewq.com", "Testpwd1").addOnCompleteListener {
            task: Task<AuthResult> ->
            if (task.isSuccessful) {
                Log.d("Photo Map", "signInWithEmail:success")
            } else {
                Log.w("Photo Map", "signInWithEmail:failure", task.getException());
                Toast.makeText(this,"Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun back() {
        supportFragmentManager.popBackStack()
    }

    override fun signUp() {
        auth.createUserWithEmailAndPassword("qwe@ewq.com", "Testpwd1").addOnCompleteListener {
            task: Task<AuthResult> ->
            if (task.isSuccessful) {
                Log.d("Photo Map", "createUserWithEmail:success")
            } else {
                Log.w("Photo Map", "createUserWithEmail:failure", task.getException());
                Toast.makeText(this,"Authentication failed.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
