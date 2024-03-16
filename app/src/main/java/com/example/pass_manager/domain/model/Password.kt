package com.example.pass_manager.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Password (
    val id : Int,
    val siteName : String,
    val userName : String,
    val password: String
) : Parcelable