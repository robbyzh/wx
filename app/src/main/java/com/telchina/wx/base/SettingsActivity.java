package com.telchina.wx.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import com.telchina.wx.R;

/**
 * Created by zg on 2015/8/12.
 */
public class SettingsActivity extends BaseActivity {

    private SharedPreferences.Editor editor = null;
    private SharedPreferences preferences;

    private EditText nameEditText;
    private EditText ageEditText;
    private CheckBox marriedCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences=getSharedPreferences("data",MODE_PRIVATE);

        nameEditText = (EditText) findViewById(R.id.settings_name);
        nameEditText.setText(preferences.getString("settings_name",""));

        ageEditText = (EditText) findViewById(R.id.settings_age);
        ageEditText.setText(String.valueOf(preferences.getInt("settings_age", 20)));

        marriedCheckBox = (CheckBox) findViewById(R.id.settings_married);
        marriedCheckBox.setChecked(preferences.getBoolean("settings_married",false));

        editor = getSharedPreferences("data", MODE_PRIVATE).edit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String nameString=nameEditText.getText().toString();
        String ageString=ageEditText.getText().toString();
        Boolean married=marriedCheckBox.isChecked();

        editor.putString("settings_name",nameString);
        editor.putInt("settings_age", Integer.parseInt(ageString));
        editor.putBoolean("settings_married", married);
        editor.commit();
    }
}
