package upkeepxpteam.equipes.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import upkeepxpteam.CustomAdapter;
import upkeepxpteam.UserModel;
import upkeepxpteam.equipes.equipebase.Equipe;
import upkeepxpteam.equipes.negocio.EquipeNegocio;
import upkeepxpteam.upkeepxp.R;
import upkeepxpteam.usuario.usuariobase.Usuario;
import upkeepxpteam.usuario.usuariopersistence.UsuarioDAO;

public class CadastraEquipeActivity extends AppCompatActivity {

    private EditText edtnomeequipe;
    private Button btnSalvar;

    Equipe equipe;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastra_equipe);
        ListView listView = findViewById(R.id.listView_Membros);
        btnSalvar = findViewById(R.id.btn_confirmar);
        edtnomeequipe = findViewById(R.id.editText_nome_equipe);

        final List<UserModel> listaUsuarios = new ArrayList<>();
        addItensListaUsuarios(listaUsuarios);

        final CustomAdapter adapter = new CustomAdapter(this, listaUsuarios);
        listView.setAdapter(adapter);

        final List<Usuario> usersequipe = new ArrayList<>();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equipe = montarObjetoEquipe();
                equipe.setUsers(usersequipe);
                EquipeNegocio equipeNegocio = new EquipeNegocio(CadastraEquipeActivity.this);
                equipeNegocio.salvarEquipe(equipe);
                chamaEquipesActivity();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UserModel model = listaUsuarios.get(i);
                if (model.isSelected()) {
                    model.setSelected(false);
                    usersequipe.remove(listaUsuarios.get(i).getUsuario());
                }
                else {
                    model.setSelected(true);
                    usersequipe.add(listaUsuarios.get(i).getUsuario());
                }
                listaUsuarios.set(i, model);

                adapter.updateRecords(listaUsuarios);

            }
        });
    }

        public void addItensListaUsuarios(List users) {
            UsuarioDAO usuarioDAO = new UsuarioDAO(this);
            List itens = usuarioDAO.buscarTodosUsuarios();
            int cont = 0;
            while (cont <= itens.size() - 1) {
                Usuario usuario = (Usuario) itens.get(cont);
                users.add(new UserModel(false, usuario));
                cont += 1;
            }
        }

        public Equipe montarObjetoEquipe(){

            String nomeequipe = edtnomeequipe.getText().toString();
            Equipe equipe = new Equipe();
            equipe.setNome(nomeequipe);
            return  equipe;

        }

        public void chamaEquipesActivity(){

            Intent intent = new Intent(CadastraEquipeActivity.this, EquipesActivity.class);
            startActivity(intent);
            finish();

        }
}




