package com.shebovich.instasaver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shebovich.instasaver.databinding.ActivityHashtagInfoBinding

class FragmentHashTagInfo : Fragment() {
    lateinit var binding : ActivityHashtagInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityHashtagInfoBinding.inflate(layoutInflater)
        return binding.root
    }

}