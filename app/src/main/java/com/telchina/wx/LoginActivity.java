package com.telchina.wx;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.telchina.wx.base.BaseActivity;

/**
 * Created by zg on 2015/8/12.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText accountEditText;
    private EditText passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LoginActivity","onCreate");
        setContentView(R.layout.activity_login);
        accountEditText = (EditText) findViewById(R.id.login_account);
        passwordEditText = (EditText) findViewById(R.id.login_password);
        loginButton = (Button) findViewById(R.id.login_button);

        loginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String account = accountEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if ("admin".equals(account) && "123".equals(password)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "wrong", Toast.LENGTH_LONG).show();
        }
    }
}
