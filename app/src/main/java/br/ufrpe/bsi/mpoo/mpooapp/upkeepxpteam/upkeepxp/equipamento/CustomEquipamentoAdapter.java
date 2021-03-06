package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.persistencia.EquipamentoDAO;
import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.infraestrutura.ui.MainActivity;
import upkeepxpteam.upkeepxp.R;


public class CustomEquipamentoAdapter extends BaseAdapter {

    private Activity activity;
    private List<EquipamentoModel> equipamentoModels;
    private LayoutInflater inflater;
    private ArrayAdapter<CharSequence> ligacaoAdapter;

    /**
     * Construtor para classe
     *
     * @param activity
     * @param equipamentoModels
     */
    public CustomEquipamentoAdapter(Activity activity, List<EquipamentoModel> equipamentoModels) {
        this.activity = activity;
        this.equipamentoModels = equipamentoModels;

        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return equipamentoModels.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * Retorna uma view
     *
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        CustomEquipamentoAdapter.ViewHolder holder = null;

        if (view == null) {
            view = inflater.inflate(R.layout.rowequipamento, viewGroup, false);
            holder = new CustomEquipamentoAdapter.ViewHolder();
            holder.tvEquipamentoAtual = view.findViewById(R.id.textView_Equipamento_Atual);
            ViewHolder.spinnerAtual = view.findViewById(R.id.spinner2);
            ViewHolder.spinnerProx = view.findViewById(R.id.spinner3);
            holder.ligacao = view.findViewById(R.id.spinner4);
            view.setTag(holder);
        } else {
            holder = (CustomEquipamentoAdapter.ViewHolder) view.getTag();
        }

        final EquipamentoModel equipamentoModel = equipamentoModels.get(i);

        List<String> lista = new ArrayList<>();
        addItensListaModeloEquipamento(lista);

        final ArrayAdapter<String> equipamentoModelArrayAdapter =
                new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, lista);
        equipamentoModelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ligacaoAdapter =
                ArrayAdapter.createFromResource(activity, R.array.ligacao, android.R.layout.simple_spinner_item); // USAR ENUM
        ligacaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ViewHolder.spinnerAtual.setAdapter(equipamentoModelArrayAdapter);
        // melhorar equipamentoModelArrayAdapter para retirar um item selecionado
        ViewHolder.spinnerProx.setAdapter(equipamentoModelArrayAdapter);
        holder.ligacao.setAdapter(ligacaoAdapter);

        ViewHolder.spinnerAtual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(activity, String.valueOf(equipamentoModelArrayAdapter.getItem(i)), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ViewHolder.spinnerProx.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(activity, String.valueOf(equipamentoModelArrayAdapter.getItem(i)), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        holder.ligacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stringLigacaoAdapter = String.valueOf(ligacaoAdapter.getItem(i));
                Toast.makeText(activity, String.valueOf(ligacaoAdapter.getItem(i)), Toast.LENGTH_SHORT).show();
                EquipamentoDAO equipamentoDAO = new EquipamentoDAO(activity);
                Equipamento equipamentoSpinnerAtual = equipamentoDAO.equipamentoPorNome(String.valueOf(ViewHolder.spinnerAtual.getSelectedItem()));
                Equipamento equipamentoSpinnerProx = equipamentoDAO.equipamentoPorNome(String.valueOf(ViewHolder.spinnerProx.getSelectedItem()));
                equipamentoDAO.execSQL(EquipamentoDAO.deleteMyTable2());
                equipamentoDAO.execSQL(EquipamentoDAO.createMyTable2());
                equipamentoModel.setEquipamento(equipamentoSpinnerAtual);
                equipamentoModel.setProxEquipamento(equipamentoSpinnerProx);
                equipamentoModel.setLigacao(stringLigacaoAdapter);
                equipamentoDAO.salvaDisponibilidade(equipamentoModel);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    static class ViewHolder {
        TextView tvEquipamentoAtual;
        static Spinner spinnerAtual;
        static Spinner spinnerProx;
        Spinner ligacao;
    }

    public void addItensListaModeloEquipamento(List equipeModels) {
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO(activity);
        List itens = equipamentoDAO.findAll();
        int cont = 0;
        while (cont <= itens.size() - 1) {
            Equipamento equipamento = (Equipamento) itens.get(cont);
            equipeModels.add(equipamento.getNome());
            cont += 1;
        }
    }

    public void newActivity(){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

}



