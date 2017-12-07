package photomap.com.richard.photomap.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object AuthorizationService {

    private val mAuth = FirebaseAuth.getInstance()

    val user: FirebaseUser?
        get() {
            return mAuth.currentUser
        }

    fun signIn(email: String, password: String): Task<AuthResult> {
        return mAuth.signInWithEmailAndPassword(email, password)
    }

    fun signUp(email: String, password: String): Task<AuthResult> {
        return mAuth.createUserWithEmailAndPassword(email, password)
    }

}