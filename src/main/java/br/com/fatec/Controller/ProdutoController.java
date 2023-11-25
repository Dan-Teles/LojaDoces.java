/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package br.com.fatec.Controller;

import br.com.fatec.model.Carrinho;
import br.com.fatec.model.Login;
import br.com.fatec.model.Produto;
import static br.com.fatec.persistencia.Banco.connect;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danie
 */
public class ProdutoController implements Initializable {

    @FXML
    private Label lblTodos;
    @FXML
    private Label lblBolo;
    @FXML
    private Label lblChocolate;
    @FXML
    private Label lblTrufa;
    @FXML
    private TableView<Produto> tableView;
    @FXML
    private TableColumn<Produto, String> Categoria;
    @FXML
    private TableColumn<Produto, String> Produto;
    @FXML
    private TableColumn<Produto, Float> Estoque;
    @FXML
    private TableColumn<Produto, Float> Valor;
    @FXML
    private TableColumn<Produto, String> Fornecedor;
    @FXML
    private TableColumn<Produto, Float> Quantidade;
    @FXML
    private Button btnCarrinho;
    @FXML
    private Button btnComprar;
    
    
    private ObservableList<Produto> dadosProduto;
    //Carrinho produto = new Carrinho();
    Produto produto = new Produto();
    
    private ComboBox<Float> comboBox;
    @FXML
    private Button btnDeslogar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        Fornecedor.setCellValueFactory(new PropertyValueFactory<>("fornecedor"));
        Produto.setCellValueFactory(new PropertyValueFactory<>("produto"));
        Estoque.setCellValueFactory(new PropertyValueFactory<>("estoque"));
        Valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        
        comboBox = new ComboBox<>(FXCollections.observableArrayList(1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f, 14f, 15f, 20f, 25f, 30f, 40f, 45f));
       
        configurarColunaQuantidade();
        
