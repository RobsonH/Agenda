package br.edu.ifro.vilhena.agendadecontatos;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifro.vilhena.agendadecontatos.model.Contato;

public class FormularioActivity extends AppCompatActivity {

    private TextInputEditText formularioNome;
    private TextInputEditText formularioEmail;
    private TextInputEditText formularioEndereco;
    private TextInputEditText formularioTelefone;
    private Button formularioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        //Mapear os componentes do xml
        formularioNome = findViewById(R.id.formulario_nome);
        formularioEmail = findViewById(R.id.formulario_email);
        formularioEndereco = findViewById(R.id.formulario_endereco);
        formularioTelefone = findViewById(R.id.formulario_telefone);
        formularioBtn = findViewById(R.id.formulario_btn);

        formularioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Criar o objeto

                Contato contato = new Contato();
                contato.setNome(formularioNome.getText().toString());
                contato.setEmail(formularioEmail.getText().toString());
                contato.setEndereco(formularioEndereco.getText().toString());
                contato.setTelefone(formularioTelefone.getText().toString());

                //Mostrar a mensagem
                Toast.makeText(FormularioActivity.this, "Contato salvo com sucesso!", Toast.LENGTH_LONG).show();
                //Destruir a Activiy
                finish();
            }
        });
    }
}
