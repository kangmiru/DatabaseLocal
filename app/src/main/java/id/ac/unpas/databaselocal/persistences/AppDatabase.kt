package id.ac.unpas.databaselocal.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.databaselocal.model.PencatatanOlahraga

@Database(entities = [PencatatanOlahraga::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pencatatanOlahragaDao(): PencatatanOlahragaDao
}
