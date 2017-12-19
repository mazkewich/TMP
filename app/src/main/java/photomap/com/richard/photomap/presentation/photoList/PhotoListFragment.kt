package photomap.com.richard.photomap.presentation.photoList

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_photo_list.*

import photomap.com.richard.photomap.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PhotoListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PhotoListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhotoListFragment : Fragment() {

    private var mListener: OnPhotoListFragmentListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photo_list, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnPhotoListFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuItems = listOf("Item", "Item", "Item", "Item", "Item", "Item", "Item", "Item", "Item")

        val listViewAdapter = PhotoListAdapter(view.context, menuItems)
        photoListView.adapter = listViewAdapter
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
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
    interface OnPhotoListFragmentListener {

    }

    companion object {

        fun newInstance() = PhotoListFragment()

    }
}// Required empty public constructor
