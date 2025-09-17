package com.example.axon;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        CheckBox chkRemember = findViewById(R.id.chkRemember);
        chkRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                if(chkRemember.isChecked()){
                    chkRemember.setButtonDrawable(R.drawable.check_bg);
                }
                else{
                    chkRemember.setButtonDrawable(R.drawable.uncheck_bg);
                }
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            chkRemember.setButtonTintList(null); //remov default itnitng for button
        }
        Button btnLogin = findViewById(R.id.btnLogin);

        // animation
        btnLogin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // Scale down when pressed
                        v.animate()
                                .scaleX(0.95f)
                                .scaleY(0.95f)
                                .setDuration(100)
                                .start();
                        v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        // Scale back to normal when released
                        v.animate()
                                .scaleX(1f)
                                .scaleY(1f)
                                .setDuration(100)
                                .start();
                        break;
                }
                return false; // Return false to allow click event to proceed
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Otp.class);
                startActivity(intent);
                finish();
            }
        });
    }
}