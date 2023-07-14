package tr.main.randomuserapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tr.main.randomuserapp.model.RandomUsers
import tr.main.randomuserapp.model.Result
import tr.main.randomuserapp.service.RandomUserAPIService


class RandomUserViewModel : ViewModel() {

    val usersData: MutableLiveData<RandomUsers> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun fetchDataFromAPI() {
        RandomUserAPIService.getUsersData().enqueue(object : Callback<RandomUsers> {
            override fun onResponse(call: Call<RandomUsers>, response: Response<RandomUsers>) {
                if (response.isSuccessful) {
                    val data = response.body()
                    usersData.value = data
                } else {
                    errorMessage.value = "Error: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<RandomUsers>, t: Throwable) {
                errorMessage.value = "Error: ${t.message}"
            }
        })
    }
}