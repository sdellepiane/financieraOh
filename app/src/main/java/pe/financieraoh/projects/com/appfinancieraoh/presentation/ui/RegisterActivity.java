package pe.financieraoh.projects.com.appfinancieraoh.presentation.ui;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import pe.financieraoh.projects.com.appfinancieraoh.R;
import pe.financieraoh.projects.com.appfinancieraoh.databinding.ActivityRegisterBinding;
import pe.financieraoh.projects.com.appfinancieraoh.domain.UserModel;
import pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel.register.RegisterNavigator;
import pe.financieraoh.projects.com.appfinancieraoh.presentation.viewmodel.register.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity implements RegisterNavigator {

    private int ZERO = 0;
    private String EMPTY = "";

    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initDatBinding();
        init();
        initDatabase();
    }

    private void init() {
        viewModel.setNavigator(this);
        setNameData();
        setLastName();
        setAgeData();
        binding.tviBirthdayLabel.setOnClickListener(view -> {
            Calendar calendar = Calendar.getInstance();
            int calendarYear = calendar.get(Calendar.YEAR);
            int calendarMonth = calendar.get(Calendar.MONTH);
            int calendarDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, (datePicker, year, month, day) -> {
                binding.tviBirthdayLabel.setText(String.format(getString(R.string.s_birthday_selected), String.valueOf(day),
                        formatNumberToDate(month + 1), String.valueOf(year)));
                viewModel.setBirthdayData(binding.tviBirthdayLabel.getText().toString());
            }, calendarYear, calendarMonth, calendarDayOfMonth);
            datePickerDialog.show();
        });

        binding.butRegister.setOnClickListener(view -> {
            UserModel model = new UserModel(binding.eteName.getText().toString(),
                    binding.eteLastName.getText().toString(),
                    Integer.parseInt(binding.eteAge.getText().toString()),
                    binding.tviBirthdayLabel.getText().toString());
            String completeName = binding.eteName.getText().toString() + " " + binding.eteLastName.getText().toString();
            mDatabase.child(completeName).setValue(model);
            clearFields();
        });
    }

    private void initDatabase() {
        mDatabase = FirebaseDatabase.getInstance().getReference("users");
    }

    private void initDatBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        binding.setLifecycleOwner(this);
    }

    private void setNameData() {
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

    private void setLastName() {
        binding.eteLastName.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT && !binding.eteLastName.getText().toString().isEmpty()) {
                viewModel.setLastNameData(binding.eteLastName.getText().toString());
            }
            return false;
        });

        binding.eteLastName.addTextChangedListener(new TextWatcher() {
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
                    viewModel.setLastNameData(null);
                } else {
                    viewModel.setLastNameData(editable.toString());
                }
            }
        });
    }

    private void setAgeData() {
        binding.eteAge.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_NEXT && !binding.eteAge.getText().toString().isEmpty()) {
                viewModel.setAgeData(Integer.parseInt(binding.eteAge.getText().toString()));
            }
            return false;
        });

        binding.eteAge.addTextChangedListener(new TextWatcher() {
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
                    viewModel.setAgeData(ZERO);
                } else {
                    viewModel.setAgeData(Integer.parseInt(editable.toString()));
                }
            }
        });
    }

    private void clearFields() {
        binding.eteName.setText(EMPTY);
        binding.eteLastName.setText(EMPTY);
        binding.eteAge.setText(EMPTY);
        binding.tviBirthdayLabel.setText(EMPTY);
    }

    private String formatNumberToDate(int number) {
        if (number < 10) {
            return String.format(getString(R.string.s_number_formatted_date), String.valueOf(ZERO), String.valueOf(number));
        } else {
            return String.valueOf(number);
        }
    }

    @Override
    public void enableButton(boolean enable) {
        binding.butRegister.setEnabled(enable);
    }
}
