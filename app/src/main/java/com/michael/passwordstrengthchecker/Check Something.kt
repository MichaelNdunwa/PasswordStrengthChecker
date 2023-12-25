package com.michael.passwordstrengthchecker

fun main() {
    val password = "Michael92"
    val number = 1..100
    println(checkForNumber(password))
}

fun checkForNumber(password: String): Boolean {
    return password.contains("[0-9]".toRegex())
}