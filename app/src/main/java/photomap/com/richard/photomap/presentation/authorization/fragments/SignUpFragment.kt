package photomap.com.richard.photomap.presentation.authorization.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_sign_up.*

import photomap.com.richard.photomap.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SignUpFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment(), View.OnClickListener, Animation.AnimationListener {

    private var mListener: OnSignUpFragmentListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnSignUpFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        signInButton.setOnClickListener(this)
        signUpButton.setOnClickListener(this)
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onClick(p0: View?) {
        if (p0 == null) {
            return
        }

        when (p0.id){
            signInButton.id -> mListener?.back()
            signUpButton.id -> signUpAction()
            else -> {
                Log.w("Photo Map", "Other button was clicked")
            }
        }
    }

    private fun signUpAction() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        mListener?.signUp(email, password)
    }

    // Animation handling

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        val animationID = if (enter) R.anim.enter_from_right else R.anim.exit_to_right
        val animation = AnimationUtils.loadAnimation(activity, animationID)
        animation.setAnimationListener(this)
        return animation
    }

    override fun onAnimationRepeat(p0: Animation?) {}

    override fun onAnimationStart(p0: Animation?) {
        activity.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    override fun onAnimationEnd(p0: Animation?) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnSignUpFragmentListener {
        fun back()
        fun signUp(email: String, password: String)
    }

    companion object {

        fun newInstance() = SignUpFragment()

    }
}
