package com.example.potatoservice.ui.mypage

import android.app.Dialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.potatoservice.databinding.DialogCustomBinding

class CustomDialogFragment : DialogFragment() {

    private lateinit var binding : DialogCustomBinding

    companion object {
        private const val ARG_TITLE = "title"
        private const val ARG_IMAGE = "image"
        private const val ARG_CONTENT = "content"

        // newInstance 메서드로 매개변수 전달
        fun newInstance(title: String, image: Int, content: String): CustomDialogFragment {
            val fragment = CustomDialogFragment()
            val args = Bundle()
            args.putString(ARG_TITLE, title)
            args.putInt(ARG_IMAGE, image)
            args.putString(ARG_CONTENT, content)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogCustomBinding.inflate(inflater,container,false)
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))   //모서리 투명하게, 없어도 되는 것 같기도..
//        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE) //다이얼로그의 타이틀바 제거 //없어도 되는 것 같기도..

        // 매개변수로 전달받은 값 사용
        val title = arguments?.getString(ARG_TITLE)
        val imageResId = arguments?.getInt(ARG_IMAGE)
        val content = arguments?.getString(ARG_CONTENT)

        // 전달받은 데이터를 다이얼로그 UI에 설정
        binding.customDialogTitle.text = title
        binding.customDialogImage.setImageResource(imageResId ?: 0)
        binding.customDialogContent.text = content

        // 닫기 버튼 이벤트 처리
        binding.customDialogClose.setOnClickListener {
            dismiss()
        }

        // 긍정 버튼 이벤트 처리
        binding.customDialogPositive.setOnClickListener {
            dismiss()
        }

        // 부정 버튼 이벤트 처리
        binding.customDialogNegative.setOnClickListener {
            dismiss()
        }

        binding.customDialogTitle

        return binding.root
    }

    // 사이즈 조절
    override fun onStart() {
        super.onStart()

        // 화면 크기 가져오기
        val displayMetrics = Resources.getSystem().displayMetrics
        val screenWidth = displayMetrics.widthPixels
        val screenHeight = displayMetrics.heightPixels

        // 다이얼로그의 크기를 화면 비율로 설정 (예: 너비 80%, 높이 50%)
        val dialogWidth = (screenWidth * 0.8).toInt()  // 너비 80%
        val dialogHeight = (screenHeight * 0.5).toInt() // 높이 50%

        // 다이얼로그 크기 설정
        dialog?.window?.setLayout(dialogWidth, dialogHeight)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }



//    override fun onResume() {
//        super.onResume()
//        // full Screen code
//        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
//    }
}
