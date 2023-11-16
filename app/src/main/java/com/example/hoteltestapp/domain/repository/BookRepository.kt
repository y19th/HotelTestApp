package com.example.hoteltestapp.domain.repository

import com.example.hoteltestapp.data.api.BookApi
import com.example.hoteltestapp.data.model.TourInfoResponse
import org.koin.core.component.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookRepository: BaseRepository() {

    fun fetchTourInfo(
        onResult: (TourInfoResponse?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        builder.instance(BookApi::class.java).fetchTourInfo().enqueue(
            object: Callback<TourInfoResponse> {
                override fun onResponse(
                    call: Call<TourInfoResponse>,
                    response: Response<TourInfoResponse>
                ) {
                    onResult.invoke(response.body())
                }

                override fun onFailure(call: Call<TourInfoResponse>, t: Throwable) {
                    onError.invoke(t)
                }

            }
        )
    }
}