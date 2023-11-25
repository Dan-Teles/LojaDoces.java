/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.marketplace.Principal;
import br.com.fatec.model.CadastroCliente;
import br.com.fatec.model.Login;
import static br.com.fatec.persistencia.Banco.connect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtLogin;
    @FXML
    private CheckBox chbVisualizar;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnCriarConta;

    @FXML
    private Label lblLogin;

    /**
     * Initializes the controller class.
     */
    
    public Stage tela;
    private String loginDigitado;
    private String senhaDigitada;
    private String visualizarSenhaDigitada;
    @FXML
    private TextField txtSenhaCheck;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtSenhaCheck.setVisible(false);
        
        txtSenha.setVisible(true);
    }    

    @FXML
    private void btnEntrar_click(ActionEvent event) {
       if(!todosCamposPreenchidos()){
            return;
        }

       loginDigitado = txtLogin.getText();
       senhaDigitada = txtSenha.getText();
       visualizarSenhaDigitada = txtSenhaCheck.getText();
       
       if("admin".equals(loginDigitado) && "admin".equals(senhaDigitada) || "admin".equals(loginDigitado) && "admin".equals(visualizarSenhaDigitada)){
           info("Admin logado!");
           
           Login.setUsuarioLogado(loginDigitado);
           
           //chamando tela admin
           mudaTela(event,"/br/com/fatec/marketplace/admin.fxml");
       }
       else{
           if (validarCredenciais(loginDigitado, senhaDigitada)) {
                // Credenciais válidas - adicione a lógica desejada aqui
                info("Login bem-sucedido!");
                
                Login.setUsuarioLogado(loginDigitado);

                mudaTela(event,"/br/com/fatec/marketplace/produto.fxml");

            } else {
                // Credenciais inválidas - adicione a lógica desejada aqui
                alert("Login falhou. Verifique suas credenciais.");
            }
        }
    }

    @FXML
    private void btnCriarConta_Click(ActionEvent event) throws IOException{
        /*Stage novaStage = new Stage();
        CadastroCliente cad = new CadastroCliente();
        
        cad.start(novaStage);*/
        mudaTela(event,"/br/com/fatec/marketplace/cadastroCliente.fxml");
    }
    
    public void fechar(Stage tela) throws IOException{
        setStage(tela);
        
        FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root);
        
        tela.setScene(scene);
        tela.close();
    }
    
    public void start(Stage tela) throws IOException {
        setStage(tela);
        
        FXMLLoader fxmlLoader = new FXMLLoader(Principal.class.getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        
        Scene scene = new Scene(root, 640, 480);
        
        tela.setScene(scene);
        tela.show();
    }
    
    public static void setStage(Stage t) {
        //tela = t;
    }
    
    private boolean validarCredenciais(String login, String senha) {
        try (Connection conn = connect()) {
            String query = "SELECT * FROM login WHERE login = ? AND senha = ?";
            
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, login);
                stmt.setString(2, senha);

                try (ResultSet resultado = stmt.executeQuery()) {
                    return resultado.next(); // Retorna true se houver um resultado (credenciais válidas)
                }
            }
        }catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção de acordo com sua lógica de tratamento de erros
            return false;
        }
    }
    
    private boolean todosCamposPreenchidos() {
    // Adicione todos os campos que você deseja verificar
        if (txtLogin.getText().isEmpty()) {
            alert("Preencha o campo Login.");
            txtLogin.requestFocus();
            return false;
        }
        if (txtSenha.getText().isEmpty() && txtSenhaCheck.getText().isEmpty()) {
            alert("Preencha o campo Senha.");
            txtSenha.requestFocus();
            return false;
        }
        return true;
    }
    
    private void limparCampos(){
        txtLogin.clear();
        txtSenha.clear();
        txtLogin.requestFocus();
    }
    
    private void mudaTela(ActionEvent event, String caminho){
        try {
                // Carrega o FXML 'CadastroFornecedor'
                FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));
                Parent root = loader.load();
                // Obtém o Stage atual
                Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();

                // Fecha o Stage atual
                stageAtual.close();

                // Cria um novo Stage para o FXML 'CadastroFornecedor'
                Stage novoStage = new Stage();
                novoStage.setScene(new Scene(root));

                // Mostra o novo Stage
                novoStage.show();
            } catch (IOException e) {
                // Lidar com exceções de IO, se necessário
                e.printStackTrace();
            }
    }
    
    public void alert(String msg){     
        Alert alerta = new Alert (Alert.AlertType.WARNING);
        alerta.setTitle("Atenção!");
        alerta.setHeaderText(msg);
               
        alerta.showAndWait(); //exibe mensagem
    }
    
    public void info(String msg){    
        Alert alerta = new Alert (Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");
               
        alerta.showAndWait(); //exibe mensagem
    }

    @FXML
    private void chbVisualizar_Click(ActionEvent event) {
        if (chbVisualizar.isSelected()){
           String senha = txtSenha.getText();
           txtSenhaCheck.setText(senha);
           txtSenhaCheck.setVisible(true);
           txtSenha.setVisible(false);
       }
       else{
           String senha = txtSenhaCheck.getText();
           txtSenha.setText(senha);
           txtSenhaCheck.setVisible(false);
           txtSenha.setVisible(true);
       }
    }
}