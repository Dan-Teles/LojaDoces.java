/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fatec.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author danie
 */
public class Chocolate {
  private final SimpleBooleanProperty selecionado;
    private final SimpleIntegerProperty codProprietario;
    private final SimpleStringProperty nome;
    
    //construtor
    public Chocolate() {
        this.selecionado = new SimpleBooleanProperty(false);
        this.codProprietario = new SimpleIntegerProperty(0);
        this.nome = new SimpleStringProperty("");
    }

    public Chocolate(int codProprietario, String nome) {
        this.selecionado = new SimpleBooleanProperty(false);
        this.codProprietario = new SimpleIntegerProperty(codProprietario);
        this.nome = new SimpleStringProperty(nome);
    }

    //retorna as propriedades
    public SimpleBooleanProperty selecionadoProperty() {
        return selecionado;
    }

    public SimpleIntegerProperty codProprietarioProperty() {
        return codProprietario;
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }
    
    //getters e setters do model
    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getNome() {
        return nome.get();
    }
    
    public void setCodProprietarioi(int codProprietario) {
        this.codProprietario.set(codProprietario);
    }
    
    public int getCodProprietario() {
        return codProprietario.get();
    }

    public void setCodProprietario(int codProprietario) {
        this.codProprietario.set(codProprietario);
    }
    
    public boolean isSelecionado() {
        return selecionado.get();
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado.set(selecionado);
    }
}
