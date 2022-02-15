package br.senai.sp.jandira.calculadoraimc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultado = findViewById<TextView>(R.id.resultado);
        val resultadoMsg = findViewById<TextView>(R.id.resultadoMsg);
        val buttonCalcular = findViewById<Button>(R.id.buttonCalcular);

        // fazendo a limpeza dos textViews de resultado:
        resultado.text = "";
        resultadoMsg.text = "";

        buttonCalcular.setOnClickListener { buttonCalcularOnClick() }
    }


    fun calculoIMC(altura : Double, peso : Double) : Double {
        return peso / (altura * altura)
    }


    fun classificaçaoIMC(imc : Double) : String {
        var categoria :String = ""

        if (imc < 18.5)
            categoria = "abaixo do peso."
        else if (imc < 25)
            categoria = " peso normal."
        else if (imc < 30)
            categoria = "acima do peso."
        else if (imc < 35)
            categoria = " obesidade grau I."
        else if (imc < 40)
            categoria = " obesidade grau II."
        else if (imc >=40)
            categoria = " obesidade morbida."
        else
            categoria = "ERRO Não é possivel calcular seu IMC !"


        return categoria
    }

    fun Aviso(imc : Double) {
        val resultadoMsg = findViewById<TextView>(R.id.resultadoMsg);

        if (imc >= 18.5 && imc <25) {
            resultadoMsg.text = "Parabéns!"
            resultadoMsg.setTextColor(Color.GREEN);
        } else if (imc >= 40) {
            resultadoMsg.text = "Cuidado!"
            resultadoMsg.setTextColor(Color.RED);
        } else {
            resultadoMsg.text = ""
        }
    }

    fun buttonCalcularOnClick() {
        val altura = findViewById<EditText>(R.id.editAltura).text.toString().toDouble();
        val peso = findViewById<EditText>(R.id.editPeso).text.toString().toDouble();

        val imc = calculoIMC(altura, peso);
        val categoria = classificaçaoIMC(imc);

        findViewById<TextView>(R.id.resultado).text = "seu imc é ${String.format("%.2f", imc)} e você esta ${categoria}"
        Aviso(imc);
    }
}