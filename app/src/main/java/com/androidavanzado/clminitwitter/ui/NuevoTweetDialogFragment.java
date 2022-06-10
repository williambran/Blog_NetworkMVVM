package com.androidavanzado.clminitwitter.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.androidavanzado.clminitwitter.R;
import com.androidavanzado.clminitwitter.common.Constantes;
import com.androidavanzado.clminitwitter.common.SharePreferencesManager;
import com.androidavanzado.clminitwitter.dataRepositorio.TweetViewModel;
import com.androidavanzado.clminitwitter.retrofit.AuthTwitterClient;
import com.androidavanzado.clminitwitter.retrofit.AuthTwitterService;
import com.androidavanzado.clminitwitter.retrofit.response.Tweet;
import com.bumptech.glide.Glide;

public class NuevoTweetDialogFragment extends DialogFragment implements View.OnClickListener {
    private ImageView ivClose, iv_Avatar;
    private Button btnTwittear;
    private EditText etMensaje;
   // private Dialog nuevodialogFragment;

    private AuthTwitterService authTwitterService;
    private AuthTwitterClient authTwitterClient;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogFragment);
       getDialog();                                                    // obtener el dialogo de este NuevoTweetDialogFragment, para cerrarlo completamente cuando le den la tacha

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.nuevo_tweet_full_dialog, container, false);

        ivClose = view.findViewById(R.id.imageViewclose);
        iv_Avatar = view.findViewById(R.id.imageViewAvatarnuevotweet);
        btnTwittear = view.findViewById(R.id.buttonTwittear);
        etMensaje = view.findViewById(R.id.editTextMensaje);

        // Eventos
        btnTwittear.setOnClickListener(this);
        ivClose.setOnClickListener(this);

        String photo = SharePreferencesManager.getSometimeStringValue(Constantes.PREF_PHOTOURL);
        Glide.with(getActivity())
                .load("https://www.minitwitter.com/apiv1/uploads/photos/" + photo)
                .into(iv_Avatar);
        return view;


    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        String menssaje = etMensaje.getText().toString();

        if (id == R.id.buttonTwittear) {
            if (menssaje.isEmpty()) {
                Toast.makeText(getActivity(), "Porfas escriba algo", Toast.LENGTH_SHORT).show();
            } else {
                TweetViewModel tweetViewModel = ViewModelProviders.of(getActivity()).get(TweetViewModel.class);        //Para poder usar los metodos del view model, que se comunicara con el contenedor
                tweetViewModel.createtweet(menssaje);
                getDialog().dismiss();
            }

        } else {
            if(menssaje!=null){
            showDialogConfirm();
            }else{
                getDialog().dismiss();
            }
        }

    }

    private void showDialogConfirm() {                                              //Dialogo sencillo, en pruebaUde un dialogo diseñado probpio
//        https://developer.android.com/guide/topics/ui/dialogs?hl=es-419        De aqui tome esto, y es para crear una alerta cuando le den cancelar creacion de tweet.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Chain together various setter methods to set the dialog characteristics
        builder.setMessage("El tweets se borrara.¿Esta seguro de salir? ")
                .setTitle("Borrar Tweet")
    //    AlertDialog dialog = builder.create();

        // Add the buttons
        .setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                dialog.dismiss();
                getDialog().dismiss();

            }
        });
        builder.setNegativeButton("Conservar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });
        builder.show();

    }
}