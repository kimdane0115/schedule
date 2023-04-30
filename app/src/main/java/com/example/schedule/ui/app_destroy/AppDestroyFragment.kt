package com.example.schedule.ui.app_destroy

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.schedule.databinding.FragmentAppdestroyBinding

class AppDestroyFragment : Fragment() {

    private var _binding: FragmentAppdestroyBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAppdestroyBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("앱 종료")
        builder.setMessage("앱을 종료하시겠습니까?")
        builder.setPositiveButton("확인") { _, _ ->
            // 확인 버튼을 누르면 앱을 종료합니다.
            activity?.finish()
        }
        builder.setNegativeButton("취소", null)
        // 다이얼로그를 표시합니다.
        builder.show()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}