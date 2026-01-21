package br.com.teya.challenge.network.retrofit.adapter

import br.com.teya.challenge.common.network.RemoteDataSourceNetworkException
import br.com.teya.challenge.common.network.RemoteDataSourceServerException
import br.com.teya.challenge.common.network.RemoteDataSourceUnknownException
import kotlinx.coroutines.TimeoutCancellationException
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

internal class RemoteResultCall<R>(
    private val delegate: Call<R>,
    private val responseType: Type,
) : Call<Result<R>> {
    override fun enqueue(callback: Callback<Result<R>>) = delegate.enqueue(
        object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@RemoteResultCall, Response.success(response.toRemoteDataSourceResult()))
            }

            private fun Response<R>.toRemoteDataSourceResult(): Result<R> {
                if (isSuccessful) {
                    return Result.success( body()!!)
                }

                return Result.failure(
                    RemoteDataSourceServerException(
                        message = "Server error code: ${this.code()} error body: ${this.errorBody()?.string()}"
                    )
                )
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val result = when(throwable){
                    is UnknownHostException,
                    is TimeoutException,
                    is TimeoutCancellationException,
                    is IOException -> Result.failure<R>(RemoteDataSourceNetworkException(throwable))
                    else -> Result.failure<R>(RemoteDataSourceUnknownException(throwable))
                }
                callback.onResponse(this@RemoteResultCall, Response.success(result))
            }
        }
    )

    override fun execute(): Response<Result<R>> {
        throw UnsupportedOperationException("RemoteResultCall doesn't support execute")
    }
    override fun clone(): Call<Result<R>> = RemoteResultCall(delegate.clone(), responseType)
    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = delegate.timeout()
    override fun isExecuted(): Boolean = delegate.isExecuted
    override fun isCanceled(): Boolean = delegate.isCanceled
    override fun cancel() = delegate.cancel()
}