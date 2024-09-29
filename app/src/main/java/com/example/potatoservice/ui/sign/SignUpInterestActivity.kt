package com.example.potatoservice.ui.sign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.potatoservice.MainActivity
import com.example.potatoservice.R
import com.example.potatoservice.databinding.ActivitySignupInterestBinding
import com.example.potatoservice.model.RetrofitClient
import com.example.potatoservice.model.remote.UserInterest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpInterestActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupInterestBinding
    private val selectedInterests = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupInterestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.educationCard.setOnClickListener { toggleInterest("교육/멘토링") }
        binding.supportCard.setOnClickListener { toggleInterest("생활편의지원") }
        binding.culturalEventCard.setOnClickListener { toggleInterest("문화행사") }
        binding.medicalCard.setOnClickListener { toggleInterest("보건의료") }
        binding.safetyCard.setOnClickListener { toggleInterest("안전예방") }
        binding.internationalEventCard.setOnClickListener { toggleInterest("국제행사") }
        binding.completeButton.setOnClickListener { sendUserInterests() }
    }

    private fun toggleInterest(interest: String) {
        val cardView = when (interest) {
            "교육/멘토링" -> binding.educationCard
            "생활편의지원" -> binding.supportCard
            "문화행사" -> binding.culturalEventCard
            "보건의료" -> binding.medicalCard
            "안전예방" -> binding.safetyCard
            "국제행사" -> binding.internationalEventCard
            else -> null
        }

        if (cardView != null) {
            if (selectedInterests.contains(interest)) {
                selectedInterests.remove(interest)
                cardView.isSelected = false // Set to unselected
            } else {
                selectedInterests.add(interest)
                cardView.isSelected = true // Set to selected
            }
        }
    }


    private fun sendUserInterests() {
        val userInterest = UserInterest(selectedInterests)
        Log.d("testt", userInterest.toString())

        RetrofitClient.apiService.sendUserInterest(userInterest).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                if (response.isSuccessful) {
                    // 서버에서 받은 응답 문자열 출력
                    val responseString = response.body()?.toString()
                    Log.d("testt", "Success Response: $responseString")
                    showToast("관심사 전송 성공: $responseString")
                    goToMainActivity() // MainActivity로 이동
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.d("testt", "Failure Response: $errorBody")
                    showToast("관심사 전송 실패: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("testt", "Request Failed: ${t.message}")
                showToast("서버 오류: ${t.message}")
            }
        })
    }


    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // 현재 액티비티 종료
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
