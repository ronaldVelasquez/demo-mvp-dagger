package com.ronaldvelasquez.mvpdagger.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ronaldvelasquez.mvpdagger.R;
import com.ronaldvelasquez.mvpdagger.root.MvpDaggerApplication;

import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements LoginMvp.View {

    //La vista se injecta desde el modulo
    @Inject
    LoginMvp.Presenter presenter;

    private EditText edtxtFirstName;
    private EditText edtxtLastName;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Injectar la actividad
        ((MvpDaggerApplication) getApplication()).getApplicationComponent().inject(this);
        // injeccion de vista
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Se inyecta en el on Resume para que siempre se cree de manera dinamica y el presentador siempre tenga un contexto
        presenter.setView(this);
        presenter.getCurrentUser();
    }

    private void initView() {
        edtxtFirstName = findViewById(R.id.edtxt_first_name);
        edtxtLastName = findViewById(R.id.edtxt_last_name);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginButtonClicked();
            }
        });
    }

    @Override
    public String getFirstName() {
        return edtxtFirstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return edtxtLastName.getText().toString();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this, "Error: Usuario no disponible", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Nombre o apellido ingresado de manera incorrecta", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(this, "Usuario guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFirstName(String firstName) {
        edtxtFirstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        edtxtLastName.setText(lastName);
    }
}
