package com

import com.google.gson.Gson

data class Student(
    val studentId: String,
    val studentName: String,
    val studentGrade: Double,
    val telephones: List<String>
)

fun main() {
    val gson = Gson()

    val student = Student(
        studentId = "12345",
        studentName = "John Doe",
        studentGrade = 3.4,
        telephones = listOf("1234", "1234")
    )

    val jsonValue = gson.toJson(student)
    val descryptedJson = gson.fromJson(jsonValue, Student::class.java)

    println(descryptedJson)
}