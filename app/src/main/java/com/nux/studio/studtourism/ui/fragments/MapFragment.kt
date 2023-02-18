package com.nux.studio.studtourism.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.databinding.FragmentMapBinding
import com.nux.studio.studtourism.ui.viewmodels.MapViewModel
import com.nux.studio.studtourism.util.extensions.observeFlow
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.ui_view.ViewProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding: FragmentMapBinding
        get() = _binding!!

    private val viewModel: MapViewModel by viewModels()
    private lateinit var mapObjects: MapObjectCollection

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        mapView = binding.mapView
        mapObjects = mapView.map.mapObjects.addCollection()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpMapView()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setUpMapView() {
        mapView.map.move(
            CameraPosition(MOSCOW_LOCATION_POINT, 10f, 0f, 0f)
        )
        addPlacemarks()
    }

    private fun addPlacemarks() {
        viewModel.mapState.observeFlow(viewLifecycleOwner) { mapState ->
            mapState.dormitories.forEach { dormitory ->
                dormitory.details?.mainInfo?.coordinates ?: return@forEach
                val latitude =
                    dormitory.details.mainInfo.coordinates.lat?.toDouble() ?: return@forEach
                val longitude =
                    dormitory.details.mainInfo.coordinates.lng?.toDouble() ?: return@forEach
                val point = Point(latitude, longitude)

                val image = ImageView(requireContext())
                image.setImageDrawable(
                    AppCompatResources.getDrawable(
                        requireContext(),
                        R.drawable.favourites
                    )
                )
                val viewProvider = ViewProvider(image)
                mapObjects.addPlacemark(point, viewProvider)
            }
        }
    }

    companion object {
        private val MOSCOW_LOCATION_POINT = Point(55.753789, 37.6209230)
    }
}