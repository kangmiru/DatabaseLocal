package id.ac.unpas.databaselocal.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.databaselocal.model.PencatatanOlahraga
@Dao
interface PencatatanOlahragaDao {
    @Query("SELECT * FROM PencatatanOlahraga")
    fun loadAll(): LiveData<List<PencatatanOlahraga>>
    @Query("SELECT * FROM PencatatanOlahraga WHERE id = :id")
    fun find(id: String): PencatatanOlahraga?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: PencatatanOlahraga)
    @Delete
    fun delete(item: PencatatanOlahraga)
}