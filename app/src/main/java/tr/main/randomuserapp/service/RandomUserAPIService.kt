package tr.main.randomuserapp.service

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tr.main.randomuserapp.api.RandomUserAPI
import tr.main.randomuserapp.constant.Constants
import tr.main.randomuserapp.model.RandomUsers

object RandomUserAPIService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(RandomUserAPI::class.java)

    fun getUsersData(): Call<RandomUsers> {
        return api.getUsersData()
    }

}