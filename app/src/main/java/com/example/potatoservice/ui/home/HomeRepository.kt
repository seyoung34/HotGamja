package com.example.potatoservice.ui.home

import android.util.Log
import com.example.potatoservice.model.remote.Activity
import com.example.potatoservice.ui.share.Request
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class HomeRepository @Inject constructor(
	private val remoteDataSource: RemoteDataSource
){
	private val _activityList = MutableStateFlow<List<Activity>>(emptyList())
	val activityList: StateFlow<List<Activity>> = _activityList.asStateFlow()
	fun search(request: Request) {
		remoteDataSource.search(request, object : RemoteDataSource.LoadCallback {
			override fun onLoaded(activities: List<Activity>) {
				_activityList.value = activities
				Log.d("testt", "onLoaded: ${activities[0].actTitle}")
			}

			override fun onFailed() {
				_activityList.value = emptyList()
			}

		})
	}
}