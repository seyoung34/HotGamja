package com.example.potatoservice.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.potatoservice.R
import com.example.potatoservice.databinding.ActivityDetailBinding
import com.example.potatoservice.ui.share.Volunteer

class DetailActivity : AppCompatActivity() {
	//봉사 활동 id
	private var id = 0
	private lateinit var binding: ActivityDetailBinding
	var institution = ""
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		//인텐트로 id 받아옴
		id = intent.getIntExtra("id", 0)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
		binding.volunteer = getVolunteer(id)
		setProgress()
	}
	//받아온 id로 봉사 활동 데이터 얻음
	//테스트용 데이터
	private fun getVolunteer(id: Int):Volunteer {
		institution = "조직111111111111111111끝"
		return Volunteer("봉사 활동 제목 12345678912345678911111111끝",
			"조직11111111111111111111111끝", "category111111111111111111끝",
			"recruitmentPeriod",
			"recruitmentNumber11111111111111111111111111끝", "time",
			"serviceRecognitionTime", "location", "확정"
		)
	}

	private fun setProgress(){
		binding.progressBar1.progress = 50
		binding.progressBar2.progress = 50
		binding.progressBar3.progress = 50
	}
}