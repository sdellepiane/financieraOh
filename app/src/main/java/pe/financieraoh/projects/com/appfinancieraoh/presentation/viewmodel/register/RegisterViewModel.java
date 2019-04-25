package pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel.register;

import android.arch.lifecycle.MutableLiveData;

import pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel.BaseViewModel;

public class RegisterViewModel extends BaseViewModel<RegisterNavigator> {

    public MutableLiveData<Boolean> enabledRegisterButton;
    private String name;
    private String lastName;
    private int age;
    private String birthday;

    public RegisterViewModel() {
        enabledRegisterButton = new MutableLiveData<>();
        enabledRegisterButton.setValue(false);
    }

    public void setNameData(String name) {
        this.name = name;
        updateEnabled();
    }

    public void setLastNameData(String lastName) {
        this.lastName = lastName;
        updateEnabled();
    }

    public void setAgeData(int age) {
        this.age = age;
        updateEnabled();
    }

    public void setBirthdayData(String birthday) {
        this.birthday = birthday;
        updateEnabled();
    }

    private void updateEnabled() {
        boolean enabled = name != null && !name.isEmpty() && lastName != null
                && !lastName.isEmpty() && age != 0 && birthday != null && !birthday.isEmpty();
        this.enabledRegisterButton.setValue(enabled);
    }

    public void onClick(){
        getNavigator().register(name, lastName, age, birthday);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
