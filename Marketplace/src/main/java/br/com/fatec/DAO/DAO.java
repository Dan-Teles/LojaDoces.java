/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.fatec.DAO;
import java.sql.SQLException;
import java.util.Collection;

/**
 *
 * @author danie
 */
public interface DAO <T> {
    public boolean insere(T model) 
                    throws SQLException;
    public boolean remove(T model) 
                    throws SQLException;
    public boolean altera(T model) 
                    throws SQLException;
    public T buscaID(T model) 
                    throws SQLException;
    public Collection <T> lista(String criterio) 
                    throws SQLException;
    
}
