/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

/**
 *
 * @author danie
 */
public class Login {
    private String login;
    private String senha;
    
    private static String usuarioLogado;
    
    public Login (){
        
    }
    
    public Login(String login, String senha){
        this.login = login;
        this.senha = senha;
    }
    
    public static void setUsuarioLogado(String usuario) {
        usuarioLogado = usuario;
    }

    public static String getUsuarioLogado() {
        return usuarioLogado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
