package com.example.potatoservice.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakao.vectormap.LatLng

class MapViewModel : ViewModel() {
    private val _cameraPosition = MutableLiveData<LatLng>()
    private val _zoomLevel = MutableLiveData<Int>()

    // 마지막 위치와 줌 레벨 저장
    fun saveLastLocation(latitude: Double, longitude: Double, zoom: Int) {
        _cameraPosition.value = LatLng.from(latitude, longitude)
        _zoomLevel.value = zoom
    }

    // 마지막 위치 가져오기
    fun getLastLocation(): Triple<Double, Double, Int> {
        val lat = _cameraPosition.value?.latitude ?: 37.402005
        val lon = _cameraPosition.value?.longitude ?: 127.108621
        val zoom = _zoomLevel.value ?: 15
        return Triple(lat, lon, zoom)
    }
}
