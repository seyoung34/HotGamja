package com.example.potatoservice.ui.home

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.potatoservice.databinding.FragmentHomeBinding
import com.example.potatoservice.ui.detail.DetailActivity
import com.example.potatoservice.ui.share.AdapterCallback
import com.example.potatoservice.ui.share.SpinnerHintAdapter
import com.example.potatoservice.ui.share.Volunteer

class HomeFragment : Fragment(), AdapterCallback {


	private lateinit var binding: FragmentHomeBinding
	private lateinit var searchResultAdapter: SearchResultAdapter
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

		setSpinner()
		return binding.root
	}
	//검색 결과 리사이클러뷰 설정
	private fun setRecyclerAdapter(){
		binding.searchResultRecyclerView.layoutManager = LinearLayoutManager(activity)
		searchResultAdapter = SearchResultAdapter(this)
		homeViewModel.serviceList.observe(viewLifecycleOwner, Observer { service ->
			searchResultAdapter.submitList(service)
			binding.searchResultRecyclerView.adapter = searchResultAdapter
		})
	}
	//필터들 설정
	private fun setSpinner(){
		binding.sort.adapter = ArrayAdapter(
			requireContext(), R.layout.simple_spinner_dropdown_item, homeViewModel.sortList)
		//정렬 선택 시
		binding.sort.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {

			}

			override fun onNothingSelected(parent: AdapterView<*>?) {

			}

		}
		binding.majorRegionalCategories.adapter = SpinnerHintAdapter(
			requireContext(), R.layout.simple_spinner_dropdown_item, homeViewModel.majorRegoinList)
		//지역 대분류 선택 시
		binding.majorRegionalCategories.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {
				//지역 대분류 선택에 따라 소분류 목록이 바뀜
				binding.minorRegionalCategories.adapter = SpinnerHintAdapter(
					requireContext(), R.layout.simple_spinner_dropdown_item, homeViewModel.minorRegoinList[position])
			}

			override fun onNothingSelected(parent: AdapterView<*>?) {

			}

		}
		binding.minorRegionalCategories.adapter = SpinnerHintAdapter(
			requireContext(), R.layout.simple_spinner_dropdown_item, homeViewModel.minorRegoinList[0])
		//지역 소분류 선택 시
		binding.minorRegionalCategories.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {

			}

			override fun onNothingSelected(parent: AdapterView<*>?) {

			}

		}
		binding.volunteerActivitiesCategories.adapter = SpinnerHintAdapter(
			requireContext(), R.layout.simple_spinner_dropdown_item, homeViewModel.volunteerList)
		//봉사 분야 선택 시
		binding.volunteerActivitiesCategories.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {

			}

			override fun onNothingSelected(parent: AdapterView<*>?) {

			}

		}
		binding.ageCategories.adapter = SpinnerHintAdapter(
			requireContext(), R.layout.simple_spinner_dropdown_item, homeViewModel.ageList)
		//나이 제한 선택 시
		binding.ageCategories.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
			override fun onItemSelected(
				parent: AdapterView<*>?,
				view: View?,
				position: Int,
				id: Long
			) {

			}

			override fun onNothingSelected(parent: AdapterView<*>?) {

			}

		}
	}

	override fun onClicked(id: Int) {
		//봉사 카드 클릭 시 상세 페이지로 이동 기능 추가 예정.
		val intent = Intent(requireContext(), DetailActivity::class.java)
		intent.putExtra("id", id) // 데이터 추가
		startActivity(intent)
	}


}