package br.edu.ifro.vilhena.agendadecontatos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.edu.ifro.vilhena.agendadecontatos.dao.ContatoDAO;
import br.edu.ifro.vilhena.agendadecontatos.model.Contato;

public class ListarContatosActivity extends AppCompatActivity {

    private ListView listarContatosListView;
    private FloatingActionButton listarContatosBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contatos);

        //Mapeando o componente da Activity
        listarContatosListView = findViewById(R.id.listar_contatos_listview);
        listarContatosBtn = findViewById(R.id.listar_contato_btn);

        //Criando um adapter para enviar os dados para o ListView
        //String [] contatos = {"Robson", "João", "Maria", "José"};
        ContatoDAO contatoDAO = new ContatoDAO(this);
        List<Contato> contatos = contatoDAO.listar();


        ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);

        //ListView utiliza o adapter
        listarContatosListView.setAdapter(adapter);

        // Ao clicar no floatActionButton
        listarContatosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarContatosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
    }


}
