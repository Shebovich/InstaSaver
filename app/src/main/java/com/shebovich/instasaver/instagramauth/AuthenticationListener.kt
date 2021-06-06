package com.shebovich.instasaver.instagramauth

interface AuthenticationListener {
    fun onTokenReceived(auth_token: String?)
}