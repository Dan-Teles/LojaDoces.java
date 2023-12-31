/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import br.com.fatec.Controller.CadastroClienteController;
import br.com.fatec.marketplace.Principal;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author danie
 */
public class CadastroCliente {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String dataNascimento;
    private String genero;
    private String login;
    private String senha;
    public static Stage tela;

    
    public CadastroCliente(String nome, String cpf, String telefone, String email, String dataNascimento, String genero,String login,String senha){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.login = login;
        this.senha = senha;
    }
    
    public CadastroCliente(){
        //
    }
    
    public void start(Stage tela) throws IOException {
        setStage(tela);
        
        FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource("cadastroCliente.fxml"));
        Parent root = fxmlLoader.load();
        //CadastroClienteController controler = fxmlLoader.getController();
        //controler("Funcionou");
        //controler.mudaBotao();
        
        Scene scene = new Scene(root);
        
        tela.setScene(scene);
        tela.show();        
    }
    
    public static void setStage(Stage t) {
        tela = t;
    }

    
    //getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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
