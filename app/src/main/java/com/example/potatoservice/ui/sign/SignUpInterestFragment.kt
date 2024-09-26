package com.example.potatoservice.ui.sign

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.potatoservice.databinding.FragmentSignupInterestBinding

class SignUpInterestFragment : Fragment() {

    private var _binding: FragmentSignupInterestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupInterestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이미지 카드 클릭 리스너
        binding.educationCard.setOnClickListener {
            showToast("교육/멘토링 선택됨")
        }

        binding.supportCard.setOnClickListener {
            showToast("생활편의지원 선택됨")
        }

        binding.culturalEventCard.setOnClickListener {
            showToast("문화행사 선택됨")
        }

        binding.medicalCard.setOnClickListener {
            showToast("보건의료 선택됨")
        }

        binding.safetyCard.setOnClickListener {
            showToast("안전 예방 선택됨")
        }

        binding.internationalEventCard.setOnClickListener {
            showToast("국제행사 선택됨")
        }

        // 모두 골랐어요 버튼 클릭 리스너
        binding.completeButton.setOnClickListener {
            showToast("모두 골랐어요")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}