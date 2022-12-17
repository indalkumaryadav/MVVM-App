package com.example.myapp1.utils

import android.content.Context
import com.example.myapp1.constants.Constants

class TokenManager (private val context:Context){

    private val prefs = context.getSharedPreferences(Constants.PREFS_TOKEN_FILE,Context.MODE_PRIVATE)

    fun saveToken(key:String,token:String):Boolean{
        try {
            val editor = prefs.edit()
            editor.putString(key,token)
            editor.apply()
            return true
        }catch (e:Exception){
            return false
        }
    }

    fun getToken(key: String): String? {
        try {
            val data = prefs.getString(key,"")
            
            if (data != null && data.isNotEmpty()){
                return data
            }
            return null
        }catch (e:Exception){
            return null
        }

    }

    fun deleteToken(key: String):Boolean{
        try {
            prefs.edit().remove(key).apply()
            return true
        }catch (ex:Exception){
            return false
        }
    }
}