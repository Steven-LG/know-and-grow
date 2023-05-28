package com.example.lorem

class Mentor(
    val id: String,
    val name: String,
    val profilePicture: String,
    val position: String,
    val rating: Double,
    val profileVideo: String,
    val workExperience: String,
    val images: Array<String>,
    val company: String,
    val priceRate: Double,
    val seminars: Array<Seminar>
) {
}
