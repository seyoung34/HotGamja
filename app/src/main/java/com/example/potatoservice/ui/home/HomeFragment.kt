package com.example.potatoservice.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.potatoservice.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), AdapterCallback {

	private lateinit var binding: FragmentHomeBinding
	private lateinit var searchResultAdapter: SearchResultAdapter
	private lateinit var spinnerAdapter: SpinnerAdapter
	private lateinit var homeViewModel: HomeViewModel
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

		binding = FragmentHomeBinding.inflate(inflater, container, false)
		binding.viewModel = homeViewModel
		setRecyclerAdapter()
		return binding.root
	}

	private fun setRecyclerAdapter(){
		binding.searchResultRecyclerView.layoutManager = LinearLayoutManager(activity)
		binding.spinnerRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
		searchResultAdapter = SearchResultAdapter(this)
		homeViewModel.serviceList.observe(viewLifecycleOwner, Observer { service ->
			searchResultAdapter.submitList(service)
			binding.searchResultRecyclerView.adapter = searchResultAdapter
		})
		val spinnerItems = listOf(
			listOf("지역 대분류", "경기", "대구", "서울"),
			listOf("지역 소분류", "남양주", "춘천", "북구"),
			listOf("봉사 분야", "생활지원 및 주거환경 개선", "교육 및 멘토링", "행정 및 사무지원",
				"문화, 환경 및 국제협력 활동", "보건의료 및 공익활동", "상담 및 자원봉사 교육", "기타 활동"))

		spinnerAdapter = SpinnerAdapter(spinnerItems)
		binding.spinnerRecyclerView.adapter = spinnerAdapter
	}

	override fun onClicked(service: Service) {
		//봉사 카드 클릭 시 상세 페이지로 이동 기능 추가 예정.
	}

}