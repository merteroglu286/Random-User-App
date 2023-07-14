package tr.main.randomuserapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import tr.main.randomuserapp.R
import tr.main.randomuserapp.RandomUserApp
import tr.main.randomuserapp.adapter.UserHistoryAdapter
import tr.main.randomuserapp.databinding.ActivityRandomUserBinding
import tr.main.randomuserapp.databinding.ActivityUserHistoryBinding
import tr.main.randomuserapp.model.UserEntity
import tr.main.randomuserapp.service.UserHistoryDao

class UserHistoryActivity : AppCompatActivity() {

    private var binding: ActivityUserHistoryBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserHistoryBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val userHistoryDao = (application as RandomUserApp).db.userHistoryDao()

        lifecycleScope.launch {
            userHistoryDao.fetchAllEmployee().collect {
                val list = ArrayList(it)
                setupListOfDataIntoRecyclerView(list,userHistoryDao)
            }
        }

    }

    private fun setupListOfDataIntoRecyclerView(userList: ArrayList<UserEntity>, userHistoryDao: UserHistoryDao) {

        if (userList.isNotEmpty()) {

            // Adapter class is initialized and list is passed in the param.
            val itemAdapter = UserHistoryAdapter(userList){ deleteId->
                lifecycleScope.launch {
                    userHistoryDao.fetchEmployeeById(deleteId).collect {
                        if (it != null) {
                            userHistoryDao.delete(UserEntity(deleteId))
                        }
                    }
                }

            }
            // Set the LayoutManager that this RecyclerView will use.
            binding?.rvHistory?.layoutManager = GridLayoutManager(this,2)
            // adapter instance is set to the recyclerview to inflate the items.
            binding?.rvHistory?.adapter = itemAdapter
            binding?.rvHistory?.visibility = View.VISIBLE
        } else {

            binding?.rvHistory?.visibility = View.GONE
        }

    }
}