package com.kabe.quizapp.data.base

import android.content.Context
import com.kabe.quizapp.util.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

object RetrofitBuilder {

    class NoInternetInterceptor(private val context: Context) : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            return if (!NetworkUtil.isNetworkAvailable(context)) {
                throw NoInternetException()
            } else {
                val builder = chain.request().newBuilder()
                chain.proceed(builder.build())
            }
        }

        inner class NoInternetException() : IOException("No internet connection")

    }

}