package com.ams.sustainability.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.ams.sustainability.R;

public class GetStartedCarbonFootprint extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_carbon_footprint);

        startButton = findViewById(R.id.startButton);

       /* // Obtener instancia de SharedPreferences
        sharedPreferences = getSharedPreferences("MyPreferencesGetStartedCarbonFootprint", MODE_PRIVATE);

        boolean isBtnNextClicked = sharedPreferences.getBoolean("btnNextClicked", false);

        // Verificar el estado del botón
        if (isBtnNextClicked) {
            // Si el botón ya ha sido pulsado, iniciar la nueva actividad directamente
            startActivity(new Intent(this, MainLogin.class));
            finish(); // Finalizar la actividad actual
        }*/

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GetStartedCarbonFootprint.this, MainLogin.class);
                startActivity(i);
                finish();
            }
        });
    }
}