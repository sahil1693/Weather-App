package com.example.wheaterapp2

import android.text.Editable

data class Temperature(val location: Editable, val countryCode: Editable)

    object Supplier{
        var temperature = mutableListOf<Temperature>()

    }
