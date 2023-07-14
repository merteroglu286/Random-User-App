package tr.main.randomuserapp.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import tr.main.randomuserapp.model.UserEntity

@Dao
interface UserHistoryDao {

    @Insert
    suspend fun insert(employeeEntity: UserEntity)

    @Delete
    suspend fun delete(employeeEntity: UserEntity)

    @Query("Select * from `users-table`")
    fun fetchAllEmployee():Flow<List<UserEntity>>

    @Query("Select * from `users-table` where id=:id")
    fun fetchEmployeeById(id:Int):Flow<UserEntity>
}