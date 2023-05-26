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

        sharedPreferences = getSharedPreferences("MyPreferencesGetStartedCalculator", MODE_PRIVATE);

        boolean isBtnNextClicked = sharedPreferences.getBoolean("btnNextClicked", false);

        if (isBtnNextClicked) {
            startActivity(new Intent(this, NavigationFragment.class));
            finish();
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