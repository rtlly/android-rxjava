package com.example.androidhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class SPActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private EditText text;
    public static final String preferred_text_key = "EDIT_TEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        String preferred_text = sharedPreferences.getString(preferred_text_key, "");

        text = findViewById(R.id.edit_text);
        Button saveText = findViewById(R.id.save_text);

        text.setText(preferred_text);

        saveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTextToSharedPreferences();
            }
        });

        text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    private void saveTextToSharedPreferences() {
        String textValue = text.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferred_text_key, textValue);
        editor.commit();
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
