package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome, editTextEmail, editTextIdade, editTextDisciplina, editTextNota1, editTextNota2;
    private TextView textViewResultado, textViewErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextIdade = findViewById(R.id.editTextIdade);
        editTextDisciplina = findViewById(R.id.editTextDisciplina);
        editTextNota1 = findViewById(R.id.editTextNota1);
        editTextNota2 = findViewById(R.id.editTextNota2);
        textViewResultado = findViewById(R.id.textViewResultado);
        textViewErro = findViewById(R.id.textViewErro);

        Button buttonEnviar = findViewById(R.id.buttonEnviar);
        Button buttonLimpar = findViewById(R.id.buttonLimpar);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDados();
            }
        });

        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCampos();
            }
        });
    }

    private void validarDados() {
        String nome = editTextNome.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String idadeStr = editTextIdade.getText().toString().trim();
        String disciplina = editTextDisciplina.getText().toString().trim();
        String nota1Str = editTextNota1.getText().toString().trim();
        String nota2Str = editTextNota2.getText().toString().trim();

        StringBuilder erros = new StringBuilder();

        if (TextUtils.isEmpty(nome)) {
            erros.append("O campo de nome está vazio.\n");
        }
        if (TextUtils.isEmpty(email) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            erros.append("O email é inválido.\n");
        }
        int idade = 0;
        if (TextUtils.isEmpty(idadeStr) || !TextUtils.isDigitsOnly(idadeStr) || (idade = Integer.parseInt(idadeStr)) <= 0) {
            erros.append("A idade deve ser um número positivo.\n");
        }
        double nota1 = 0, nota2 = 0;
        if (TextUtils.isEmpty(nota1Str) || (nota1 = Double.parseDouble(nota1Str)) < 0 || nota1 > 10) {
            erros.append("A Nota 1º Bimestre deve estar entre 0 e 10.\n");
        }
        if (TextUtils.isEmpty(nota2Str) || (nota2 = Double.parseDouble(nota2Str)) < 0 || nota2 > 10) {
            erros.append("A Nota 2º Bimestre deve estar entre 0 e 10.\n");
        }

        if (erros.length() > 0) {
            textViewErro.setText(erros.toString());
            textViewResultado.setText("");
        } else {
            exibirResultados(nome, email, idade, disciplina, nota1, nota2);
            textViewErro.setText("");
        }
    }

    private void exibirResultados(String nome, String email, int idade, String disciplina, double nota1, double nota2) {
        double media = (nota1 + nota2) / 2;
        String resultado = String.format("Nome: %s\nEmail: %s\nIdade: %d\nDisciplina: %s\nNotas 1º e 2º Bimestres: %.2f, %.2f\nMédia: %.2f\n", nome, email, idade, disciplina, nota1, nota2, media);
        String aprovacao = media >= 6 ? "Aprovado" : "Reprovado";
        resultado += "Resultado: " + aprovacao;
        textViewResultado.setText(resultado);
    }

    private void limparCampos() {
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextIdade.setText("");
        editTextDisciplina.setText("");
        editTextNota1.setText("");
        editTextNota2.setText("");
        textViewResultado.setText("");
        textViewErro.setText("");
    }
}
