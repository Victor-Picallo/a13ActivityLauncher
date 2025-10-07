package com.example.a13activityresultlauncher;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    TextView resultado;

    final static int Request_Code = 14;

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button botonIrSegunda = findViewById(R.id.botonActividad1);
        editText1 = findViewById(R.id.obtenerNumero1);
        editText2 = findViewById(R.id.obtenerNumero2);
        resultado = findViewById(R.id.resultado);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Toast.makeText(this, "En el regreso", Toast.LENGTH_SHORT).show();
                            Integer suma = data.getIntExtra("suma", 0);
                            resultado.findViewById(R.id.resultado);
                            if (suma != null) {
                                resultado.setText(String.valueOf(suma));
                            } else
                                Toast.makeText(this, "Error al obtener el resultado", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        botonIrSegunda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSegundaActividad();
            }
        });
                /*Intent intent = new Intent(MainActivity.this, SegundaActivity.class);

                String texto1 = editText1.getText().toString();
                String texto2 = editText2.getText().toString();

                if (TextUtils.isEmpty(texto1) || TextUtils.isEmpty(texto2)) {
                    Toast.makeText(MainActivity.this, "No dejes cajas sin numero", Toast.LENGTH_SHORT).show();
                    return;
                }

                int numero1 = Integer.parseInt(texto1);
                int numero2 = Integer.parseInt(texto2);

                intent.putExtra("numero1", numero1);
                intent.putExtra("numero2", numero2);

                //Deprecado
                //startActivityForResult(intent, Request_Code);
            }
        });*/
    }

    //METODO CLICK DEL BOTON
    private void abrirSegundaActividad() {
        Intent intent = new Intent(MainActivity.this, SegundaActivity.class);

        String texto1 = editText1.getText().toString();
        String texto2 = editText2.getText().toString();

        if (TextUtils.isEmpty(texto1) || TextUtils.isEmpty(texto2)) {
            Toast.makeText(MainActivity.this, "No dejes cajas sin numero", Toast.LENGTH_SHORT).show();
            return;
        }

        int numero1 = Integer.parseInt(texto1);
        int numero2 = Integer.parseInt(texto2);

        intent.putExtra("numero1", numero1);
        intent.putExtra("numero2", numero2);

        launcher.launch(intent);
    }
    

    //METODO DE REGRESO
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Request_Code && resultCode == RESULT_OK) {
            int suma = data.getIntExtra("suma", 0);
            resultado = findViewById(R.id.resultado);
            resultado.setText(String.valueOf(suma));
        }
    }
}