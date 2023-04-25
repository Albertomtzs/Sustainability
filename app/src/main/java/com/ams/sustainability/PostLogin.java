package com.ams.sustainability;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.ams.sustainability.model.InputData;

public class PostLogin extends AppCompatActivity {

    private Button CALCULAR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_login);

        CALCULAR = (Button) findViewById(R.id.btnCalcular);

        CALCULAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostLogin.this, InputData.class);
                startActivity(intent);
                finish();
            }
        });
    }
}