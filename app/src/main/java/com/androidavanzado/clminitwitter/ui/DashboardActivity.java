package com.androidavanzado.clminitwitter.ui;

import android.os.Bundle;
import android.view.View;

import com.androidavanzado.clminitwitter.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class DashboardActivity extends AppCompatActivity {

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        BottomNavigationView navView = findViewById(R.id.nav_view); // el bootomnaviation ...el de abajo
        getSupportActionBar().hide();

        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);//este metodo findNavController localiza al NavController asociado con la vista

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               NuevoTweetDialogFragment dialog = new NuevoTweetDialogFragment();
               dialog.show(getSupportFragmentManager(), "NuevoTwieetDialogFragment");
            }
        });

      /* String token= SharePreferencesManager.getSometimeStringValue(Constantes.PREF_TOKEN);
        Toast.makeText(MyApp.getContext(), "Token"+ token, Toast.LENGTH_SHORT).show();*/ //// SE MANDA A TRAER EL SHARE, PERO SE PUEDE HACER UNA CLASE DE AUTH PARA INTEGRAR EL INTERCEPTOR
    }

}
