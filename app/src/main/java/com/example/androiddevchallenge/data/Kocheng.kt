package com.example.androiddevchallenge.data

import com.example.androiddevchallenge.R

data class Kocheng(
    val id: String,
    val name: String,
    val sex: String,
    val breed: String,
    val age: String,
    val image: Int
)

val kochengList = listOf(
    Kocheng(
        "1",
        "Uchiha Roy",
        "Male",
        "British Short Hair",
        "1",
        R.drawable.satu
    ),
    Kocheng(
        "2",
        "Nur Amelia",
        "Female",
        "Anggora",
        "2",
        R.drawable.dua
    ),
    Kocheng(
        "3",
        "Muhammad Fatah",
        "Male",
        "Persia",
        "4",
        R.drawable.sepuluh
    ),
    Kocheng(
        "4",
        "Samsul",
        "Male",
        "Persija",
        "1",
        R.drawable.enam
    ),
    Kocheng(
        "5",
        "Cimoy",
        "Female",
        "British Short Hair",
        "3",
        R.drawable.delapan
    ),
    Kocheng(
        "6",
        "Mas Pam",
        "Male",
        "Persia",
        "2",
        R.drawable.tujuh
    ),
    Kocheng(
        "7",
        "Nadin Hayati",
        "Female",
        "Anggora",
        "2",
        R.drawable.sembilan
    ),
    Kocheng(
        "8",
        "Cinovac",
        "Male",
        "Persia",
        "1",
        R.drawable.lima
    ),
    Kocheng(
        "9",
        "Aldi T Awal",
        "Female",
        "Laperm",
        "4",
        R.drawable.empat
    ),
    Kocheng(
        "10",
        "Terawan",
        "Female",
        "Persia",
        "2",
        R.drawable.tiga
    ),
)