package com.example.a13activityresultlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SegundaActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        Button botonIrAPrimera = findViewById(R.id.botonActividad2);

        textView = findViewById(R.id.textoActividad2Derecha);
        textView2 = findViewById(R.id.textoActividad2Izquierda);

        Intent intent = getIntent();

        int numero1 = intent.getIntExtra("numero1", 0);
        int numero2 = intent.getIntExtra("numero2", 0);

        textView.setText(String.valueOf(numero1));
        textView2.setText(String.valueOf(numero2));

        int suma = numero1 + numero2;

        botonIrAPrimera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("suma", suma);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}