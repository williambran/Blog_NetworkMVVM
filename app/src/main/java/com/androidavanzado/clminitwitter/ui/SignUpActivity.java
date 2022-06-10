package com.androidavanzado.clminitwitter.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidavanzado.clminitwitter.R;
import com.androidavanzado.clminitwitter.retrofit.MiniTwitterService;
import com.androidavanzado.clminitwitter.common.Constantes;
import com.androidavanzado.clminitwitter.common.SharePreferencesManager;
import com.androidavanzado.clminitwitter.retrofit.MiniTwitterClient;
import com.androidavanzado.clminitwitter.retrofit.request.RequestSignup;
import com.androidavanzado.clminitwitter.retrofit.response.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_signup;
    private EditText et_nombre;
    private  EditText et_correo;
    private  EditText et_password;
    private TextView tv_regresar;

    private MiniTwitterService miniTwitterService;
    private MiniTwitterClient miniTwitterClient;
    private RequestSignup requestSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        findView();
        findevent();
        retrofitInit();

    }

    private void retrofitInit() {
        miniTwitterClient = MiniTwitterClient.getInstance();
        miniTwitterService = miniTwitterClient.getminiTwitterService();


    }


    private void findevent() {
        btn_signup.setOnClickListener(this);
        tv_regresar.setOnClickListener(this);
    }

    private void findView() {
        btn_signup = findViewById(R.id.btn_signUp);
        tv_regresar = findViewById(R.id.tv_regresar);
        et_nombre = findViewById(R.id.et_nombre_SignUp);
        et_correo = findViewById(R.id.et_correo_signUp);
        et_password= findViewById(R.id.et_password_SignUp);
    }


    @Override
    public void onClick(View view) {

        int id= view.getId();
        switch (id) {
            case R.id.btn_signUp:
                entrar();
                break;
            case R.id.tv_regresar:
                goLogin();
                break;
        }
    }

    private void entrar() {
        String nombre = et_nombre.getText().toString();
        String correo = et_correo.getText().toString();
        String password= et_password.getText().toString();

        if(nombre.isEmpty()){
            et_nombre.setError("Campo nombre vacio");
        }else if (correo.isEmpty()){
            et_correo.setError("Campo correo vacio");
        }else if(password.isEmpty()||password.length()<4){
            et_password.setError("La cotraseña tiene que tener mas caracteres y no vacia");
        } else {
            String code = "UDEMYANDROID";
           requestSignup =  new RequestSignup(nombre, correo, password, code);
           Call<ResponseLogin> call = miniTwitterService.doSignUp(requestSignup);

           call.enqueue(new Callback<ResponseLogin>() {
               @Override
               public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                   if (response.isSuccessful()){
                       Toast.makeText(SignUpActivity.this, "El  regstor se realizo exitosamente", Toast.LENGTH_SHORT).show();

                       /** Colocamos aqui el sharepreference pa que nos guarde los datos de respuesta*/
                       SharePreferencesManager.setSometimeStringValue(Constantes.PREF_TOKEN, response.body().getToken());
                       SharePreferencesManager.setSometimeStringValue(Constantes.PREF_USERNAME, response.body().getUsername());
                       SharePreferencesManager.setSometimeStringValue(Constantes.PREF_EMAIL, response.body().getEmail());
                       SharePreferencesManager.setSometimeStringValue(Constantes.PREF_PHOTOURL, response.body().getPhotoUrl());
                       SharePreferencesManager.setSometimeStringValue(Constantes.PREF_CREATE, response.body().getCreated());
                       SharePreferencesManager.setSometimeBooleanValue(Constantes.PREF_ACTIVE, response.body().getActive());

                       Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
                       startActivity(intent);
                       finish();
                   }
               }

               @Override
               public void onFailure(Call<ResponseLogin> call, Throwable t) {

               }
           });
        }
    }

    private void goLogin() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);

    }

//  Se usa para despues de que se registra el usuario exitosamente, no pueda regresar a login, si no a salir de la aplicacion
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
