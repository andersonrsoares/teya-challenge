package br.com.teya.challenge.network.retrofit.adapter

import br.com.teya.challenge.network.result.RemoteDataSourceError
import br.com.teya.challenge.network.result.RemoteDataSourceResult
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

internal class RemoteResultCall<R>(
    private val delegate: Call<R>,
    private val responseType: Type,
) : Call<RemoteDataSourceResult<R>> {
    override fun enqueue(callback: Callback<RemoteDataSourceResult<R>>) = delegate.enqueue(
        object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@RemoteResultCall, Response.success(response.toRemoteDataSourceResult()))
            }

            private fun Response<R>.toRemoteDataSourceResult(): RemoteDataSourceResult<R> {
                if (isSuccessful) {
                    return RemoteDataSourceResult.Success( body()!!)
                }

                return RemoteDataSourceResult.Error(
                    RemoteDataSourceError.ServerError(
                        Throwable("Server error code: ${this.code()} error body: ${this.errorBody()?.string()}"), this.code()
                    )
                )
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                val result =  RemoteDataSourceResult.Error<R>(
                    RemoteDataSourceError.NetworkError(throwable)
                )
                callback.onResponse(this@RemoteResultCall, Response.success(result))
            }
        }
    )

    override fun execute(): Response<RemoteDataSourceResult<R>> {
        throw UnsupportedOperationException("RemoteResultCall doesn't support execute")
    }
    override fun clone(): Call<RemoteDataSourceResult<R>> = RemoteResultCall(delegate.clone(), responseType)
    override fun request(): Request = delegate.request()
    override fun timeout(): Timeout = delegate.timeout()
    override fun isExecuted(): Boolean = delegate.isExecuted
    override fun isCanceled(): Boolean = delegate.isCanceled
    override fun cancel() = delegate.cancel()
}