package com.ams.sustainability.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ams.sustainability.R;
import com.ams.sustainability.model.usecases.CarbonFootprintCalculator;
import com.ams.sustainability.ui.navigation.NavigationFragment;

public class GetStartedCalculator extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

    private Button CALCULAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_calculator);

        CALCULAR = (Button) findViewById(R.id.btnCalcular);

        // Obtener instancia de SharedPreferences
        sharedPreferences = getSharedPreferences("MyPreferencesGetStartedCalculator", MODE_PRIVATE);

        boolean isBtnNextClicked = sharedPreferences.getBoolean("btnNextClicked", false);

        // Verificar el estado del botón
        if (isBtnNextClicked) {
            // Si el botón ya ha sido pulsado, iniciar la nueva actividad directamente
            startActivity(new Intent(this, NavigationFragment.class));
            finish(); // Finalizar la actividad actual
        }

        CALCULAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("btnNextClicked", true);
                editor.apply();
                Intent intent = new Intent(GetStartedCalculator.this, CarbonFootprintCalculator.class);
                startActivity(intent);
                finish();
            }
        });
    }
}