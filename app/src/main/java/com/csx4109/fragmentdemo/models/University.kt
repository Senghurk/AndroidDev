package com.csx4109.fragmentdemo.models

data class University(
    val name: String,
    val alpha_two_code: String,
    val `state-province`: String,
    val domains: List<String>,
    val web_pages: List<String>
)