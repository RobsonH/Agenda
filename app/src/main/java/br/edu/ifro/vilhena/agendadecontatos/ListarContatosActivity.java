package br.edu.ifro.vilhena.agendadecontatos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
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

        listarContatosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarContatosActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });

        registerForContextMenu(listarContatosListView);

        listarContatosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int posicao, long id) {

                Contato contato = (Contato) lista.getItemAtPosition(posicao);

                Intent intent = new Intent(ListarContatosActivity.this, FormularioActivity.class);

                intent.putExtra("contato", contato);

                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Criando um adapter para enviar os dados para o ListView
        //String [] contatos = {"Robson", "João", "Maria", "José"};
        carregarLista();
        // Ao clicar no floatActionButton

    }

    private void carregarLista() {
        ContatoDAO contatoDAO = new ContatoDAO(this);
        List<Contato> contatos = contatoDAO.listar();


        final ArrayAdapter<Contato> adapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);

        //ListView utiliza o adapter
        listarContatosListView.setAdapter(adapter);
    }

    //Criando o menu para deletar
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem menuDeletar = menu.add("Deletar");

        menuDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AdapterView.AdapterContextMenuInfo menu = (AdapterView.AdapterContextMenuInfo) menuInfo;


                Contato contato = (Contato) listarContatosListView.getItemAtPosition(menu.position);

                ContatoDAO contatoDAO = new ContatoDAO(ListarContatosActivity.this);
                contatoDAO.deletar(contato);


                carregarLista();

                return false;
            }
        });
    }
}
