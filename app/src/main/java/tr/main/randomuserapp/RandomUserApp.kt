package tr.main.randomuserapp

import android.app.Application
import tr.main.randomuserapp.db.UserHistoryDatabase

class RandomUserApp:Application() {

    val db by lazy {
        UserHistoryDatabase.getInstance(this)
    }

}