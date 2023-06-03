package com.example.practicalwork5;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText login = findViewById(R.id.editTextLogin);
        EditText password = findViewById(R.id.editTextTextPassword);

        Button firstButton = findViewById(R.id.loginButton);

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateLogin(login.getText().toString())) {
                    login.setError("Неправильный формат почты");
                    return;
                }
                if (!validatePassword(password.getText().toString())) {
                    password.setError("Неправильный формат пароля \n " +
                            "- Длина от 8 до 20 символов\n" +
                            "- Содержит как минимум одну строчную букву\n" +
                            "- Содержит как минимум одну заглавную букву\n" +
                            "- Содержит как минимум одну цифру\n");
                    return;
                }

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("mail", login.getText());
                startActivity(intent);
            }
        });
    }

    private boolean validateLogin(String login) {
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        return !TextUtils.isEmpty(login) && emailPattern.matcher(login).matches();
    }

    private boolean validatePassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)\\S{8,20}$";
        return !TextUtils.isEmpty(password) && password.matches(passwordRegex);
    }
}