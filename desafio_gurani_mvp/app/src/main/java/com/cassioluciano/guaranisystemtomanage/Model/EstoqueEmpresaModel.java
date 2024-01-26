package com.cassioluciano.guaranisystemtomanage.Model;

public class EstoqueEmpresaModel {
    private String empresa;
    private String codigo;
    private double estoque;
    private String local;

    // Adicione construtores, getters e setters


    public EstoqueEmpresaModel() {
    }

    public EstoqueEmpresaModel(String empresa, String codigo, double estoque, String local) {
        this.empresa = empresa;
        this.codigo = codigo;
        this.estoque = estoque;
        this.local = local;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getEstoque() {
        return estoque;
    }

    public void setEstoque(double estoque) {
        this.estoque = estoque;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
