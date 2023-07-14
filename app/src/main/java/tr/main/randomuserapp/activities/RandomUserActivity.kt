package tr.main.randomuserapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch
import tr.main.randomuserapp.RandomUserApp
import tr.main.randomuserapp.databinding.ActivityRandomUserBinding
import tr.main.randomuserapp.model.UserEntity
import tr.main.randomuserapp.service.UserHistoryDao
import tr.main.randomuserapp.viewmodel.RandomUserViewModel
import java.io.IOException

class RandomUserActivity : AppCompatActivity() {

    private var binding: ActivityRandomUserBinding? = null
    private lateinit var randomUserViewModel: RandomUserViewModel

    private lateinit var username : String
    private lateinit var userEmail : String
    private lateinit var userGender : String
    private lateinit var userAge : String
    private lateinit var userPhone : String
    private lateinit var userImage : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomUserBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        getRandomUserData()

        binding?.regenerateUserBtn?.setOnClickListener {
            getRandomUserData()
        }

        val dao = (application as RandomUserApp).db.userHistoryDao()

        binding?.saveUserBtn?.setOnClickListener {
            try {
                addUserToDatabase(dao)
            }catch (e: IOException){
                e.stackTrace
            }
        }
    }

    private fun getRandomUserData(){
        randomUserViewModel = ViewModelProvider(this).get(RandomUserViewModel::class.java)
        randomUserViewModel.fetchDataFromAPI()

        randomUserViewModel.usersData.observe(this, Observer {
            val user = it.results[0]

            username = user.name.first +" "+ user.name.last
            userEmail = user.email
            userAge = user.dob.age.toString()
            userPhone = user.phone
            userGender = user.gender
            userImage = user.picture.large

            binding?.txtName?.text = username
            binding?.txtEmail?.text = userEmail
            binding?.txtGender?.text = userGender
            binding?.txtAge?.text = userAge
            binding?.txtPhone?.text = userPhone

            Glide.with(this@RandomUserActivity).load(userImage).into(binding?.imageview!!)


        })

        randomUserViewModel.errorMessage.observe(this, Observer {
            Log.i("TAG_USERS",it)
        })
    }

    private fun addUserToDatabase(userHistoryDao: UserHistoryDao){

        lifecycleScope.launch {
            userHistoryDao.insert(UserEntity(name=username, email = userEmail, gender = userGender, phone = userPhone, imageUrl = userImage))
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}