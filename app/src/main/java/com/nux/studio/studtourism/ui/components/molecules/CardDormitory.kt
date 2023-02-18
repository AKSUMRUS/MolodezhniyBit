package com.nux.studio.studtourism.ui.components.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nux.studio.studtourism.R
import com.nux.studio.studtourism.data.local.models.DormitoryDetails
import com.nux.studio.studtourism.ui.components.atoms.texts.Body1
import com.nux.studio.studtourism.ui.components.atoms.texts.Body2
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH2
import com.nux.studio.studtourism.ui.components.atoms.texts.HeadlineH4

@Composable
fun CardDormitory(
    dormitory: DormitoryDetails,
){

    var price: String = "";
    if(dormitory.service.isNullOrEmpty() || dormitory.service[0].isFree!!){
        price = "Бесплатно"
    }else{
        price = dormitory.service[0].price!!
    }

    var dates: String = "";
    if(dormitory.mainInfo?.minDays.isNullOrEmpty() || dormitory.mainInfo?.maxDays.isNullOrEmpty()){
        dates = "-"
    }else{
        dates = "от ${dormitory.mainInfo!!.minDays} до ${dormitory.mainInfo.maxDays}"
    }


    Column(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .clickable {
            },

        ) {
            Card(elevation = 10.dp) {
                AsyncImage(
                    model = dormitory.mainInfo?.photos?.get(0),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(20.dp)),
                    contentScale = ContentScale.Crop,
                )

                Column(
                    modifier = Modifier.padding(10.dp)
                ){
                    Body1(
                        text = dormitory.mainInfo?.name!!,
                        color = MaterialTheme.colors.onSecondary
                    )

                    var modifierSubtitle:Modifier = Modifier.padding(start = 20.dp)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ){
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.icon),
                            contentDescription = "",
                            modifier = Modifier,
                        )

                        Body2(
                            text = price,
                            color = MaterialTheme.colors.onSecondary,
                            modifier = modifierSubtitle,
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ){
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.icon),
                            contentDescription = "",
                            modifier = Modifier,
                        )

                        Body2(
                            text = dates,
                            color = MaterialTheme.colors.onSecondary,
                            modifier = modifierSubtitle,
                        )
                }
            }
        }

    }

}