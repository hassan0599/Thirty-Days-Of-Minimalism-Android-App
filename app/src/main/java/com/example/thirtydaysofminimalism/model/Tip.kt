package com.example.thirtydaysofminimalism.model

import androidx.annotation.StringRes

data class Tip(
    @StringRes val name: Int,
    @StringRes val description: Int,
    val number: Int
) {
}