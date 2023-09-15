package com.patronusgroup.presentation.list

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker
import com.patronusgroup.presentation.R
import com.patronusgroup.presentation.list.state.ListUiState
import com.patronusgroup.presentation.list.viewmodel.ListViewModel
import com.patronusgroup.presentation.style.BodyTextStyle
import com.patronusgroup.presentation.style.FullNameTextStyle
import com.patronusgroup.presentation.style.GenderTextStyle
import com.patronusgroup.presentation.style.HeaderTextStyle
import com.patronusgroup.presentation.views.DisplayImage
import com.patronusgroup.presentation.views.ErrorView
import com.patronusgroup.presentation.views.LoadingView
import com.patronusgroup.presentation.views.StickersView

@Composable
fun ListScreenView(
    viewModel: ListViewModel,
    isInternetConnected: Boolean,
    navigateToDetail: (String) -> Unit,
) {
    val viewState by viewModel.listUiState.collectAsStateWithLifecycle()
    when (viewState) {
        is ListUiState.Loading -> LoadingView()
        is ListUiState.Success -> DeviceHolderListView(
            (viewState as ListUiState.Success).deviceHolders,
            isInternetConnected,
            navigateToDetail,
        )

        else -> ErrorView(viewModel::loadData)
    }
}

@Composable
private fun DeviceHolderListView(
    deviceHolders: List<DeviceHolder>,
    isInternetConnected: Boolean,
    navigateToDetail: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Header()
        ListView(deviceHolders, isInternetConnected, navigateToDetail)
    }
}

@Composable
private fun Header() {
    Text(
        text = stringResource(id = R.string.label_device_holders),
        style = HeaderTextStyle,
    )
}

@Composable
private fun ListView(
    deviceHolders: List<DeviceHolder>,
    isInternetConnected: Boolean,
    navigateToDetail: (String) -> Unit,
) {
    val context = LocalContext.current
    val onItemClick: (String) -> Unit = { id ->
        if (isInternetConnected) {
            navigateToDetail(id)
        } else {
            showNoInternetError(context)
        }
    }

    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(deviceHolders) { deviceHolder ->
            DeviceHolderListItem(
                deviceHolder,
                onItemClick = { onItemClick(deviceHolder.id.toString()) },
            )
        }
    }
}

private fun showNoInternetError(context: Context) {
    Toast.makeText(context, R.string.internet_error_text, Toast.LENGTH_SHORT).show()
}

@Composable
private fun DeviceHolderListItem(
    deviceHolder: DeviceHolder,
    onItemClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .clickable(onClick = onItemClick),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                DisplayImage(deviceHolder.imageUrl, deviceHolder.nameInitials)
                Content(deviceHolder)
            }
            Divider(color = Color.LightGray, thickness = 1.dp)
        }
    }
}

@Composable
private fun Content(deviceHolder: DeviceHolder) {
    Column(
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        ContentFirstLine(deviceHolder)
        ContentSecondLine(deviceHolder)
    }
}

@Composable
private fun ContentFirstLine(deviceHolder: DeviceHolder) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = deviceHolder.fullName,
            style = FullNameTextStyle,
        )
        Text(
            text = deviceHolder.gender?.value ?: "",
            style = GenderTextStyle,
        )
    }
}

@Composable
private fun ContentSecondLine(deviceHolder: DeviceHolder) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            text = deviceHolder.phoneNumber,
            style = BodyTextStyle,
        )
        StickersView(deviceHolder.stickers)
    }
}

@Preview(showBackground = true)
@Composable
private fun ListScreenPreview() {
    DeviceHolderListItem(
        deviceHolder = DeviceHolder(
            1,
            fullName = "John Cena",
            gender = Gender.MALE,
            phoneNumber = "+1 123-456789",
            imageUrl = "https://fastly.picsum.photos/id/445/400/400.jpg?hmac=CCjqlZXQQ_5kl0X6naMjQKUWSbQloDYImyB9zGFOA8M",
            stickers = listOf(
                Sticker.BAN,
                Sticker.FAM,
            ),
            nameInitials = "JC",
        ),
    ) {}
}

@Preview(showBackground = true)
@Composable
private fun DeviceHolderListPreview() {
    DeviceHolderListView(
        listOf(
            DeviceHolder(
                1,
                fullName = "John Cena",
                gender = Gender.MALE,
                phoneNumber = "+1 123-456789",
                imageUrl = "https://fastly.picsum.photos/id/445/400/400.jpg?hmac=CCjqlZXQQ_5kl0X6naMjQKUWSbQloDYImyB9zGFOA8M",
                stickers = listOf(
                    Sticker.FAM,
                    Sticker.BAN,
                ),
                nameInitials = "JC",
            ),
            DeviceHolder(
                2,
                fullName = "Some One",
                gender = Gender.FEMALE,
                phoneNumber = "+1 123-456789",
                imageUrl = null,
                stickers = listOf(Sticker.FAM),
                nameInitials = "SO",
            ),
        ),
        true,
    ) {}
}
