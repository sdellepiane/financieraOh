package pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import pe.financieraoh.projects.com.appfinancieraoh.domain.UserObservable;

public class LogInViewModel extends ViewModel {

    private UserObservable userObservable;

    public MutableLiveData<String> user = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    public boolean validateEnableButton(){
        return user.getValue() != null && password.getValue() != null;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
