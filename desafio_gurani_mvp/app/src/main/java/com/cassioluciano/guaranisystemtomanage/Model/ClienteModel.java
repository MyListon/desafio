package com.cassioluciano.guaranisystemtomanage.Model;

import java.util.List;

public class ClienteModel {
    private String codigoCliente;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String cpf;
    private String emailPrincipal;
    private String emailSecundario;
    private List<EnderecoModel> enderecos;

    public ClienteModel() {
    }

    public ClienteModel(String codigoCliente, String razaoSocial, String nomeFantasia, String cnpj, String cpf, String emailPrincipal, String emailSecundario, List<EnderecoModel> enderecos) {
        this.codigoCliente = codigoCliente;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.cpf = cpf;
        this.emailPrincipal = emailPrincipal;
        this.emailSecundario = emailSecundario;
        this.enderecos = enderecos;
    }

    public ClienteModel(String s, String s1, String cnpj1, String email1) {

    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmailPrincipal() {
        return emailPrincipal;
    }

    public void setEmailPrincipal(String emailPrincipal) {
        this.emailPrincipal = emailPrincipal;
    }

    public String getEmailSecundario() {
        return emailSecundario;
    }

    public void setEmailSecundario(String emailSecundario) {
        this.emailSecundario = emailSecundario;
    }

    public List<EnderecoModel> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoModel> enderecos) {
        this.enderecos = enderecos;
    }
}

    //adb push /caminho/local/do/seu/arquivo/bancomovel.sqlite /data/data/seu_pacote_do_aplicativo/databases/
