package com.patronusgroup.presentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.patronusgroup.domain.model.DeviceHolderDetail
import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker
import com.patronusgroup.presentation.R
import com.patronusgroup.presentation.detail.viewmodel.DetailViewModel
import com.patronusgroup.presentation.style.BodyTextStyle
import com.patronusgroup.presentation.style.GreyBackground
import com.patronusgroup.presentation.style.Header2TextStyle
import com.patronusgroup.presentation.style.HeaderTextStyle
import com.patronusgroup.presentation.views.DisplayImage
import com.patronusgroup.presentation.views.ErrorView
import com.patronusgroup.presentation.views.LoadingView
import com.patronusgroup.presentation.views.MapShape
import com.patronusgroup.presentation.views.SpacerM
import com.patronusgroup.presentation.views.SpacerS
import com.patronusgroup.presentation.views.SpacerXS
import com.patronusgroup.presentation.views.StickersView
import com.patronusgroup.presentation.detail.state.DetailUiState
import kotlinx.coroutines.delay

@Composable
fun DetailScreenView(
    viewModel: DetailViewModel,
    goBackToList: () -> Unit,
) {
    val viewState by viewModel.detailUiState.collectAsStateWithLifecycle()
    when (viewState) {
        is DetailUiState.Loading -> LoadingView()
        is DetailUiState.Success -> DetailScreen(
            (viewState as DetailUiState.Success).detail,
            goBackToList,
        )

        else -> ErrorView { viewModel.loadData() }
    }
}

@Composable
private fun DetailScreen(
    detail: DeviceHolderDetail,
    onBackButtonClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        BackButton(onBackButtonClick)
        SpacerS()
        MapWithLocation(detail.latitude, detail.longitude)
        SpacerS()
        DisplayImage(detail.imageUrl, detail.nameInitials)
        SpacerM()
        DeviceHolderNameText(detail.fullName)
        SpacerXS()
        StickersView(detail.stickers)
        SpacerXS()
        GenderAndContactNumberView(detail.gender, detail.phoneNumber)
        SpacerM()
        AddressView(detail.fullAddress)
    }
}

@Composable
private fun BackButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .size(48.dp)
            .clickable(onClick = onClick)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.ic_arrow),
            contentDescription = null,
        )
    }
}

@Composable
private fun MapWithLocation(latitude: Double?, longitude: Double?) {
    if (latitude != null && longitude != null) {
        val location = LatLng(latitude, longitude)

        val initialZoom = 10f
        val finalZoom = 15f

        val markerVisibility = remember { mutableStateOf(false) }

        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(location, initialZoom)
        }

        GoogleMap(
            uiSettings = MapUiSettings(
                compassEnabled = false,
                indoorLevelPickerEnabled = false,
                mapToolbarEnabled = false,
                myLocationButtonEnabled = false,
                rotationGesturesEnabled = false,
                scrollGesturesEnabled = false,
                scrollGesturesEnabledDuringRotateOrZoom = false,
                tiltGesturesEnabled = false,
                zoomControlsEnabled = false,
                zoomGesturesEnabled = false,
            ),
            modifier = Modifier
                .height(292.dp)
                .clip(
                    MapShape(),
                ),
            cameraPositionState = cameraPositionState.also {
                LaunchedEffect(key1 = true) {
                    delay(300)
                    cameraPositionState.animate(
                        update = CameraUpdateFactory.newCameraPosition(
                            CameraPosition(location, finalZoom, 0f, 0f),
                        ),
                        durationMs = 2000,
                    )
                    markerVisibility.value = true
                }
            },
        ) {
            Marker(
                icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_location_marker),
                state = MarkerState(position = location),
                visible = markerVisibility.value,
            )
        }
    }
}

@Composable
private fun DeviceHolderNameText(fullName: String) {
    Text(
        text = fullName,
        style = HeaderTextStyle,
    )
}

@Composable
private fun GenderAndContactNumberView(
    gender: Gender?,
    phoneNumber: String,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = gender?.value ?: "",
            style = BodyTextStyle,
        )

        Divider(
            color = GreyBackground,
            modifier = Modifier
                .height(16.dp)
                .width(1.dp),
        )

        Text(
            text = phoneNumber,
            style = BodyTextStyle,
        )
    }
}

@Composable
private fun AddressView(
    fullAddress: String,
) {
    Column {
        Text(
            text = stringResource(id = R.string.label_address),
            style = Header2TextStyle,
        )
        SpacerS()
        Text(
            text = fullAddress,
            style = BodyTextStyle,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview() {
    DetailScreen(
        detail = DeviceHolderDetail(
            imageUrl = null,
            latitude = null,
            longitude = null,
            fullName = "John Cena",
            stickers = listOf(
                Sticker.FAM,
                Sticker.BAN,
            ),
            gender = Gender.MALE,
            phoneNumber = "+1 456-3134",
            fullAddress = "123 Imaginary Land, 45678 Imaginary City",
            nameInitials = "JC",
        ),
    ) {}
}

@Preview(showBackground = true)
@Composable
private fun DetailScreenPreview2() {
    DetailScreen(
        detail = DeviceHolderDetail(
            imageUrl = null,
            latitude = null,
            longitude = null,
            fullName = "Maria Müller",
            stickers = listOf(),
            gender = Gender.FEMALE,
            phoneNumber = "+1 456-3134",
            fullAddress = "123 Imaginary Land, 45678 Imaginary City",
            nameInitials = "MM",
        ),
    ) {}
}
