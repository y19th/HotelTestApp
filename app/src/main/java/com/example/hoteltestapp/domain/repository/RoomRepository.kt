package com.example.hoteltestapp.domain.repository

import com.example.hoteltestapp.RetrofitBuilder
import com.example.hoteltestapp.data.api.RoomApi
import com.example.hoteltestapp.data.model.RoomsResponse
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomRepository: KoinComponent {
    private val builder: RetrofitBuilder by inject()

    fun fetchRooms(
        onResult: (RoomsResponse?) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        builder.instance(RoomApi::class.java).getRooms().enqueue(
            object: Callback<RoomsResponse> {
                override fun onResponse(
                    call: Call<RoomsResponse>,
                    response: Response<RoomsResponse>
                ) {
                    onResult.invoke(response.body())
                }

                override fun onFailure(call: Call<RoomsResponse>, t: Throwable) {
                    onError.invoke(t)
                }
            }
        )
    }
}