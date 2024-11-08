package com.myliston.desafio.model;

import java.io.Serializable;

public class Product implements Serializable {

    private String code;
    private String description;
    private int stock;
    private double maxPrice;
    private double minPrice;
    private String codigoEmpresa;
    private String codigo;
    private String codigoMarca;
    private String codigoGrupo;
    private String descricao;
    private String status;
    private int estoque;
    private double precoMaximo;
    private double precoMinimo;

    // Getters e Setters


    public Product() {
    }
    public Product(String code, String description, int stock, double maxPrice, double minPrice) {

        this.code = code;
        this.description = description;
        this.stock = stock;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }
    public Product(String code, String description, int stock, double maxPrice, double minPrice, String codigoEmpresa, String codigo, String codigoMarca, String codigoGrupo, String descricao, String status, int estoque, double precoMaximo, double precoMinimo) {
        this.code = code;
        this.description = description;
        this.stock = stock;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.codigoEmpresa = codigoEmpresa;
        this.codigo = codigo;
        this.codigoMarca = codigoMarca;
        this.codigoGrupo = codigoGrupo;
        this.descricao = descricao;
        this.status = status;
        this.estoque = estoque;
        this.precoMaximo = precoMaximo;
        this.precoMinimo = precoMinimo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
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

    public String getCodigoMarca() {
        return codigoMarca;
    }

    public void setCodigoMarca(String codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    public String getCodigoGrupo() {
        return codigoGrupo;
    }

    public void setCodigoGrupo(String codigoGrupo) {
        this.codigoGrupo = codigoGrupo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
}
