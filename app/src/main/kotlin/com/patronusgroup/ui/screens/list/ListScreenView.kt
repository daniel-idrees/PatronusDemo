package com.patronusgroup.ui.screens.list

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.patronusgroup.domain.model.DeviceHolder
import com.patronusgroup.domain.model.enums.Gender
import com.patronusgroup.domain.model.enums.Sticker
import com.patronusgroup.ui.screens.list.state.ListUiState
import com.patronusgroup.ui.screens.list.viewmodel.ListViewModel
import com.patronusgroup.ui.screens.views.DisplayImage
import com.patronusgroup.ui.screens.views.ErrorView
import com.patronusgroup.ui.screens.views.LoadingView
import com.patronusgroup.ui.screens.views.StickersView
import com.patronusgroup.ui.theme.FullNameTextStyle
import com.patronusgroup.ui.theme.GenderTextStyle
import com.patronusgroup.ui.theme.HeaderTextStyle
import com.patronusgroup.ui.theme.BodyTextStyle

@Composable
fun ListScreenView(
    viewModel: ListViewModel,
    navigateToDetail: (String) -> Unit,
) {
    val viewState by viewModel.listUiState.collectAsStateWithLifecycle()
    when (viewState) {
        is ListUiState.Loading -> LoadingView()
        is ListUiState.Success -> DeviceHolderListView(
            (viewState as ListUiState.Success).deviceHolders,
            navigateToDetail,
        )

        else -> ErrorView(viewModel::loadData)
    }
}

@Composable
private fun DeviceHolderListView(
    deviceHolders: List<DeviceHolder>,
    navigateToDetail: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 24.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Header()
        ListView(deviceHolders, navigateToDetail)
    }
}

@Composable
private fun Header() {
    Text(
        text = "Device holders",
        style = HeaderTextStyle,
    )
}

@Composable
private fun ListView(deviceHolders: List<DeviceHolder>, navigateToDetail: (String) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        items(deviceHolders) { deviceHolder ->
            DeviceHolderListItem(deviceHolder) { navigateToDetail(deviceHolder.id.toString()) }
        }
    }
}

@Composable
private fun DeviceHolderListItem(
    deviceHolder: DeviceHolder,
    navigateToDetail: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .clickable { navigateToDetail() },
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
            stickers = listOf(Sticker.BAN, Sticker.FAM),
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
                stickers = listOf(Sticker.FAM, Sticker.BAN),
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
    ) {}
}
