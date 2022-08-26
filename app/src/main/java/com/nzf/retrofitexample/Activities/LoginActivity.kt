package com.nzf.retrofitexample.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nzf.retrofitexample.Api.RetrofitClient
import com.nzf.retrofitexample.Models.LoginParam
import com.nzf.retrofitexample.Models.LoginResponse
import com.nzf.retrofitexample.R
import com.nzf.retrofitexample.Storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.editTextEmail
import kotlinx.android.synthetic.main.activity_login.editTextPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editTextEmail.setText("eve.holt@reqres.in")
        editTextPassword.setText("cityslicka")

        buttonLogin.setOnClickListener{
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if(email.isEmpty()){
                editTextEmail.error ="Email Required"
                editTextEmail.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty()){
                editTextPassword.error ="Password Required"
                editTextPassword.requestFocus()
                return@setOnClickListener
            }

            RetrofitClient.instance.userLogin(LoginParam(email,password))
                .enqueue(object: Callback<LoginResponse>{
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful){
                            if (response.code()==200) {
                                Toast.makeText(
                                    this@LoginActivity,
                                    "to next page",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }else{
                                Toast.makeText(
                                    this@LoginActivity,
                                    response.message(),
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                        }else{
                            Toast.makeText(this@LoginActivity, response.message(), Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message,Toast.LENGTH_SHORT).show()

                    }


                } )
        }
    }

    override fun onStart() {
        super.onStart()
        if (SharedPrefManager.getInstance(this)!!.isLoggedIn){

        }
    }
}