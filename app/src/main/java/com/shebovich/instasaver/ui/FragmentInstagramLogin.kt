package com.shebovich.instasaver.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shebovich.instasaver.R
import com.shebovich.instasaver.databinding.ActivityLoginInstagramBinding
import com.shebovich.instasaver.other.Constants
import com.shebovich.instasaver.ui.viewModels.LoginInstagramViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentInstagramLogin : Fragment() {
    val viewModel: LoginInstagramViewModel  by viewModels()
    lateinit var binding: ActivityLoginInstagramBinding
    lateinit var request_url : String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityLoginInstagramBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
        initObservables()
    }
    fun goToMainFragment(boolean: Boolean) {
        findNavController().navigate(R.id.action_fragmentInstagramLogin_to_home_fragment)
    }

    private fun initObservables() {

        viewModel.onFinishedRegistrationLiveData.observe(requireActivity(), this::goToMainFragment)
    }

    private fun initWebView() {
        val redirect_url = requireContext().resources.getString(R.string.callback_url)
        request_url = requireContext().resources.getString(R.string.base_url) +
                "oauth/authorize/?client_id=" +
                requireContext().resources.getString(R.string.client_id) +
                "&redirect_uri=" + redirect_url +
                "&scope=user_profile,user_media&response_type=code"
        binding.webView.apply {
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
            loadUrl(request_url)
            webViewClient = webClient
        }
    }

    var webClient: WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.startsWith(Constants.REDIRECT_URL)) {
               findNavController().navigate(R.id.action_fragmentInstagramLogin_to_home_fragment)
                return true
            }
            return false
        }
        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            viewModel.onPageFinished(url)

        }
    }




}