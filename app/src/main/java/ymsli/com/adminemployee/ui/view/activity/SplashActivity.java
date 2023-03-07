package ymsli.com.adminemployee.ui.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import dagger.hilt.android.AndroidEntryPoint;
import ymsli.com.adminemployee.R;


//Launcher activity
@AndroidEntryPoint
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash);

        //Adding a delay on splash for smooth screen transition
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //intent is used for going form one activity to another
                Intent intent = new Intent(getApplicationContext(), LoginRegisterActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}