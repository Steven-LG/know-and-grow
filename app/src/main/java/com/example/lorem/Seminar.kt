package com.example.lorem

import android.telecom.Conference

class Seminar(
    val image: String,
    val name: String,
    val conferences: List<Conference>,
    val mentors: List<Mentor>,
    val rating: Double,
    val price: Int,
    val keywords: List<String>
)
