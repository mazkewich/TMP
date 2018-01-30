package photomap.com.richard.photomap.presentation.map

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.baoyz.actionsheet.ActionSheet
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_map.*

import photomap.com.richard.photomap.R
import photomap.com.richard.photomap.core.CategoryType
import photomap.com.richard.photomap.presentation.category.CategoryActivity

class MapFragment : Fragment(), OnMapReadyCallback, View.OnClickListener, GoogleMap.OnMapLongClickListener, ActionSheet.ActionSheetListener {

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView

    private var mListener: OnMapFragmentListener? = null

    private lateinit var actionSheet: ActionSheet.Builder

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnMapFragmentListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onResume() {
        mapView.onResume()
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_map, container, false)

        mapView = v.findViewById<MapView>(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        actionSheet = ActionSheet.createBuilder(activity, activity?.supportFragmentManager)
                .setCancelButtonTitle(R.string.cancel)
                .setOtherButtonTitles(getString(R.string.take_a_picture), getString(R.string.choose_from_library))
                .setCancelableOnTouchOutside(true)
                .setListener(this)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        categoryButton.setOnClickListener(this)
        locationButton.setOnClickListener(this)
        cameraButton.setOnClickListener(this)
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
            locationButton.id -> locationAction()
            cameraButton.id -> cameraAction()
            categoryButton.id -> categoryAction()
            else -> {
                Log.w("Photo Map", "Other button was clicked")
            }
        }
    }

    private fun locationAction() {
        locationButton.isSelected = !locationButton.isSelected
        Log.w("Photo Map", "locationAction")
    }

    private fun cameraAction() {
        actionSheet.show()
    }

    private fun categoryAction() {
        val categoryIntent = Intent(activity, CategoryActivity::class.java)
        categoryIntent.putExtra("categoryType", CategoryType.MAP)
        startActivityForResult(categoryIntent, 2)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isMyLocationButtonEnabled = false
        if (ContextCompat.checkSelfPermission(activity!!, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        }
        mMap.setOnMapLongClickListener(this)
    }

    override fun onMapLongClick(p0: LatLng?) {
        actionSheet.show()
    }

    override fun onDismiss(actionSheet: ActionSheet?, isCancel: Boolean) {

    }

    override fun onOtherButtonClick(actionSheet: ActionSheet?, index: Int) {
        when (index) {
            0 -> showCamera()
            1 -> showGallery()
            else -> {
                Log.w("Photo Map", "Other button was clicked")
            }
        }
    }

    private fun showCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, 0)
    }

    private fun showGallery() {
        val pickPhotoIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhotoIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            2 -> Log.w("Photo Map", "Categories")
        }
    }

    interface OnMapFragmentListener {

    }

    companion object {

        fun newInstance() = MapFragment()

    }
}// Required empty public constructor
