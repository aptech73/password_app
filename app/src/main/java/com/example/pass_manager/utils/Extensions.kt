package com.example.pass_manager.utils

import android.text.Editable

fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)