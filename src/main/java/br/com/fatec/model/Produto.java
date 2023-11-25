/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author danie
 */
public class Produto {
    private String categoria;
    private String produto;
    private float estoque;
    private float valor;
    private String fornecedor;
    
    private float quantidade;

    public Produto(String categoria, String produto, float estoque, float valor, String fornecedor) {
        this.categoria = categoria;
        this.produto = produto;
        this.estoque = estoque;
        this.valor = valor;
        this.fornecedor = fornecedor;
    }

    public Produto(){
        //
    }

    @Override
    public String toString() {
        return "Produto{" + "produto=" + produto + '}';
    }
   
    
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public float getEstoque() {
        return estoque;
    }

    public void setEstoque(float estoque) {
        this.estoque = estoque;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }
 
}
