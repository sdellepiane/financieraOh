package pe.financieraoh.projects.com.appfinancieraoh.domain;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class UserObservable extends BaseObservable {

    private UserModel model = new UserModel();
    private MutableLiveData<UserModel> logInClick =  new MutableLiveData<>();

    @Bindable
    public boolean isValid(){
        return !model.getUser().isEmpty() && !model.getPassword().isEmpty();
    }

    public void onClick(){
        if(isValid()){
            logInClick.setValue(model);
        }
    }

    public MutableLiveData<UserModel> getLogInFields() {
        return logInClick;
    }

    public UserModel getModel() {
        return model;
    }
}
