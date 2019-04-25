package pe.financieraoh.projects.com.appfinancieraoh.presentation.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pe.financieraoh.projects.com.appfinancieraoh.R;
import pe.financieraoh.projects.com.appfinancieraoh.databinding.ActivityLogInBinding;
import pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel.login.LogInNavigator;
import pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel.login.LogInViewModel;

public class LogInActivity extends AppCompatActivity implements LogInNavigator {

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
        logInViewModel.setNavigator(this);
        setUserData();
        setPasswordData();
    }

    private void setUserData() {
        activityLogInBinding.eteUser.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT && !activityLogInBinding.eteUser.getText().toString().isEmpty()) {
                logInViewModel.setUserData(activityLogInBinding.eteUser.getText().toString());
            }
            return false;
        });

        activityLogInBinding.eteUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Nothing to do
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Nothing to do
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    logInViewModel.setUserData(null);
                } else {
                    logInViewModel.setUserData(editable.toString());
                }
            }
        });
    }

    private void setPasswordData() {
        activityLogInBinding.etePassword.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE && !activityLogInBinding.etePassword.getText().toString().isEmpty()) {
                logInViewModel.setPasswordData(activityLogInBinding.etePassword.getText().toString());
            }
            return false;
        });

        activityLogInBinding.etePassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Nothing to do
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //Nothing to do
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().isEmpty()) {
                    logInViewModel.setPasswordData(null);
                } else {
                    logInViewModel.setPasswordData(editable.toString());
                }
            }
        });
    }

    private void initDatBinding() {
        activityLogInBinding = DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        logInViewModel = ViewModelProviders.of(this).get(LogInViewModel.class);
        activityLogInBinding.setLifecycleOwner(this);
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

    @Override
    public void logIn(String user, String password) {
        startSignIn(user, password);
    }
}
