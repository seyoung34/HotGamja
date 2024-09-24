package com.example.potatoservice.ui.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.potatoservice.databinding.FragmentMypageBinding

class MyPageFragment : Fragment() {

    private  lateinit var binding: FragmentMypageBinding
//    private  lateinit var voluntieerAdapter :
    private  lateinit var  mypageViewModel : MyPageViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mypageViewModel = ViewModelProvider(this).get(MyPageViewModel::class.java)

        binding = FragmentMypageBinding.inflate(inflater, container, false)

        //경험치 프로그레스 바
        mypageViewModel.progress.observe(viewLifecycleOwner) { progress ->
            binding.progressBar.progress = progress
        }

        //리사이클러뷰 Adapter



        return super.onCreateView(inflater, container, savedInstanceState)
    }
}
