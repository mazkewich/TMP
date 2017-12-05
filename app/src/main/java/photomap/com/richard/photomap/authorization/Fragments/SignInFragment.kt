package photomap.com.richard.photomap.authorization.Fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_sign_in.*

import photomap.com.richard.photomap.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SignInFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SignInFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignInFragment : Fragment(), View.OnClickListener {

    private var mListener: OnSignInFragmentListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnSignInFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_sign_in, container, false)
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

        when (p0.id) {
            signInButton.id -> mListener?.signIn()
            signUpButton.id -> mListener?.showSignUpFragment()
            else -> {
                Log.d("Photo Map", "Other button was clicked")
            }
        }
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
    interface OnSignInFragmentListener {
        fun showSignUpFragment()
        fun signIn()
    }

    companion object {

        fun newInstance() = SignInFragment()

    }
}
