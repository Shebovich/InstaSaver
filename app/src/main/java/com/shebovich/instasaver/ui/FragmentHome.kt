package com.shebovich.instasaver.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.shebovich.instasaver.R
import com.shebovich.instasaver.databinding.ActivityHomeBinding
import com.google.android.material.snackbar.Snackbar

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import com.shebovich.instasaver.ui.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentHome : Fragment(), IFragment {
    lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rippleBackground.startRippleAnimation()
        binding.centerImage.startAnimation(AnimationUtils.loadAnimation(requireActivity(),
            R.anim.pulse
        ))
        binding.closeDialog.setOnClickListener {
            slideDown(binding.helpDialogConstraint)
        }
        binding.instIcon.setOnClickListener{
            findNavController().navigate(R.id.action_home_fragment_to_fragmentInstagramLogin)
        }
        checkImageUrl()
    }

    fun slideUp(view: View) {
        view.visibility = View.VISIBLE
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            view.height.toFloat(),  // fromYDelta
            0F
        ) // toYDelta
        animate.duration = 500
        animate.fillAfter = true
        view.startAnimation(animate)
    }

    // slide the view from its current position to below itself
    fun slideDown(view: View) {
        val animate = TranslateAnimation(
            0F,  // fromXDelta
            0F,  // toXDelta
            0F,  // fromYDelta
            view.height.toFloat()
        ) // toYDelta
        animate.duration = 200
        animate.fillAfter = true
        animate.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                view.visibility = View.GONE
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        view.startAnimation(animate)

    }
    private fun checkImageUrl() {
        val clipBoard = requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val textToPaste = clipBoard.primaryClip?.getItemAt(0)?.text
        println("textToPaste $textToPaste")
        clipBoard.addPrimaryClipChangedListener {
            val clipData = clipBoard.primaryClip
            val item = clipData?.getItemAt(0)
            val clipUrl = item?.text.toString()

            if (clipUrl.startsWith("https://www.instagram.com")) {
                println("RESULT clipUrl")
                viewModel.parseMediaInstagram(clipUrl)

            }
        }
    }

    override fun initNavigation() {

    }
}