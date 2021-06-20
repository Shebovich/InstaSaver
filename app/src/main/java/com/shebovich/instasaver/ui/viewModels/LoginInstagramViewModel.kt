package com.shebovich.instasaver.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shebovich.instasaver.db.SessionManager
import com.shebovich.instasaver.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.URLDecoder
import javax.inject.Inject

@HiltViewModel
class LoginInstagramViewModel @Inject constructor(
    val sessionManager: SessionManager,
    val loginRepository: LoginRepository
) : ViewModel() {
    val isShowDialogLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val onFinishedRegistrationLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData() }
    public fun isShowHelpDialog() {
        isShowDialogLiveData.postValue(sessionManager.isShowHelp())
    }

    public fun setShowHelpDialog(isShow: Boolean) {
        sessionManager.setShowDialog(isShow)
    }

    fun onPageFinished(url: String) {
        if (url.contains("code%3")) {
            val afterDecode: String = URLDecoder.decode(url, "UTF-8")
            var access_token = afterDecode?.substring(afterDecode.lastIndexOf("code=") + 5)
            access_token = access_token.substring(0, access_token.length - 2);
            println("acces $access_token")
            sessionManager.setCode(access_token!!)
            loginUser()
           // onFinishedRegistrationLiveData.postValue(true)

        } else if (url.contains("?error")) {
            Log.e("access_token", "getting error fetching access token")
        }
    }

    private fun loginUser() {
        loginRepository.authUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                print("userID "+it.user_id)
                sessionManager.setUserId(it.user_id)
                sessionManager.setAccesToken(it.access_token)
            }, {it.printStackTrace()
            })
    }
}