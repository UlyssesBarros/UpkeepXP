package br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento;

import br.ufrpe.bsi.mpoo.mpooapp.upkeepxpteam.upkeepxp.equipamento.dominio.Equipamento;


public class EquipamentoModel {

    Equipamento equipamento;
    Equipamento proxEquipamento;
    String nome;
    Boolean isSelected;
    String ligacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Construtor para classe
     * @param equipamento
     */
    public EquipamentoModel(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public EquipamentoModel(){}

    /**
     * Retorna um equipamento
     * @return equipamento
     */
    public Equipamento getEquipamento() {
        return equipamento;
    }

    /**
     * Seta um equipamento no objeto EquipamentoModel
     * @param equipamento
     */
    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public void setSelected(boolean selected) {

        isSelected = selected;
    }

    public boolean isSelected() {
        if (isSelected == true){
            return true;
        }
        else{
            return false;
        }
    }

    public Equipamento getProxEquipamento() {
        return proxEquipamento;
    }

    public void setProxEquipamento(Equipamento proxEquipamento) {
        this.proxEquipamento = proxEquipamento;
    }

}
