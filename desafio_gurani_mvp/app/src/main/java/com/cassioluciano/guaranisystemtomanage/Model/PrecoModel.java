package com.cassioluciano.guaranisystemtomanage.Model;

public class PrecoModel {
    private String codigo;
    private String tabelaPreco;
    private String uniVenda;
    private String codigoEmpresa;
    private String preco;
    private double descontoPromocao;
    private double descontoNormal;
    private double descontoFlex;
    private double acrescimoMax;
    private String codigoComissao;
    private double percEmb;
    private int qtdeMinima;
    private int qtdeMaxima;
    private String alteraPreco;
    private String descontoPromocaoImpactaVerba;
    private double exibeEtiquetaDescontoPerc;
    private double valorMinVerbaAvulsa;
    private int segregacao;
    private int qtdeSegrSelecao;

    // construtores, getters e setters


    public PrecoModel() {
    }

    public PrecoModel(String codigo, String tabelaPreco, String uniVenda, String codigoEmpresa, String precos, double descontoPromocao, double descontoNormal, double descontoFlex, double acrescimoMax, String codigoComissao, double percEmb, int qtdeMinima, int qtdeMaxima, String alteraPreco, String descontoPromocaoImpactaVerba, double exibeEtiquetaDescontoPerc, double valorMinVerbaAvulsa, int segregacao, int qtdeSegrSelecao) {
        this.codigo = codigo;
        this.tabelaPreco = tabelaPreco;
        this.uniVenda = uniVenda;
        this.codigoEmpresa = codigoEmpresa;
        this.preco = precos;
        this.descontoPromocao = descontoPromocao;
        this.descontoNormal = descontoNormal;
        this.descontoFlex = descontoFlex;
        this.acrescimoMax = acrescimoMax;
        this.codigoComissao = codigoComissao;
        this.percEmb = percEmb;
        this.qtdeMinima = qtdeMinima;
        this.qtdeMaxima = qtdeMaxima;
        this.alteraPreco = alteraPreco;
        this.descontoPromocaoImpactaVerba = descontoPromocaoImpactaVerba;
        this.exibeEtiquetaDescontoPerc = exibeEtiquetaDescontoPerc;
        this.valorMinVerbaAvulsa = valorMinVerbaAvulsa;
        this.segregacao = segregacao;
        this.qtdeSegrSelecao = qtdeSegrSelecao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTabelaPreco() {
        return tabelaPreco;
    }

    public String getUniVenda() {
        return uniVenda;
    }

    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public String getPreco() {
        return preco;
    }

    public double getDescontoPromocao() {
        return descontoPromocao;
    }

    public double getDescontoNormal() {
        return descontoNormal;
    }

    public double getDescontoFlex() {
        return descontoFlex;
    }

    public double getAcrescimoMax() {
        return acrescimoMax;
    }

    public String getCodigoComissao() {
        return codigoComissao;
    }

    public double getPercEmb() {
        return percEmb;
    }

    public int getQtdeMinima() {
        return qtdeMinima;
    }

    public int getQtdeMaxima() {
        return qtdeMaxima;
    }

    public String getAlteraPreco() {
        return alteraPreco;
    }

    public String getDescontoPromocaoImpactaVerba() {
        return descontoPromocaoImpactaVerba;
    }

    public double getExibeEtiquetaDescontoPerc() {
        return exibeEtiquetaDescontoPerc;
    }

    public double getValorMinVerbaAvulsa() {
        return valorMinVerbaAvulsa;
    }

    public int getSegregacao() {
        return segregacao;
    }

    public int getQtdeSegrSelecao() {
        return qtdeSegrSelecao;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTabelaPreco(String tabelaPreco) {
        this.tabelaPreco = tabelaPreco;
    }

    public void setUniVenda(String uniVenda) {
        this.uniVenda = uniVenda;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public void setDescontoPromocao(double descontoPromocao) {
        this.descontoPromocao = descontoPromocao;
    }

    public void setDescontoNormal(double descontoNormal) {
        this.descontoNormal = descontoNormal;
    }

    public void setDescontoFlex(double descontoFlex) {
        this.descontoFlex = descontoFlex;
    }

    public void setAcrescimoMax(double acrescimoMax) {
        this.acrescimoMax = acrescimoMax;
    }

    public void setCodigoComissao(String codigoComissao) {
        this.codigoComissao = codigoComissao;
    }

    public void setPercEmb(double percEmb) {
        this.percEmb = percEmb;
    }

    public void setQtdeMinima(int qtdeMinima) {
        this.qtdeMinima = qtdeMinima;
    }

    public void setQtdeMaxima(int qtdeMaxima) {
        this.qtdeMaxima = qtdeMaxima;
    }

    public void setAlteraPreco(String alteraPreco) {
        this.alteraPreco = alteraPreco;
    }

    public void setDescontoPromocaoImpactaVerba(String descontoPromocaoImpactaVerba) {
        this.descontoPromocaoImpactaVerba = descontoPromocaoImpactaVerba;
    }

    public void setExibeEtiquetaDescontoPerc(double exibeEtiquetaDescontoPerc) {
        this.exibeEtiquetaDescontoPerc = exibeEtiquetaDescontoPerc;
    }

    public void setValorMinVerbaAvulsa(double valorMinVerbaAvulsa) {
        this.valorMinVerbaAvulsa = valorMinVerbaAvulsa;
    }

    public void setSegregacao(int segregacao) {
        this.segregacao = segregacao;
    }

    public void setQtdeSegrSelecao(int qtdeSegrSelecao) {
        this.qtdeSegrSelecao = qtdeSegrSelecao;
    }
}
