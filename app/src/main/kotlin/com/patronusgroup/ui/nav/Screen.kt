package com.patronusgroup.ui.nav

sealed class Screen(val route: String)
object DetailScreen : Screen("detail/{$detailScreenArgumentIdKey}")
object ListScreen : Screen("list")

const val detailScreenArgumentIdKey = "id"
