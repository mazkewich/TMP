package photomap.com.richard.photomap.presentation.map

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import kotlinx.android.synthetic.main.fragment_map.*

import photomap.com.richard.photomap.R

class MapFragment : Fragment(), OnMapReadyCallback, View.OnClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var mapView: MapView

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.uiSettings.isMyLocationButtonEnabled = false
        if (ContextCompat.checkSelfPermission(activity!!, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        }
    }

    private var mListener: OnMapFragmentListener? = null

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
        Log.w("Photo Map", "cameraAction")
    }

    private fun categoryAction() {
        Log.w("Photo Map", "categoryAction")
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnMapFragmentListener {

    }

    companion object {

        fun newInstance() = MapFragment()

    }
}// Required empty public constructor
