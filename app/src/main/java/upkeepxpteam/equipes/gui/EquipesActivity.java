package upkeepxpteam.equipes.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import upkeepxpteam.CustomEquipeAdapter;
import upkeepxpteam.EquipeModel;
import upkeepxpteam.equipes.equipebase.Equipe;
import upkeepxpteam.equipes.negocio.EquipeNegocio;
import upkeepxpteam.upkeepxp.R;

public class EquipesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipes);
        FloatingActionButton button = findViewById(R.id.floatingActionButton);
        button.bringToFront();

        ListView lista = findViewById(R.id.listview_Equipes);

        final List<EquipeModel> modeloEquipe = new ArrayList<>();
        addItensListaModeloEquipe(modeloEquipe);

        final CustomEquipeAdapter adapter = new CustomEquipeAdapter(this, modeloEquipe);
        lista.setAdapter(adapter);
    }

    public void addEquipe(View view){
        Intent intent = new Intent(this, CadastraEquipeActivity.class);
        startActivity(intent);
        finish();
    }

    public void addItensListaModeloEquipe(List equipeModels) {
        EquipeNegocio equipeNegocio = new EquipeNegocio(this);
        List itens = equipeNegocio.buscarTodasEquipes();
        int cont = 0;
        while (cont <= itens.size() - 1) {
            Equipe equipe = (Equipe) itens.get(cont);
            equipeModels.add(new EquipeModel(equipe));
            cont += 1;
        }
    }
}
