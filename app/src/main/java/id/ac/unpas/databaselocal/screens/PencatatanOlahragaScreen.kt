package id.ac.unpas.databaselocal.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.databaselocal.model.PencatatanOlahraga
import id.ac.unpas.databaselocal.persistences.AppDatabase

@Composable
fun PencatatanOlahragaScreen() {

    val context = LocalContext.current

    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "pengelolaan-sampah"
    ).build()

    val pencatatanOlahragaDao = db.pencatatanOlahragaDao()

    val list : LiveData<List<PencatatanOlahraga>> = pencatatanOlahragaDao.loadAll()
    val items: List<PencatatanOlahraga> by list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        FormPencatatanOlahraga(pencatatanOlahragaDao)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal", fontSize = 14.sp)
                        Text(
                            text = item.tanggal, fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama", fontSize = 14.sp)
                        Text(
                            text = item.olahraga, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Berat Badan", fontSize = 14.sp)
                        Text(
                            text = "${item.berat_badan} Kg", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Keterangan", fontSize = 14.sp)
                        Text(
                            text = item.keterangan, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}