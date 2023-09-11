package com.patronusgroup.ui.nav

sealed class Screen(val route: String)
object DetailScreen : Screen("detail")
object ListScreen : Screen("list")
