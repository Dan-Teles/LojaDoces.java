/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author danie
 */
public class Carrinho {
    private int idPedido;
    private String produto;
    private float quantidade;
    private float valor;
    private String login;
    private float valorTotal;
    private String fornecedor;

    
    public Carrinho (){
        //
    }
    
    public Carrinho (int idPedido, String login, String produto, float quantidade, float valor, float valorTotal, String fornecedor){
        this.idPedido = idPedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valor = valor;
        this.login = login;
        this.fornecedor = fornecedor;
        this.valorTotal = valorTotal;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
}
