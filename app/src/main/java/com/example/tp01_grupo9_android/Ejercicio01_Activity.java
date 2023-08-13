package com.example.tp01_grupo9_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ejercicio01_Activity extends AppCompatActivity {

    private EditText tvNumber1;
    private EditText tvNumber2;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio01);

        // me conecto con la vista xml y obtengo cada id.. cada elemento de la vista la relacionaremos
        // a cada edit Text y button
        tvNumber1 = findViewById(R.id.tvNumber1);
        tvNumber2 = findViewById(R.id.tvNumber2);
        tvResultado = findViewById(R.id.tvResultado);
    }

    // Este metodo realiza la suma
    public void Suma(View miVista){

        String valor1 = tvNumber1.getText().toString();
        String valor2 = tvNumber2.getText().toString();

        // Crear validaciones
        if(valor1.isEmpty()){
            Toast.makeText(this, "Debe de ingresar un valor en la primer casilla", Toast.LENGTH_LONG).show();
            return;
        }
        if(valor2.isEmpty()){
            Toast.makeText(this, "Debe de ingresar un valor en la segunda casilla", Toast.LENGTH_LONG).show();
            return;
        }

        //-----------------------------------------------------//

        int num1 =  Integer.parseInt(valor1);
        int num2 =  Integer.parseInt(valor2);

        int suma = num1 + num2;
        String result = String.valueOf(suma); // Convertir a string

        tvResultado.setText("El resultado es:" + result);
    }


    public void Volver(View view)
    {
        startActivity(new Intent(Ejercicio01_Activity.this, MainActivity.class));
    }
}