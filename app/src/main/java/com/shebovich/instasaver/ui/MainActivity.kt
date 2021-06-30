package com.shebovich.instasaver.ui

import android.content.ClipboardManager
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.shebovich.instasaver.R
import com.shebovich.instasaver.databinding.ActivityMainBinding
import com.shebovich.instasaver.instagramauth.AuthenticationDialog
import com.shebovich.instasaver.instagramauth.AuthenticationListener
import com.shebovich.instasaver.ui.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AuthenticationListener {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        printKeyHash()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavView.setupWithNavController(navController)

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        val clipBoard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipUrl = clipBoard.primaryClip?.getItemAt(0)?.text.toString()
        if (clipUrl.startsWith("https://www.instagram.com")) {
            println("RESULT clipUrl")
            viewModel.parseMediaInstagram(clipUrl)

        }
        clipBoard.addPrimaryClipChangedListener {
            val clipData = clipBoard.primaryClip
            val item = clipData?.getItemAt(0)
            val clipUrl = item?.text.toString()
            println("clipUrl $clipUrl")

        }
    }


    override fun onResume() {
        super.onResume()
        checkImageUrl()
    }

    private fun openInstagram() {
        val authenticationDialog = AuthenticationDialog(this, this)
        authenticationDialog.setCancelable(true)
        authenticationDialog.show()
    }


    private fun checkImageUrl() {

        //val textToPaste = clipBoard.primaryClip?.getItemAt(0)?.text

    }







    private fun printKeyHash() {
        try {
            val info = packageManager
                .getPackageInfo(
                    "com.shebovich.instasaver",
                    PackageManager.GET_SIGNATURES
                )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("TAG", "KeyHash: " + Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }

    override fun onTokenReceived(auth_token: String?) {
        println("token = $auth_token")
    }
}