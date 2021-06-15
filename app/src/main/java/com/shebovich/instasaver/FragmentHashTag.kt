package com.shebovich.instasaver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shebovich.instasaver.databinding.ActivityHashtagBinding

class FragmentHashTag : Fragment() {
    lateinit var binding: ActivityHashtagBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = ActivityHashtagBinding.inflate(inflater)
        return binding.root
    }
}