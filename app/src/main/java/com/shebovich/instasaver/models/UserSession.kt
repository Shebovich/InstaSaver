package com.shebovich.instasaver.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserSession(val access_token : String,
                       val user_id : String) {


}