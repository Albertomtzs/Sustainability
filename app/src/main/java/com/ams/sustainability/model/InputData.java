package com.ams.sustainability.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ams.sustainability.MainActivity;
import com.ams.sustainability.R;
import com.ams.sustainability.ui.ResultsPage;

import java.util.ArrayList;
import java.util.List;

public class InputData extends AppCompatActivity {

    private int stage;
    private int backlines;

    private TextView title;
    private Button back, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);

        LinearLayout layout = findViewById(R.id.scrollLayout);
        title = (TextView) findViewById(R.id.title);
        back = (Button) findViewById(R.id.btnBack);
        next = (Button) findViewById(R.id.btnNext);

        Intent intent = getIntent();
        stage = intent.getIntExtra("NEXT_STAGE", 0);
        backlines = intent.getIntExtra("BACK_LINES", 0);

        switch (stage) {
            case 0:
                title.setText(R.string.vivienda);
                back.setVisibility(View.INVISIBLE);
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_vivienda, null));
                break;
            case 1:
                title.setText(R.string.alimentacion);
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_comida, null));
                break;
            case 2:
                title.setText(R.string.ropa);
                layout.addView(getLayoutInflater().inflate(R.layout.preguntas_ropa, null));
                break;
            case 3:
                title.setText(R.string.ropa);
                //layout.addView(getLayoutInflater().inflate(R.layout.preguntas_transporte,null));
                break;
        }
    }

    public void toNext(View view) {

        LinearLayout layout = findViewById(R.id.scrollLayout);
        List<View> children = getAllChildrenBFS(layout);
        ArrayList<RadioGroup> questions = new ArrayList<>();

        for(View v : children) {
            if (v instanceof RadioGroup) {
                questions.add((RadioGroup) v);
            }
        }

        for(RadioGroup r : questions){
            if(r.getCheckedRadioButtonId() != -1) {
                String category = (String) r.getTag();
                RadioButton b = findViewById(r.getCheckedRadioButtonId());
                String n = (String) b.getTag();
                Float answer = Float.parseFloat(n);
                backlines++;
            }}

        stage++;
        Intent next;
        if(stage == 4){next = new Intent(this, ResultsPage.class);}
        else {next = new Intent(this, InputData.class);
            next.putExtra("NEXT_STAGE", stage);
        }
        next.putExtra("BACK_LINES",backlines);
        startActivity(next);

    }

    private List<View> getAllChildrenBFS(View v) {
        List<View> visited = new ArrayList<>();
        List<View> unvisited = new ArrayList<>();
        unvisited.add(v);

        while (!unvisited.isEmpty()) {
            View child = unvisited.remove(0);
            visited.add(child);
            if (!(child instanceof ViewGroup)) continue;
            ViewGroup group = (ViewGroup) child;
            final int childCount = group.getChildCount();
            for (int i=0; i<childCount; i++) unvisited.add(group.getChildAt(i));
        }

        return visited;
    }

    public void back(View view)
    {
        if(stage==0) {
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
        }
        else{
            Intent back = new Intent(this, InputData.class);
            stage--;
            back.putExtra("NEXT_STAGE",stage);
            startActivity(back);

        }
    }
}
