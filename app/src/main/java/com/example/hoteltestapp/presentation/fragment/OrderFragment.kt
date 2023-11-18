package com.example.hoteltestapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.hoteltestapp.R
import com.example.hoteltestapp.databinding.FragmentOrderBinding
import com.example.hoteltestapp.util.BaseFragment
import com.example.hoteltestapp.util.extension.navigate
import com.example.hoteltestapp.util.extension.navigateBack

class OrderFragment: BaseFragment<FragmentOrderBinding>(R.layout.fragment_order) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            backButton.setOnClickListener { it.navigateBack() }
            nextButton.setOnClickListener {
                it.navigate(OrderFragmentDirections.actionOrderFragmentToStartFragment())
            }
        }
    }

}