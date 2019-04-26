package pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel.login;

import android.arch.lifecycle.MutableLiveData;

import pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel.BaseViewModel;

public class LogInViewModel extends BaseViewModel<LogInNavigator> {

    public MutableLiveData<Boolean> enabledLogInButton;
    private String user;
    private String password;

    public LogInViewModel(){
        enabledLogInButton = new MutableLiveData<>();
        enabledLogInButton.setValue(false);
    }

    public void setUserData(String user) {
        this.user = user;
        updateEnabled();
    }

    public void setPasswordData(String password) {
        this.password = password;
        updateEnabled();
    }

    private void updateEnabled() {
        boolean enabled = user != null && !user.isEmpty() && password != null && !password.isEmpty();
        this.enabledLogInButton.setValue(enabled);
        getNavigator().enableButton(enabled);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
