package com.example.potatoservice.ui.share

import com.example.potatoservice.ui.home.Service

interface AdapterCallback {
	fun onClicked(service: Service)
	fun onSpinnerSelected(category:Int, item:Int)
}
