package com.telchina.wx;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.telchina.wx.base.BaseActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by zg on 2015/8/12.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText accountEditText;
    private EditText passwordEditText;
    private CheckBox rememberPassCheckBox;
    private Button loginButton;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        preferences= PreferenceManager.getDefaultSharedPreferences(this);

        accountEditText = (EditText) findViewById(R.id.login_account);
        passwordEditText = (EditText) findViewById(R.id.login_password);
        rememberPassCheckBox = (CheckBox) findViewById(R.id.login_remember_pass);
        loginButton = (Button) findViewById(R.id.login_button);

        boolean isRemember=preferences.getBoolean("remember_password",false);

        if(isRemember){
            String inputText=preferences.getString("login_name","");
            accountEditText.setText(inputText);
            accountEditText.setSelection(inputText.length());

            String password=preferences.getString("login_pass","");
            passwordEditText.setText(password);
            rememberPassCheckBox.setChecked(true);
        }

        loginButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String account = accountEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        editor=preferences.edit();
        if(rememberPassCheckBox.isChecked()){
            editor.putString("login_name",account);
            editor.putString("login_pass",password);
            editor.putBoolean("remember_password",true);
        } else {
            editor.clear();
        }
        editor.commit();

        if ("admin".equals(account) && "123".equals(password)) {

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "wrong", Toast.LENGTH_LONG).show();
        }
    }

    @Deprecated
    private void save(String data) {
        FileOutputStream out = null;
        BufferedWriter writer = null;

        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Deprecated
    private String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
}
