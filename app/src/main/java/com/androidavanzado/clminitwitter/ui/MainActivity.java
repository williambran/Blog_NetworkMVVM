package com.androidavanzado.clminitwitter.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidavanzado.clminitwitter.R;
import com.androidavanzado.clminitwitter.retrofit.MiniTwitterService;
import com.androidavanzado.clminitwitter.common.Constantes;
import com.androidavanzado.clminitwitter.common.SharePreferencesManager;
import com.androidavanzado.clminitwitter.retrofit.MiniTwitterClient;
import com.androidavanzado.clminitwitter.retrofit.request.RequestLogin;
import com.androidavanzado.clminitwitter.retrofit.response.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG ="wito" ;
    private Button btn_login;
    private Button btn_createCuenta;
    private EditText et_nombre;
    private EditText et_password;
    private EditText et_correo;

    public MiniTwitterClient miniTwitterClient;
    public MiniTwitterService miniTwitterService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        findevent();
        RetrofitInit();


    }

    private void RetrofitInit() {
        miniTwitterClient = MiniTwitterClient.getInstance();
        miniTwitterService = miniTwitterClient.getminiTwitterService(); // No poner MiniTwitterClient, con mayusculo, "nombre d ela clase" por que solo se puede instanciar una vez, SINGLETON

    }


    private void findView() {
        btn_login = findViewById(R.id.btn_login);
        et_nombre = findViewById(R.id.et_nombre);
        et_correo = findViewById(R.id.et_correo);
        et_password = findViewById(R.id.et_password);
        btn_createCuenta = findViewById(R.id.btn_crearCuenta);
    }

    private void findevent() {
        btn_login.setOnClickListener(this);
        btn_createCuenta.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id) {
            case R.id.btn_login:
                goToLogin();
                break;
            case R.id.btn_crearCuenta:
                gotoSignup();
                break;
        }
    }

    private void goToLogin() {
        String email = et_correo.getText().toString();
        String password = et_password.getText().toString();

        if (email.isEmpty()) {
            // Snackbar.make( "Campo vacio", Snackbar.LENGTH_LONG).show();
            et_correo.setError("campo vacio no");
        } else if (password.isEmpty()) {
            et_password.setError("Contraseña requerida");
        } else {
//            peticion de login, mandamos a traer su constructor y le pasamos email y pass // el metodo miniTwitterService.doLogin recibe un requestLogin, y se lo pasamos aqui
            RequestLogin requestLogin = new RequestLogin(email, password);
            Call<ResponseLogin> call = miniTwitterService.doLogin(requestLogin);

            call.enqueue(new Callback<ResponseLogin>() { // en cola...
                @Override
                public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) { //                    aqui puede ir que conexion queremos escoger
                    if (response.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Secion iniciada", Toast.LENGTH_SHORT).show();

                        /** Colocamos aqui el sharepreference pa que nos guarde los datos de respuesta... los Constantes.PREF_TOKEN -- son la clave,  lo que sigue es el valor */
                        SharePreferencesManager.setSometimeStringValue(Constantes.PREF_TOKEN, response.body().getToken());
                        SharePreferencesManager.setSometimeStringValue(Constantes.PREF_USERNAME, response.body().getUsername());
                        SharePreferencesManager.setSometimeStringValue(Constantes.PREF_EMAIL, response.body().getEmail());
                        SharePreferencesManager.setSometimeStringValue(Constantes.PREF_PHOTOURL, response.body().getPhotoUrl());
                        SharePreferencesManager.setSometimeStringValue(Constantes.PREF_CREATE, response.body().getCreated());
                        SharePreferencesManager.setSometimeBooleanValue(Constantes.PREF_ACTIVE, response.body().getActive());


                        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    }else
                    Toast.makeText(MainActivity.this, "Revise sus datos ", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<ResponseLogin> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Problemas de conexion , intenetelo de nuevo puto"+ t, Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "wito"+ t);

                }
            });
        }
    }

    private void gotoSignup() {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
