package pe.financieraoh.projects.com.appfinancieraoh.presentation.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pe.financieraoh.projects.com.appfinancieraoh.R;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private int SPLASH_SCREEN = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (currentUser == null) {
                    startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                } else {
                    startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                }
                finish();
            }
        }, SPLASH_SCREEN);




    }
}
