package id.ac.unpas.databaselocal.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PencatatanOlahraga(
    @PrimaryKey val id: String,
    val tanggal: String,
    val olahraga: String,
    val berat_badan: String,
    val keterangan: String
)
