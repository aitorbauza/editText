package com.example.myapplication8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView articleTextView;
    private EditText editArticleText;

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

        // Enlaces entre XML y Java
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        EditText editText = findViewById(R.id.edit_text);
        TextView savedTextView = findViewById(R.id.saved_text);

        articleTextView = findViewById(R.id.article);
        editArticleText = findViewById(R.id.edit_article_text);

        // Configuración del botón 1
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setVisibility(View.VISIBLE);  //Una vez clicas, aparece el editText
                String inputText = editText.getText().toString();
                savedTextView.setText(inputText); //Cuando vuelves a clicar, se guarda el texto en pantalla
            }
        });

        // Configuración del botón 2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleEditArticleMode(); //Al clicar, llamas a esta función
            }
        });
    }

    // Esta función se encarga de alternar entre modo de edición o de visualización
    private void toggleEditArticleMode() {
        if (editArticleText.getVisibility() == View.GONE) { // Si está en modo edición
            startEditingArticle(); //Se activa esta funciión
        } else {
            saveEditedArticle(); //Si no, se activa esta otra
        }
    }

    // Esta función se encarga de editar el texto
    private void startEditingArticle() {
        editArticleText.setVisibility(View.VISIBLE);
        editArticleText.setText(articleTextView.getText().toString());
        articleTextView.setVisibility(View.GONE);
    }

    // Esta función se encarga de guardar el texto
    private void saveEditedArticle() {
        articleTextView.setText(editArticleText.getText().toString());
        editArticleText.setVisibility(View.GONE);
        articleTextView.setVisibility(View.VISIBLE);
    }
}
