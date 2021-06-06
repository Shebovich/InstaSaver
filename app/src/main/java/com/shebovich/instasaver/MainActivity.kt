package com.shebovich.instasaver

import android.content.ClipboardManager
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.shebovich.instasaver.databinding.ActivityMainBinding
import com.shebovich.instasaver.instagramauth.AuthenticationDialog
import com.shebovich.instasaver.instagramauth.AuthenticationListener
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class MainActivity : AppCompatActivity(), AuthenticationListener {
    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    val fragmentsMap = mapOf(R.id.ic_repost to R.id.action_fragmentHome_to_fragmentRepost)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        printKeyHash()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        initView()
        checkImageUrl()
    }

    private fun initView() {
        binding.bubbleTabBar.addBubbleListener {
            fragmentsMap[it]?.let { it1 -> navController.navigate(it1) }
        }
    }

    override fun onResume() {
        super.onResume()

    }

    private fun openInstagram() {
        val authenticationDialog = AuthenticationDialog(this, this)
        authenticationDialog.setCancelable(true)
        authenticationDialog.show()
    }

    private fun checkImageUrl() {
        val clipBoard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        clipBoard.addPrimaryClipChangedListener {
            val clipData = clipBoard.primaryClip
            val item = clipData!!.getItemAt(0)
            val text = item.text.toString()

        }

        // Access your context here using YourActivityName.this
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