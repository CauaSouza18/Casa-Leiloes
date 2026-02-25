/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
  public void cadastrarProduto(ProdutosDTO produto) {
    conn = new conectaDAO().connectDB(); // Abre a conexão
    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
    
    try {
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        
        prep.executeUpdate(); // Executa o comando no banco
        
        // Mensagem de sucesso solicitada
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
        
    } catch (Exception e) {
        // Mensagem de erro caso algo falhe
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar: " + e.getMessage());
    }
}
  public ArrayList<ProdutosDTO> listarProdutos() {
    String sql = "SELECT * FROM produtos";
    conn = new conectaDAO().connectDB();
    
    try {
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            
            listagem.add(produto); // Adiciona na lista definida na sua classe
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar: " + e.getMessage());
    }
    return listagem;
}
        
}

