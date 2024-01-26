package com.cassioluciano.guaranisystemtomanage.Model;

public class ProdutoModel {
    private String codigoEmpresa;
    private String codigo;
    private String descricao;
    private int estoque;
    private double precoMaximo;
    private double precoMinimo;
    private String status;

    // Adicione construtores, getters e setters


    public ProdutoModel() {
    }

    public ProdutoModel(String codigoEmpresa, String codigo, String descricao, int estoque, double precoMaximo, double precoMinimo, String status) {
        this.codigoEmpresa = codigoEmpresa;
        this.codigo = codigo;
        this.descricao = descricao;
        this.estoque = estoque;
        this.precoMaximo = precoMaximo;
        this.precoMinimo = precoMinimo;
        this.status = status;
    }

    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getPrecoMaximo() {
        return precoMaximo;
    }

    public void setPrecoMaximo(double precoMaximo) {
        this.precoMaximo = precoMaximo;
    }

    public double getPrecoMinimo() {
        return precoMinimo;
    }

    public void setPrecoMinimo(double precoMinimo) {
        this.precoMinimo = precoMinimo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
