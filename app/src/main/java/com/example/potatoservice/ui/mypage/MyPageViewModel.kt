package com.example.potatoservice.ui.mypage

import android.content.Context
import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.potatoservice.R

class MyPageViewModel(private val context: Context, private val myPageModel: MyPageModel) : ViewModel() {
    private val _volunteerHours = MutableLiveData<Int>()
    val volunteerHours: LiveData<Int> get() = _volunteerHours

    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> get() = _progress

    private val _vmDialogArray: Array<DialogModel> = myPageModel.dialogArray
    val vmDialogArray: Array<DialogModel> get() = _vmDialogArray

    private val vmSpinnerItems: Array<String> = myPageModel.spinnerItems

    lateinit var vmAdapter: ArrayAdapter<String>

    init {
        vmAdapter = ArrayAdapter(context, R.layout.item_spinner, vmSpinnerItems)
        vmAdapter.setDropDownViewResource(R.layout.item_spinner_dropdown)
    }

    fun setVolunteerHours(hours: Int) {
        _volunteerHours.value = hours
        calculateProgress(hours)
    }

    private fun calculateProgress(hours: Int) {
        val maxHours = 200 // 최대 시간 예시
        val progressValue = (hours * 100) / maxHours
        _progress.value = progressValue
    }
}