        // Carregar os dados do banco de dados e exibir na TableView
        carregarDadosDoBanco();
        // TODO
    }    

    @FXML
    private void lblTodos_Click(MouseEvent event) {
        carregarDadosDoBanco();
    }

    @FXML
    private void lblBolo_Click(MouseEvent event) {
        ObservableList<Produto> dadosProduto = FXCollections.observableArrayList();
        //dadosProduto = FXCollections.observableArrayList();

        // Conectar ao banco de dados
        try (Connection conn = connect()) {
            String query = "SELECT CATEGORIA, DESCRICAO, ESTOQUE, PRECO, NomeFantasia FROM produto WHERE CATEGORIA = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, "BOLO");
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        String categoria = rs.getString("CATEGORIA");
                        String produto = rs.getString("DESCRICAO");
                        float estoque = rs.getFloat("ESTOQUE");
                        float valor = rs.getFloat("PRECO");
                        String fornecedor = rs.getString("NomeFantasia");
                        
                        Produto item = new Produto(categoria, produto, estoque, valor, fornecedor);
                        dadosProduto.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção de acordo com sua lógica de tratamento de erros
        }

        // Configurar os dados da TableView
        tableView.setItems(dadosProduto);
    }

    @FXML
    private void lblChocolate_Click(MouseEvent event) {
        ObservableList<Produto> dadosProduto = FXCollections.observableArrayList();
        //dadosProduto = FXCollections.observableArrayList();

        // Conectar ao banco de dados
        try (Connection conn = connect()) {
            String query = "SELECT CATEGORIA, DESCRICAO, ESTOQUE, PRECO, NomeFantasia FROM produto WHERE CATEGORIA = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, "CHOCOLATE");
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        String categoria = rs.getString("CATEGORIA");
                        String produto = rs.getString("DESCRICAO");
                        float estoque = rs.getFloat("ESTOQUE");
                        float valor = rs.getFloat("PRECO");
                        String fornecedor = rs.getString("NomeFantasia");
                        
                        Produto item = new Produto(categoria, produto, estoque, valor, fornecedor);
                        dadosProduto.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção de acordo com sua lógica de tratamento de erros
        }

        // Configurar os dados da TableView
        tableView.setItems(dadosProduto);
    }

    @FXML
    private void lblTrufa_Click(MouseEvent event) {
        ObservableList<Produto> dadosProduto = FXCollections.observableArrayList();
        //dadosProduto = FXCollections.observableArrayList();

        // Conectar ao banco de dados
        try (Connection conn = connect()) {
            String query = "SELECT CATEGORIA, DESCRICAO, ESTOQUE, PRECO, NomeFantasia FROM produto WHERE CATEGORIA = ?";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, "TRUFA");
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        String categoria = rs.getString("CATEGORIA");
                        String produto = rs.getString("DESCRICAO");
                        float estoque = rs.getFloat("ESTOQUE");
                        float valor = rs.getFloat("PRECO");
                        String fornecedor = rs.getString("NomeFantasia");
                        
                        Produto item = new Produto(categoria, produto, estoque, valor, fornecedor);
                        dadosProduto.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção de acordo com sua lógica de tratamento de erros
        }

        // Configurar os dados da TableView
        tableView.setItems(dadosProduto);
    }


    @FXML
    private void btnCarrinho_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/carrinho.fxml");
    }

    @FXML
    private void btnComprar_Click(ActionEvent event) {
        String usuarioLogado = Login.getUsuarioLogado();
        Produto produtoSelecionado = tableView.getSelectionModel().getSelectedItem();

        if (usuarioLogado != null) {
            if (produtoSelecionado != null) {
                Float quantidadeSelecionada = produtoSelecionado.getQuantidade();

                if (quantidadeSelecionada != null && quantidadeSelecionada > 0) {
                    //&& quantidadeSelecionada > 0
                    String fornecedorProduto = produtoSelecionado.getFornecedor();
                    Float valorTotal = quantidadeSelecionada * produtoSelecionado.getValor();
                    Float valor = produtoSelecionado.getValor();

                    inserirNoCarrinho(produtoSelecionado, usuarioLogado, quantidadeSelecionada, valor, valorTotal, fornecedorProduto);
                    // Outras ações, se necessário...
                } else {
                    alert("Forneça uma");
                }
            } else {
                alert("Selecione um produto para comprar.");
            }
        } else {
            alert("Você precisa fazer login para comprar.");
        }
    }
    
    private void configurarColunaQuantidade() {
        Quantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        Quantidade.setCellFactory(column -> {
            return new TableCell<Produto, Float>() {
                private final ComboBox<Float> comboBox = new ComboBox<>(FXCollections.observableArrayList(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f, 12f, 13f, 14f, 15f, 20f, 25f, 30f, 40f, 45f));

                {
                    comboBox.setOnAction(event -> {
                        Float quantidadeSelecionada = comboBox.getValue();
                        Produto produto = getTableView().getItems().get(getIndex());
                        produto.setQuantidade(quantidadeSelecionada);
                    });
                }

                @Override
                protected void updateItem(Float item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        comboBox.setValue(item);
                        setGraphic(comboBox);
                    }
                }
            };
        });
    }
    
    @FXML
    private void btnDeslogar_Click(ActionEvent event) {
        mudaTela(event,"/br/com/fatec/marketplace/login.fxml");
    }
    
    private void inserirNoCarrinho(Produto produto, String login, Float quantidade,Float valor, Float valorTotal, String fornecedorProduto) {
        boolean inseriu;
        try (Connection conn = connect()) {
            conn.setAutoCommit(false);
            
            String query = "INSERT INTO carrinho (produto, login, quantidade, valor, valorTotal, fornecedor) VALUES (?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, produto.getProduto());
                pst.setString(2, login);
                pst.setFloat(3, quantidade);
                pst.setFloat(4, valor);
                pst.setFloat(5, valorTotal);
                pst.setString(6, fornecedorProduto);
                //faltam = fornecedor
                System.out.println("produto: " + produto.getProduto());
                System.out.println("login: " + login);
                System.out.println("quantidade: " + quantidade);
                System.out.println("valor: " + valor);
                System.out.println("valorTotal: " + valorTotal);
                System.out.println("fornecedorProduto: " + fornecedorProduto);

                int linhasAfetadas = pst.executeUpdate();

                //executa comando
                if(linhasAfetadas > 0){
                    //mensagem.info("Inserção bem-sucedida!");
                    info("Compra realizada com sucesso!");
                    System.out.println("Inserção bem-sucedida!");

                    inseriu = true;//tudo certo com a inserção 
                }else{
                    //mensagem.alert("Nenhuma linha foi inserida.");
                    inseriu = false; 
                    System.out.println("Nenhuma linha foi inserida.");
                }
                conn.commit();
                pst.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção de acordo com sua lógica de tratamento de erros
        }
    }
    
    private void carregarDadosDoBanco() {
        ObservableList<Produto> dadosProduto = FXCollections.observableArrayList();
        //dadosProduto = FXCollections.observableArrayList();

        // Conectar ao banco de dados
        try (Connection conn = connect()) {
            String query = "SELECT CATEGORIA, DESCRICAO, ESTOQUE, PRECO, NomeFantasia FROM produto";
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        String categoria = rs.getString("CATEGORIA");
                        String produto = rs.getString("DESCRICAO");
                        float estoque = rs.getFloat("ESTOQUE");
                        float valor = rs.getFloat("PRECO");
                        String fornecedor = rs.getString("NomeFantasia");
                        
                        Produto item = new Produto(categoria, produto, estoque, valor, fornecedor);
                        dadosProduto.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trate a exceção de acordo com sua lógica de tratamento de erros
        }

        // Configurar os dados da TableView
        tableView.setItems(dadosProduto);
    }
    
    public void info(String msg){    
        Alert alerta = new Alert (Alert.AlertType.INFORMATION);
        alerta.setTitle("Mensagem");
        alerta.setHeaderText(msg);
        alerta.setContentText("");
               
        alerta.showAndWait(); //exibe mensagem
    }
    
    public void alert(String msg){     
        Alert alerta = new Alert (Alert.AlertType.WARNING);
        alerta.setTitle("Atenção!");
        alerta.setHeaderText(msg);
               
        alerta.showAndWait(); //exibe mensagem
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

}
