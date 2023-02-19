package com.nux.studio.studtourism.ui.components.screens

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.data.local.models.*
import com.nux.studio.studtourism.ui.components.atoms.*
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH5
import com.nux.studio.studtourism.ui.components.atoms.texts.SectionHeader
import com.nux.studio.studtourism.ui.components.atoms.texts.Subtitle2
import com.nux.studio.studtourism.ui.components.molecules.BackButton
import com.nux.studio.studtourism.ui.navigation.SegmentControlTabs
import com.nux.studio.studtourism.ui.viewmodels.AuthViewModel
import com.nux.studio.studtourism.ui.viewmodels.MainViewModel
import com.nux.studio.studtourism.ui.viewmodels.UniversityViewModel
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import java.util.*
import java.util.Date

@Composable
fun DormitoryScreen(
    dormitoryId: String,
    navController: NavController,
    viewModel: MainViewModel
) {
    val dormitory = viewModel.state.dormitoriesList.find { item ->
        item.id == dormitoryId
    } ?: viewModel.state.dormitoriesList[0]

    var requestState by remember { mutableStateOf(DormitoryBookingRequest()) }

    val authRepository = hiltViewModel<AuthViewModel>().repository

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    val month: Int = calendar.get(Calendar.MONTH)
    val year: Int = calendar.get(Calendar.YEAR)

    val checkedCalendar = remember { mutableStateOf(false) }

    calendar.time = Date()

    val datePickerDialogFrom = DatePickerDialog(
        context,
        { _: DatePicker, yearPicked: Int, monthRaw: Int, dayRaw: Int ->
            val dayPicked = String.format("%02d", dayRaw)
            val monthPicked = String.format("%02d", monthRaw + 1)
            val date = "$dayPicked.$monthPicked.$yearPicked"
            requestState = requestState.copy(dates = requestState.dates.copy(from = date))
        }, year, month, day
    )
    val datePickerDialogTo = DatePickerDialog(
        context,
        { _: DatePicker, yearPicked: Int, monthRaw: Int, dayRaw: Int ->
            val dayPicked = String.format("%02d", dayRaw)
            val monthPicked = String.format("%02d", monthRaw + 1)
            val date = "$dayPicked.$monthPicked.$yearPicked"
            requestState = requestState.copy(dates = requestState.dates.copy(to = date))
        }, year, month, day
    )

    val universityViewModel: UniversityViewModel = hiltViewModel()
    var screenState by remember { mutableStateOf(0) }

    if (dormitory.universityId != null) {
        LaunchedEffect(true) {
            universityViewModel.loadUniversity(dormitory.universityId)
        }
    }
    val university = universityViewModel.state.university

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
            item {
                dormitory.details?.mainInfo?.photos?.let { photos ->
                    if (photos.isNotEmpty()) {
                        ImagesCarousel(photos = photos)
                    }
                }
                HeadlineH5(
                    text = dormitory.details?.mainInfo!!.name,
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .padding(top = 20.dp),
                    fontWeight = FontWeight.Bold,
                )
                Row(
                    modifier = Modifier
                        .padding(10.dp, 0.dp)
                        .background(Color.Transparent)
                ) {
                    dormitory.details.mainInfo.city?.let { city ->
                        Pill(city, variant = PillVariant.BACKGROUND)
                    }
                    university?.details?.region?.let { region ->
                        Pill(region, variant = PillVariant.BACKGROUND)
                    }
                }
                if (screenState == 0) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 15.dp)
                            .fillMaxWidth(),
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            val minDays = dormitory.details.mainInfo.minDays
                            val maxDays = dormitory.details.mainInfo.maxDays
                            if (!minDays.isNullOrEmpty() && !maxDays.isNullOrEmpty()) {
                                Dates(
                                    "$minDays – $maxDays дней",
                                    modifier = Modifier.padding(bottom = 7.dp)
                                )
                            }
                            Price(getFormattedPrice(dormitory))
                        }
                        Column(modifier = Modifier.weight(1f)) {
                            dormitory.details.rules?.committee?.phone?.let { phone ->
                                Phone(phone, modifier = Modifier.padding(bottom = 7.dp))
                            }
                            dormitory.details.rules?.committee?.email?.let { email -> Mail(email) }
                        }
                    }
                    SectionHeader(text = "Адрес", modifier = Modifier.padding(15.dp, 0.dp))
                    Text(text = getFormattedAddress(dormitory), modifier = Modifier.padding(15.dp))
                    university?.details?.name?.let { name ->
                        SectionHeader(
                            text = "Организация",
                            modifier = Modifier.padding(horizontal = 15.dp)
                        )
                        Text(text = name, modifier = Modifier.padding(horizontal = 15.dp))
                    }
                    dormitory.rooms.let { rooms ->
                        if (rooms != null && rooms.isNotEmpty()) {
                            SectionHeader(
                                text = "Комнаты",
                                modifier = Modifier.padding(15.dp, 0.dp)
                            )
                            Rooms(rooms = rooms.values)
                        }
                    }
                    dormitory.details.services?.let { services ->
                        if (services.isNotEmpty()) {
                            SectionHeader(text = "Услуги", modifier = Modifier.padding(15.dp, 0.dp))
                            Services(services = services)
                        }
                    }
                    dormitory.details.rules?.let { rules ->
                        SectionHeader(
                            text = "Правила",
                            modifier = Modifier
                                .padding(horizontal = 15.dp)

                        )
                        Rules(rules = rules)
                    }
                    dormitory.details.documents?.let { documents ->
                        if (documents.isNotEmpty()) {
                            SectionHeader(
                                text = "Документы",
                                modifier = Modifier.padding(15.dp, 0.dp)
                            )
                            Documents(documents = dormitory.details.documents)
                        }
                    }
                } else {

//                    SectionHeader(
//                        text = "Тип Размещения",
//                        modifier = Modifier.padding(15.dp, 0.dp)
//                    )
//                    SectionHeader(
//                        text = "Питание",
//                        modifier = Modifier.padding(15.dp, 0.dp)
//                    )
                    LazyRow() {
                        dormitory.rooms?.forEach { roomPair ->
                            val id = roomPair.key
                            val room = roomPair.value
                            item {
                                OurRadioButton(
                                    text = room.details?.type ?: "Тип",
                                    selected = (requestState.roomId == id),
                                    onClick = { requestState = requestState.copy(roomId = id) })
                            }
                        }
                    }
                    SectionHeader(
                        text = "Даты",
                        modifier = Modifier.padding(15.dp, 0.dp)
                    )
                    InputFieldScreen(
                        title = "c дд/мм",
                        text = requestState.dates.from ?: "",
                        onValueChange = {
                            requestState =
                                requestState.copy(dates = requestState.dates.copy(from = it))
                        },
                        trailingIcon = {
                            Icon(
                                ImageVector.vectorResource(id = R.drawable.icon_calendar),
                                tint = Color.Black,
                                contentDescription = "calendar",
                                modifier = Modifier
                                    .clickable {
                                        datePickerDialogFrom.show()
                                    }
                            )
                        }
                    )
                    InputFieldScreen(
                        title = "до дд/мм",
                        text = requestState.dates.to ?: "",
                        onValueChange = {
                            requestState =
                                requestState.copy(dates = requestState.dates.copy(to = it))
                        },
                        trailingIcon = {
                            Icon(
                                ImageVector.vectorResource(id = R.drawable.icon_calendar),
                                tint = Color.Black,
                                contentDescription = "calendar",
                                modifier = Modifier
                                    .clickable {
                                        datePickerDialogTo.show()
                                    }
                            )
                        }
                    )
                    InputFieldScreenWithTitle(
                        title = "ФИО",
                        text = requestState.author.name ?: "",
                        onValueChange = {
                            requestState =
                                requestState.copy(author = requestState.author.copy(name = it))
                        }
                    )
                    InputFieldScreenWithTitle(
                        title = "E-mail",
                        text = requestState.author.contacts.email ?: "",
                        onValueChange = {
                            requestState = requestState.copy(
                                author = requestState.author.copy(
                                    contacts = requestState.author.contacts.copy(email = it)
                                )
                            )
                        }
                    )
                    InputFieldScreenWithTitle(
                        title = "Телефон",
                        text = requestState.author.contacts.phone ?: "",
                        onValueChange = {
                            requestState = requestState.copy(
                                author = requestState.author.copy(
                                    contacts = requestState.author.contacts.copy(phone = it)
                                )
                            )
                        }
                    )
                    InputFieldScreenWithTitle(
                        title = "Комментарий",
                        text = requestState.comment ?: "",
                        onValueChange = {
                            requestState = requestState.copy(comment = it)
                        }
                    )
                    Row(
                        modifier = Modifier
                    ) {
                        SectionHeader(
                            text = "Добавить в календарь",
                            modifier = Modifier
                                .padding(15.dp, 0.dp)
                                .align(CenterVertically)
                        )
                        Switch(
                            checked = checkedCalendar.value,
                            modifier = Modifier
                                .align(CenterVertically),
                            onCheckedChange = {
                                checkedCalendar.value = it
                            },
                        )
                    }
                }
                Box(modifier = Modifier.height(120.dp))
            }
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(bottom = 30.dp)
                .padding(horizontal = 25.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface,
            ),
            onClick = {
                if (!authRepository.isAuthorized()) {
                    navController.navigate("login")
                } else if (screenState == 0) {
                    screenState = 1
                } else {
                    screenState = 0
                    if (checkedCalendar.value) {
                        val formatter = DateTimeFormatterBuilder()
                            .appendPattern("dd.MM.yyyy")
                            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                            .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
                            .toFormatter()
                        val start: LocalDateTime =
                            LocalDateTime.parse(requestState.dates.from, formatter)
                        val startMillis: Long =
                            start.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()

                        val end: LocalDateTime =
                            LocalDateTime.parse(requestState.dates.to, formatter)
                        val endMillis: Long =
                            end.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()

                        val intent = Intent(Intent.ACTION_EDIT)
                        intent.type = "vnd.android.cursor.item/event"
                        intent.putExtra("beginTime", startMillis)
                        intent.putExtra("allDay", true)
                        intent.putExtra("rule", "FREQ=YEARLY")
                        intent.putExtra("endTime", endMillis)
                        intent.putExtra("title", dormitory.details?.mainInfo?.name)
                        startActivity(context, intent, null)
                    }
                    viewModel.makeDormitoryBooking(requestState)
                    navController.navigate(SegmentControlTabs.DORMITORIES.route) {
                        popUpTo(SegmentControlTabs.DORMITORIES.route) {

                        }
                    }
                }
            },
        ) {
            Text(
                modifier = Modifier.padding(15.dp),
                text = "Оставить заявку",
                color = MaterialTheme.colors.onSurface,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }

        BackButton(
            navController = navController,
            modifier = Modifier.align(Alignment.TopStart)
        )

    }
}

