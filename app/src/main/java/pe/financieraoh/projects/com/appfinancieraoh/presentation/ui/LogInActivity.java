package pe.financieraoh.projects.com.appfinancieraoh.presentation.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pe.financieraoh.projects.com.appfinancieraoh.R;
import pe.financieraoh.projects.com.appfinancieraoh.databinding.ActivityLogInBinding;
import pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel.LogInViewModel;

public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private LogInViewModel logInViewModel;
    private ActivityLogInBinding activityLogInBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        initDatBinding();
        init();
    }

    private void init() {
        mAuth = FirebaseAuth.getInstance();
        logInViewModel.user.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String user) {
                logInViewModel.user.setValue(user);
            }
        });

        logInViewModel.password.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String password) {
                logInViewModel.password.setValue(password);
            }
        });
    }

    private void initDatBinding() {
        activityLogInBinding = DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        logInViewModel = ViewModelProviders.of(this).get(LogInViewModel.class);
        activityLogInBinding.setLifecycleOwner(this);
        activityLogInBinding.setViewModel(logInViewModel);
    }

    private void onClick() {
        String user = activityLogInBinding.eteUser.getText().toString();
        String password = activityLogInBinding.etePassword.getText().toString();
        startSignIn(user, password);
    }

    private void startSignIn(final String user, final String password) {
        mAuth.signInWithEmailAndPassword(user, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            createUser(user, password);
                        }
                    }
                });
    }

    private void createUser(String user, String password) {
        mAuth.createUserWithEmailAndPassword(user, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
