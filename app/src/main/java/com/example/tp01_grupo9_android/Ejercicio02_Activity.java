package com.example.tp01_grupo9_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Ejercicio02_Activity extends AppCompatActivity {
    private TextView tvResultado;
    private String primerNumero = "";
    private boolean calculo = false;
    private boolean encender = false;
    private Button [] numeros = new Button[10] ;
    private Button [] operaciones = new Button[10] ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio02);

        declaraciones();
        ingresarNumeros();
        operacionesMatematicas();
        funcionesExtras();
    }

    private void declaraciones(){
        numeros[0] = findViewById(R.id.num0);
        numeros[1] = findViewById(R.id.num1);
        numeros[2] = findViewById(R.id.num2);
        numeros[3] = findViewById(R.id.num3);
        numeros[4] = findViewById(R.id.num4);
        numeros[5] = findViewById(R.id.num5);
        numeros[6] = findViewById(R.id.num6);
        numeros[7] = findViewById(R.id.num7);
        numeros[8] = findViewById(R.id.num8);
        numeros[9] = findViewById(R.id.num9);

        operaciones[0] = findViewById(R.id.suma);
        operaciones[1] = findViewById(R.id.resta);
        operaciones[2] = findViewById(R.id.multiplicar);
        operaciones[3] = findViewById(R.id.division);
        operaciones[4] = findViewById(R.id.igual);
        operaciones[5] = findViewById(R.id.cambioSigno);
        operaciones[6] = findViewById(R.id.punto);
        operaciones[7] = findViewById(R.id.del);
        operaciones[8] = findViewById(R.id.on);
        operaciones[9] = findViewById(R.id.off);

        tvResultado = findViewById(R.id.mostrarResultado);
    }
    private void ingresarNumeros(){

        for (int i = 0; i < numeros.length; i++) {
            int finalI = i;
            numeros[i].setOnClickListener(view -> {
                if(encender){
                    Toast.makeText(this, "Encienda la calculadora", Toast.LENGTH_SHORT).show();
                    return;
                }

                String numeroActual = numeros[finalI].getText().toString();

                String valorInicial = tvResultado.getText().toString();
                float cero = Float.parseFloat(valorInicial);

                if(calculo){
                    tvResultado.setText("");
                    calculo = false;
                }

                if (cero == 0) {
                    tvResultado.setText(numeroActual);
                } else {
                    tvResultado.append(numeroActual);
                }
            });
        }
    }

    //---------------------------------------------------------//
    private String ultimaOperacion = "";
    private String operacionActual = "";
    private boolean operar = false;
    private void operacionesMatematicas(){

        // SUMA-RESTA-MULTIPLICACION-DIVISION
        String[] operadoresMate = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            operaciones[i].setOnClickListener(view -> {

                if(encender){
                    Toast.makeText(this, "Encienda la calculadora", Toast.LENGTH_SHORT).show();
                    return;
                }

                operacionActual = operadoresMate[finalI];

                if (operar) {
                    calcular(ultimaOperacion, "");
                }

                if (ultimaOperacion == "") {
                    operar = true;
                    ultimaOperacion = operadoresMate[finalI];
                    primerNumero = tvResultado.getText().toString();
                    tvResultado.setText("0");
                }
            });
        }

        // IGUAL
        operaciones[4].setOnClickListener(view -> {
            if(encender){
                Toast.makeText(this, "Encienda la calculadora", Toast.LENGTH_SHORT).show();
                return;
            }

            calcular(ultimaOperacion, "=");
        });

        // CAMBIO DE SIGNO
        operaciones[5].setOnClickListener(view -> {
            if(encender){
                Toast.makeText(this, "Encienda la calculadora", Toast.LENGTH_SHORT).show();
                return;
            }

            String textoActual = tvResultado.getText().toString();
            if (!textoActual.isEmpty() && !textoActual.equals("0")) {
                double valor = Double.parseDouble(textoActual);
                valor *= -1;
                tvResultado.setText(String.valueOf(valor));
            }
        });

        // PUNTO DECIMAL
        operaciones[6].setOnClickListener(view -> {
            if(encender){
                Toast.makeText(this, "Encienda la calculadora", Toast.LENGTH_SHORT).show();
                return;
            }

            String textoActual = tvResultado.getText().toString();
            if (!textoActual.contains(".")) {
                tvResultado.setText(textoActual + ".");
            }
        });
    }
    private void calcular(String ultimaOp, String igual)
    {
        float num1 = Float.parseFloat(primerNumero);
        float num2 = Float.parseFloat(tvResultado.getText().toString());
        float result = 0;

        switch (ultimaOp) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    Toast.makeText(this, "La División por cero no existe", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            default:
                break;
        }

        tvResultado.setText(String.valueOf(result));
        primerNumero = String.valueOf(result);
        calculo = true;

        if(igual == "="){
            ultimaOperacion = "";
            operar = false;
        }else if(ultimaOperacion == ""){
            ultimaOperacion = ultimaOp;
        }else{
            ultimaOperacion = operacionActual;
        }


    }

    //-----------------------------------------------------------------------------------------//

    private void funcionesExtras(){

        // DEL
        operaciones[7].setOnClickListener(view -> {
            if(encender){
                Toast.makeText(this, "Encienda la calculadora", Toast.LENGTH_SHORT).show();
                return;
            }

            String textoActual = tvResultado.getText().toString();
            if (textoActual.length() > 0) {
                textoActual = textoActual.substring(0, textoActual.length() - 1);
                tvResultado.setText(textoActual);
            }
        });

        // ON
        operaciones[8].setOnClickListener(view -> {
            tvResultado.setVisibility(View.VISIBLE);
            tvResultado.setText("0");
            tvResultado.setEnabled(true);

            ultimaOperacion = primerNumero = operacionActual = "";
            operar = calculo = false;

            encender = false;
        });

        // OFF
        operaciones[9].setOnClickListener(view -> {
            tvResultado.setVisibility(View.GONE);
            tvResultado.setText("");
            tvResultado.setEnabled(false);
            ultimaOperacion = primerNumero = operacionActual = "";
            operar = calculo = false;

            encender = true;
        });
    }

}