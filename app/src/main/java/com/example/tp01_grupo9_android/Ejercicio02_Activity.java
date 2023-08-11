package com.example.tp01_grupo9_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ejercicio02_Activity extends AppCompatActivity {

    private TextView editText;
    private double numeroActual = 0;
    private double numeroAnterior = 0;
    private String operador = "";
    private boolean nuevaEntrada = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio02);

        editText = findViewById(R.id.mostrarResultado);
    }

    public void numeroPresionado(View view) {
        Button boton = (Button) view;
        String numero = boton.getText().toString();

        if (nuevaEntrada) {
            editText.setText("");
            nuevaEntrada = false;
        }

        editText.setText(editText.getText() + numero);
    }

    public void operacionPresionada(View view) {
        Button boton = (Button) view;
        operador = boton.getText().toString();
        numeroAnterior = Double.parseDouble(editText.getText().toString());
        nuevaEntrada = true;
    }

    public void calcular(View view) {
        if (!nuevaEntrada) {
            double resultado;
            numeroActual = Double.parseDouble(editText.getText().toString());
            switch (operador) {
                case "+":
                    resultado = numeroAnterior + numeroActual;
                    break;
                case "-":
                    resultado = numeroAnterior - numeroActual;
                    break;
                case "*":
                    resultado = numeroAnterior * numeroActual;
                    break;
                case "/":
                    if (numeroActual != 0) {
                        resultado = numeroAnterior / numeroActual;
                    } else {
                        editText.setText("Error");
                        return;
                    }
                    break;
                default:
                    return;
            }
            editText.setText(String.valueOf(resultado));
            nuevaEntrada = true;
        }
    }

    public void encenderCalculadora(View view) {
        editText.setEnabled(true);
    }

    public void apagarCalculadora(View view) {
        editText.setText("");
        editText.setEnabled(false);
    }

    public void borrarDigito(View view) {
        String textoActual = editText.getText().toString();
        if (textoActual.length() > 0) {
            textoActual = textoActual.substring(0, textoActual.length() - 1);
            editText.setText(textoActual);
        }
    }

    public void cambiarSigno(View view) {
        String textoActual = editText.getText().toString();
        if (!textoActual.isEmpty() && !textoActual.equals("0")) {
            double valor = Double.parseDouble(textoActual);
            valor *= -1;
            editText.setText(String.valueOf(valor));
        }
    }

    public void agregarPunto(View view) {
        String textoActual = editText.getText().toString();
        if (!textoActual.contains(".")) {
            editText.setText(textoActual + ".");
        }
    }

}