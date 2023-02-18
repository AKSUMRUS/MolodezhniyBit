package com.nux.studio.studtourism.ui.components.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH3
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH5
import com.nux.studio.studtourism.data.local.models.*
import com.nux.studio.studtourism.ui.components.atoms.*
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH6
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel
import com.nux.studio.studtourism.ui.components.atoms.texts.SectionHeader

@Composable
fun DormitoryScreen(
    index: Int,
    viewModel: MainViewModel
) {
    Log.e("LOGGGG", viewModel.toString())
    val dormitory = viewModel.state.dormitoriesList[index]
    LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
        item {
            LazyRow() {
                item {
                    dormitory.details?.mainInfo?.photos?.forEach { photoUrl ->
                        AsyncImage(
                            model = photoUrl,
                            contentDescription = "Фото общежития",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp)
                        )
                    }
                }
            }
            HeadlineH3(
                text = dormitory.details?.mainInfo!!.name, modifier = Modifier.padding(15.dp, 5.dp)
            )
            Row(
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .background(Color.Transparent)
            ) {
                dormitory.details.mainInfo.city?.let { city ->
                    Pill(dormitory.details.mainInfo.city, variant = PillVariant.BACKGROUND)
                }
            }
            val minDays = dormitory.details.mainInfo.minDays
            val maxDays = dormitory.details.mainInfo.maxDays
            if (!minDays.isNullOrEmpty() && !maxDays.isNullOrEmpty()) {
                Dates("$minDays – $maxDays дней", modifier = Modifier.padding(15.dp, 0.dp))
            }
            Price(getFormattedPrice(dormitory), modifier = Modifier.padding(15.dp, 0.dp))

            SectionHeader(text = "Адрес", modifier = Modifier.padding(15.dp, 0.dp))
            Text(text = getFormattedAddress(dormitory), modifier = Modifier.padding(15.dp))

            dormitory.rooms.let { rooms ->
                if (rooms != null && rooms.isNotEmpty()) {
                    SectionHeader(text = "Комнаты", modifier = Modifier.padding(15.dp, 0.dp))
                    Rooms(rooms = rooms.values)
                }
            }
            dormitory.details.services?.let { services ->
                if (services.isNotEmpty()) {
                    SectionHeader(text = "Услуги", modifier = Modifier.padding(15.dp, 0.dp))
                    Services(services = services)
                }
            }
            dormitory.details.documents?.let { documents ->
                if (documents.isNotEmpty()) {
                    SectionHeader(text = "Документы", modifier = Modifier.padding(15.dp, 0.dp))
                    Documents(documents = dormitory.details.documents)
                }
            }
            dormitory.details.rules?.let { rules ->
                SectionHeader(text = "Правила", modifier = Modifier.padding(15.dp, 0.dp))
                Rules(rules = rules)
            }
        }
    }
}

@Composable
fun Room(
    room: Room,
    modifier: Modifier = Modifier,
) {
    HorizontalCard(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .then(modifier)
    ) {
        HeadlineH5(
            text = room.details?.type ?: "Что-то где можно жить",
        )
        Price(formatPrice(room.details?.price), modifier = Modifier)
        room.details?.description?.let { description ->
            HorizontalCardDescription(text = description, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun Rooms(
    rooms: Collection<Room>,
    modifier: Modifier = Modifier,
) {
    HorizontalCardsContainer(modifier = modifier) {
        rooms.forEach { room ->
            item {
                Room(room = room)
            }
        }
    }
}

@Composable
fun Service(
    service: Service,
    modifier: Modifier = Modifier,
) {
    HorizontalCard(
        color = MaterialTheme.colors.primaryVariant,
        modifier = Modifier.then(modifier)
    ) {
        HeadlineH5(
            text = service.name ?: "Услуга",
        )
        Price(formatPrice(service.price))
        service.description?.let { description ->
            HorizontalCardDescription(text = description)
        }
    }
}

@Composable
fun Services(
    services: Collection<Service>,
    modifier: Modifier = Modifier,
) {
    HorizontalCardsContainer(modifier = modifier) {
        services.forEach { service ->
            item {
                Service(service)
            }
        }
    }
}

@Composable
fun Documents(
    documents: Collection<DocumentUrl>,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(15.dp, 0.dp)
            .then(modifier)
    ) {
        documents.forEachIndexed { index, documentUrl ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(documentUrl))
            Button(onClick = { context.startActivity(intent) }) {
                Text(text = "Документ ${index + 1}")
            }
        }
    }
}

@Composable
fun Rules(
    rules: Rules,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .then(modifier)
    ) {
        val uniDoc = rules.requiredUniDocuments;
        val studentsDocs = rules.requiredStudentsDocuments;
        if (studentsDocs == uniDoc && studentsDocs != null) {
            Text(
                text = "Перечень необходимых документов и требований",
                modifier = Modifier, fontWeight = FontWeight.Bold, fontSize = 18.sp
            )
            Text(text = studentsDocs)
        } else {
            rules.requiredUniDocuments?.let { requiredUniDocuments ->
                Text(
                    text = "Перечень необходимых документов и требований для направляющей образовательной организации",
                    modifier = Modifier, fontWeight = FontWeight.Bold, fontSize = 18.sp
                )
                Text(text = requiredUniDocuments)
            }
            rules.requiredStudentsDocuments?.let { requiredStudentsDocuments ->
                Text(
                    text = "Перечень необходимых документов и требований для путешественников, оплачивающих услуги самостоятельно",
                    modifier = Modifier, fontWeight = FontWeight.Bold, fontSize = 18.sp
                )
                Text(text = requiredStudentsDocuments)
            }
        }
    }
}