@Composable
private fun Room(
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
private fun Rooms(
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
private fun Service(
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
private fun Services(
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
private fun Documents(
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
private fun RulesHeader(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = Modifier
            .padding(bottom = 10.dp)
            .then(modifier),
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
}

@Composable
private fun RulesText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = Modifier
            .padding(bottom = 20.dp)
            .then(modifier),
        lineHeight = 20.sp,
        style = MaterialTheme.typography.body2
    )
}

@Composable
private fun Rules(
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
            RulesHeader(
                text = "Перечень необходимых документов и требований"
            )
            RulesText(text = studentsDocs)
        } else {
            rules.requiredUniDocuments?.let { requiredUniDocuments ->
                RulesHeader(
                    text = "Перечень необходимых документов и требований для направляющей образовательной организации",
                )
                RulesText(text = requiredUniDocuments)
            }
            rules.requiredStudentsDocuments?.let { requiredStudentsDocuments ->
                RulesHeader(
                    text = "Перечень необходимых документов и требований для путешественников, оплачивающих услуги самостоятельно",
                )
                RulesText(text = requiredStudentsDocuments)
            }
        }
    }
}

@Composable
private fun InputFieldScreen(
    title: String,
    text: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
//    if(!title.isNullOrEmpty()) {
//        Subtitle2(
//            text = title,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(
//                start = 16.dp,
//                end = 16.dp,
//                top = 16.dp,
//                bottom = 4.dp
//            )
//        )
//    }

    InputField(
        text = text,
        placeholder = title,
        onValueChange = onValueChange,
        modifier = Modifier
            .background(Color(0xF3F8FCFF)),
        trailingIcon = trailingIcon
    )
}

@Composable
private fun InputFieldScreenWithTitle(
    title: String,
    text: String,
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
) {
    Subtitle2(
        text = title,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 4.dp
        )
    )

    InputField(
        text = text,
        placeholder = title,
        onValueChange = onValueChange,
        modifier = Modifier
            .background(Color(0xF3F8FCFF)),
        trailingIcon = trailingIcon
    )
}