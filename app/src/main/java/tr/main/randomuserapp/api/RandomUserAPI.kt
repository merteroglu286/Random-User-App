package tr.main.randomuserapp.api

import retrofit2.Call
import retrofit2.http.GET
import tr.main.randomuserapp.constant.Constants
import tr.main.randomuserapp.model.RandomUsers

interface RandomUserAPI {

    @GET(Constants.EXT)
    fun getUsersData(): Call<RandomUsers>

}