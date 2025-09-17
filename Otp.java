package com.example.axon;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class Otp extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp_main);
        setupOtpFields();
        View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText et = (EditText) v;
                if (hasFocus) {
                    et.setBackgroundResource(R.drawable.otp_field_focused);
                } else {
                    et.setBackgroundResource(R.drawable.otp_field);
                }
            }
        };
        EditText digit1 = findViewById(R.id.digit1);
        EditText digit2 = findViewById(R.id.digit2);
        EditText digit3 = findViewById(R.id.digit3);
        EditText digit4 = findViewById(R.id.digit4);
        digit1.setOnFocusChangeListener(focusChangeListener);
        digit2.setOnFocusChangeListener(focusChangeListener);
        digit3.setOnFocusChangeListener(focusChangeListener);
        digit4.setOnFocusChangeListener(focusChangeListener);
    }
    class OtpKeyListener implements View.OnKeyListener {
        private EditText currentField;
        private EditText previousField;

        public OtpKeyListener(EditText currentField, EditText previousField) {
            this.currentField = currentField;
            this.previousField = previousField;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (currentField.getText().toString().isEmpty() && previousField != null) {
                    previousField.requestFocus();
                    return true;
                }
            }
            return false;
        }
    }
    class OtpTextWatcher implements TextWatcher {
        private EditText currentField;
        private EditText nextField;

        public OtpTextWatcher(EditText currentField, EditText nextField) {
            this.currentField = currentField;
            this.nextField = nextField;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 1 && nextField != null) {
                nextField.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    }
    private void setupOtpFields() {
        EditText digit1 = findViewById(R.id.digit1);
        EditText digit2 = findViewById(R.id.digit2);
        EditText digit3 = findViewById(R.id.digit3);
        EditText digit4 = findViewById(R.id.digit4);

        digit1.addTextChangedListener(new OtpTextWatcher(digit1, digit2));
        digit2.addTextChangedListener(new OtpTextWatcher(digit2, digit3));
        digit3.addTextChangedListener(new OtpTextWatcher(digit3, digit4));
        digit4.addTextChangedListener(new OtpTextWatcher(digit4, null));

        digit2.setOnKeyListener(new OtpKeyListener(digit2, digit1));
        digit3.setOnKeyListener(new OtpKeyListener(digit3, digit2));
        digit4.setOnKeyListener(new OtpKeyListener(digit4, digit3));
    }

}
