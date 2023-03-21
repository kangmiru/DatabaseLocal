package id.ac.unpas.databaselocal.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.unpas.databaselocal.model.PencatatanOlahraga
import id.ac.unpas.databaselocal.persistences.PencatatanOlahragaDao
import id.ac.unpas.databaselocal.ui.theme.Purple700
import id.ac.unpas.databaselocal.ui.theme.Teal200
import kotlinx.coroutines.launch
import com.benasher44.uuid.uuid4

@Composable
fun FormPencatatanOlahraga(setoranSampahDao: PencatatanOlahragaDao) {
    val tanggal = remember { mutableStateOf(TextFieldValue("")) }
    val olahraga = remember { mutableStateOf(TextFieldValue("")) }
    val berat_badan = remember { mutableStateOf(TextFieldValue("")) }
    val keterangan = remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current

    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            label = { Text(text = "Tanggal") },
            value = tanggal.value,
            onValueChange = {
                tanggal.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        OutlinedTextField(
            label = { Text(text = "Olahraga") },
            value = olahraga.value,
            onValueChange = {
                olahraga.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "push up, joging, dsb") }
        )
        OutlinedTextField(
            label = { Text(text = "Berat Badan") },
            value = berat_badan.value,
            onValueChange = {
                berat_badan.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Decimal
            ),
            placeholder = { Text(text = "40") }
        )
        OutlinedTextField(
            label = { Text(text = "Keterangan") },
            value = keterangan.value,
            onValueChange = {
                keterangan.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                capitalization =
                KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text
            ),
            placeholder = { Text(text = "XXXXX") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val id = uuid4().toString()
                if (tanggal.value.text.isBlank() && olahraga.value.text.isBlank()){
                    Toast.makeText(context, "Field tanggal & olahraga harus diisi", Toast.LENGTH_LONG).show()
                } else {
                    val item = PencatatanOlahraga(
                        id,
                        tanggal.value.text, olahraga.value.text,
                        berat_badan.value.text, keterangan.value.text
                    )

                    scope.launch {
                        setoranSampahDao.insertAll(item)
                    }
                }
                tanggal.value = TextFieldValue("")
                olahraga.value = TextFieldValue("")
                berat_badan.value = TextFieldValue("")
                keterangan.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                tanggal.value = TextFieldValue("")
                olahraga.value = TextFieldValue("")
                berat_badan.value = TextFieldValue("")
                keterangan.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
