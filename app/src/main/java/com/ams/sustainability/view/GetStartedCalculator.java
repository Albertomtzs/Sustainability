package com.ams.sustainability.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ams.sustainability.R;
import com.ams.sustainability.model.usecases.CarbonFootprintCalculator;

public class GetStartedCalculator extends AppCompatActivity {

    private Button CALCULAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_calculator);

        CALCULAR = (Button) findViewById(R.id.btnCalcular);

        CALCULAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetStartedCalculator.this, CarbonFootprintCalculator.class);
                startActivity(intent);
                finish();
            }
        });
    }
}