package tr.main.randomuserapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tr.main.randomuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.generateRandomUserBtn?.setOnClickListener{

            val intent = Intent(this@MainActivity,RandomUserActivity::class.java)
            startActivity(intent)

        }

        binding?.historyBtn?.setOnClickListener {
            val intent = Intent(this@MainActivity,UserHistoryActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}