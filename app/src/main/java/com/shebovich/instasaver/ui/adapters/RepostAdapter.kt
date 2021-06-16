package com.shebovich.instasaver.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shebovich.instasaver.models.Profile
import com.shebovich.instasaver.databinding.RepostRawBinding

class RepostAdapter(private val profileList: List<Profile>) : RecyclerView.Adapter<RepostAdapter.ViewHolder>() {
    lateinit var binding: RepostRawBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RepostRawBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }



    override fun getItemCount(): Int = profileList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }
    inner class ViewHolder(val binding: RepostRawBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}


