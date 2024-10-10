package com.example.potatoservice.ui.home

import android.util.Log
import com.example.potatoservice.model.APIService
import com.example.potatoservice.model.remote.Activity
import com.example.potatoservice.model.remote.ActivityResponse
import com.example.potatoservice.ui.share.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
//레트로핏으로 데이터 받아 오는 클래스
class RemoteDataSource @Inject constructor(private val apiService: APIService){
	interface LoadCallback{
		fun onLoaded(activities: List<Activity>)
		fun onFailed()
	}

	fun search(request: Request, callback: LoadCallback){
		val page = request.page
		val size:Int? = request.size
		val sort:String? = request.sort
		val beforeDeadlineOnly: Boolean? = request.beforeDeadlineOnly
		val teenPossibleOnly: Boolean? = request.teenPossibleOnly
		val category: String? = request.category
		apiService.getActivities(page, size, sort, beforeDeadlineOnly, teenPossibleOnly, category).enqueue(
			object : Callback<ActivityResponse>{
				override fun onResponse(
					call: Call<ActivityResponse>,
					response: Response<ActivityResponse>
				) {
					if (response.isSuccessful){
						val activityList = response.body()?.content?.map { content ->
							Activity(
								content.actId,
								content.actTitle,
								content.actLocation,
								content.noticeStartDate,
								content.noticeEndDate,
								content.actStartDate,
								content.actEndDate,
								content.actStartTime,
								content.actEndTime,
								content.recruitTotalNum,
								changeCategory(content.category)
							)
						}?: emptyList()
						callback.onLoaded(activityList)
					} else{
						Log.d("testt", "onResponse")
						callback.onFailed()
					}
				}

				override fun onFailure(call: Call<ActivityResponse>, t: Throwable) {
					Log.d("testt", "onFailure")
					callback.onFailed()
				}

			}
		)
	}

	private fun changeCategory(originalCategory: String): String{
		return when(originalCategory){
			"LIFE_SUPPORT_AND_HOUSING_IMPROVEMENT" -> "생활지원 및 주거환경 개선"
			"EDUCATION_AND_MENTORING" -> "교육 및 멘토링"
			"ADMINISTRATIVE_AND_OFFICE_SUPPORT" -> "행정 및 사무지원"
			"CULTURE_ENVIRONMENT_AND_INTERNATIONAL_COOPERATION" -> "문화, 환경 및 국제협력 활동"
			"HEALTHCARE_AND_PUBLIC_WELFARE" -> "보건의료 및 공익활동"
			"COUNSELING_AND_VOLUNTEER_TRAINING" -> "상담 및 자원봉사 교육"
			"OTHER_ACTIVITIES" -> "기타 활동"
			else -> "봉사 활동 분류 오류"
		}
	}
}