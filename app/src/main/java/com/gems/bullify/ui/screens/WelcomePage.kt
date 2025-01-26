package com.gems.bullify.ui.screens

import androidx.compose.foundation.Image
import com.gems.bullify.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import com.gems.bullify.navigation.NavItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun WelcomePage(navController: NavHostController) {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            val gradient = Brush.verticalGradient(listOf(Color(0xFFFFFFFF), Color(0xFFFFEB3B)))
            val login = remember { mutableStateOf("") }
            val email = remember { mutableStateOf("") }
            var password = rememberSaveable { mutableStateOf("") }
            var passwordVisible = rememberSaveable { mutableStateOf(false) }
            var passwordCheckVisible = rememberSaveable { mutableStateOf(false) }
            val checkPassword = remember { mutableStateOf("") }
            var passwordError = remember { mutableStateOf("") }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradient)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Text(
                        "Bullify",
                        fontSize = 30.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "Sign Up",
                        fontSize = 17.sp,
                        color = Color.Gray,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = email.value,
                        onValueChange = { email.value = it },
                        singleLine = true,
                        label = { Text("Email") },
                        placeholder = { Text("Your email") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done
                        )
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = login.value,
                        onValueChange = { login.value = it },
                        singleLine = true,
                        label = { Text("Login") },
                        placeholder = { Text("Name/Login") }
                    )

                    //birthdate
                    DatePickerDocked()

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = password.value,
                        onValueChange = { password.value = it },
                        singleLine = true,
                        label = { Text("Password") },
                        placeholder = { Text("Password") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        trailingIcon = {
                            val image = if (passwordVisible.value)
                                R.drawable.ic_visibility
                            else R.drawable.ic_visibility_off

                            val description =
                                if (passwordVisible.value) "Hide password" else "Show password"

                            IconButton(onClick = {
                                passwordVisible.value = !passwordVisible.value
                            }) {
                                Icon(painter = painterResource(image), description)
                            }
                        },
                        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation()
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = checkPassword.value,
                        onValueChange = { checkPassword.value = it },
                        singleLine = true,
                        label = { Text("Repeat Password") },
                        placeholder = { Text("Repeat Password") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        trailingIcon = {
                            val image = if (passwordCheckVisible.value)
                                R.drawable.ic_visibility
                            else R.drawable.ic_visibility_off

                            val description =
                                if (passwordCheckVisible.value) "Hide password" else "Show password"

                            IconButton(onClick = {
                                passwordCheckVisible.value = !passwordCheckVisible.value
                            }) {
                                Icon(painter = painterResource(image), description)
                            }
                        },
                        visualTransformation = if (passwordCheckVisible.value) VisualTransformation.None else PasswordVisualTransformation()
                    )
                    if (password.value.isNotEmpty() && checkPassword.value.isNotEmpty() && password.value != checkPassword.value) {
                        passwordError.value = "*passwords are not the same"
                        Text(passwordError.value, color = Color.Red, fontSize = 13.sp)
                    }

                    var message = remember { mutableStateOf("") }

                    if (message.value.isNotEmpty()) {
                        Text(message.value, color = Color.Red, fontSize = 13.sp)
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "By signing up you agree on Privacy Policy and Terms of Service",
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            color = Color.Gray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        )
                        Button(
                            onClick = {
                                if (password.value.isNotEmpty() && login.value.isNotEmpty() && email.value.isNotEmpty()) {
                                    navController.navigate(NavItem.ProfileSetPage.route)
                                } else {
                                    message.value = "*It is not complete"
                                }
                            }, modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp)
                        ) {
                            Text("Sign Up")
                        }

                    }

                    Spacer(modifier = Modifier.size(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(width = 100.dp, height = 1.dp)
                                .background(Color.Black)
                        )
                        Text("or")
                        Box(
                            modifier = Modifier
                                .size(width = 100.dp, height = 1.dp)
                                .background(Color.Black)
                        )
                    }

                    Spacer(modifier = Modifier.size(10.dp))

                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(Color.White),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Image(
                                painter = painterResource(R.drawable.google),
                                contentDescription = "Google",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text("Continue with Google", color = Color.Black)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Already have account?")
                        Text(
                            "Login",
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {})
                    }


                }

            }

        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked() {
    var showDatePicker = remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = selectedDate,
            onValueChange = { },
            label = { Text("DOB") },
            placeholder = {Text("Date of birth")},
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker.value = !showDatePicker.value }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clickable { showDatePicker.value = true }
        )

        if (showDatePicker.value) {
            Popup(
                onDismissRequest = { showDatePicker.value = false },
                alignment = Alignment.TopStart
            ) {
                Column(modifier = Modifier.padding(30.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = 64.dp)
                            .shadow(elevation = 4.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp)
                    ) {
                        DatePicker(
                            state = datePickerState,
                            showModeToggle = false
                        )
                        Button(onClick = { showDatePicker.value = false }) {
                            Text("Set date")
                        }
                    }
                }
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}