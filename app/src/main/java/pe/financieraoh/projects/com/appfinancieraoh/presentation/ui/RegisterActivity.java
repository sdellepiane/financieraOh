package pe.financieraoh.projects.com.appfinancieraoh.presentation.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;

import pe.financieraoh.projects.com.appfinancieraoh.R;
import pe.financieraoh.projects.com.appfinancieraoh.databinding.ActivityRegisterBinding;
import pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel.register.RegisterNavigator;
import pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel.register.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity implements RegisterNavigator {

    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initDatBinding();
        init();
    }

    private void init() {
        viewModel.setNavigator(this);
        setNameData();
        setLastName();
        setAgeData();
        setBirthdayData();
    }

    private void initDatBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        binding.setLifecycleOwner(this);
    }

    private void setNameData(){
        binding.eteName.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT && !binding.eteName.getText().toString().isEmpty()) {
                viewModel.setNameData(binding.eteName.getText().toString());
            }
            return false;
        });

        binding.eteName.addTextChangedListener(new TextWatcher() {
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
                    viewModel.setNameData(null);
                } else {
                    viewModel.setNameData(editable.toString());
                }
            }
        });
    }

    private void setLastName(){

    }

    private void setAgeData(){

    }

    private void setBirthdayData(){

    }

    @Override
    public void register(String name, String lastname, int age, String birthday) {

    }
}
