package com.androidavanzado.clminitwitter.retrofit;

import com.androidavanzado.clminitwitter.retrofit.request.RequestLogin;
import com.androidavanzado.clminitwitter.retrofit.request.RequestSignup;
import com.androidavanzado.clminitwitter.retrofit.response.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

//android va a consumir desde aqui. van las peticiones
//En las Call<Le indicamos que vamos a recibir una llamada de tipo ResponseLogin>
public interface MiniTwitterService {

    @POST("auth/login")
    Call<ResponseLogin> doLogin(@Body RequestLogin requestLogin);

    @POST("auth/signup")
    Call<ResponseLogin> doSignUp(@Body RequestSignup requestSignup);
}

