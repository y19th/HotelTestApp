package com.example.hoteltestapp.domain.repository

import com.example.hoteltestapp.data.api.HotelApi
import com.example.hoteltestapp.data.model.HotelInfoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotelRepository : BaseRepository() {
    fun fetchHotelInfo(
        onResult: (HotelInfoResponse?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        builder.instance(HotelApi::class.java).getHotelInfo().enqueue(
            object: Callback<HotelInfoResponse> {
                override fun onResponse(
                    call: Call<HotelInfoResponse>,
                    response: Response<HotelInfoResponse>
                ) {
                    onResult.invoke(response.body())
                }

                override fun onFailure(call: Call<HotelInfoResponse>, t: Throwable) {
                    onError.invoke(t)
                }

            }
        )
    }
}