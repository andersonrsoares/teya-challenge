package br.com.teya.challenge.network.retrofit.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class RemoteResultCallAdapter<T>(
    private val responseType: Type
) : CallAdapter<T, Any> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<T>): Any {
        return RemoteResultCall(call, responseType)
    }
}