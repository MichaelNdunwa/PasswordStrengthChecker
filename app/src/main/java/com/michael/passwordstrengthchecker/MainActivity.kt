package com.michael.passwordstrengthchecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var passwordEditText: EditText
    private lateinit var passwordTextView: TextView
    private lateinit var checkPasswordStrengthButton: Button
    private lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        passwordEditText = findViewById(R.id.passwordEditText)
        passwordTextView = findViewById(R.id.passwordTextView)
        checkPasswordStrengthButton = findViewById(R.id.checkPasswordStrengthButton)

        checkPasswordStrengthButton.setOnClickListener {
            password = passwordEditText.text.toString()
            passwordTextView.text = checkPasswordStrength(password)
            passwordTextView.visibility = View.VISIBLE
        }
    }

    private fun passwordLength(password: String): Boolean {
        return password.length > 4
    }

    private fun presenceOfNumbers(password: String): Boolean {
        return password.contains(Regex("[0-9]"))
    }

    private fun specialCharacters(password: String): Boolean {
        val specialCharacters = "!@#$%^&*()-_=+[]{}|;:'\",.<>/?"
        /*password.forEach {
            if (specialCharacters.contains(it)) {
                return true
            }
        }
        return false*/

        // I got this idea from chatGPT:
        return password.any { specialCharacters.contains(it) }
    }

    private fun checkUpperCaseLetters(password: String): Boolean {
        return password.contains(Regex("[A-Z]"))
    }

    private fun checkPasswordStrength(password: String): String {
       return when {
           passwordLength(password) && specialCharacters(password) && presenceOfNumbers(password)
                   && checkUpperCaseLetters(password)-> "Very Strong Password"
           passwordLength(password) && presenceOfNumbers(password) && checkUpperCaseLetters(password) -> "Moderate Password"
           passwordLength(password) && specialCharacters(password) && presenceOfNumbers(password) ->
               "Strong Password (But do add Upper Case Letters)"
           passwordLength(password) && presenceOfNumbers(password) ->
               "Moderate Password (But do add Upper Case Letters)"
           passwordLength(password) -> "Weak Password (Add special characters and numbers)"
           else -> "Very Weak Password (Add more characters)"
        }
    }
}