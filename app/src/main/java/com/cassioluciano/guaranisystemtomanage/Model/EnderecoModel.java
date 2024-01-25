package com.cassioluciano.guaranisystemtomanage.Model;

public class EnderecoModel {
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String codigoMunicipio;
    private String telefone;
    private String fax;
    private String cep;

    // Adicione construtores, getters e setters


    public EnderecoModel() {
    }

    public EnderecoModel(String endereco, String numero, String complemento, String bairro, String codigoMunicipio, String telefone, String fax, String cep) {
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.codigoMunicipio = codigoMunicipio;
        this.telefone = telefone;
        this.fax = fax;
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}


