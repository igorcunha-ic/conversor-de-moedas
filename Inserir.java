package br.dev.selenium;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.fasterxml.jackson.databind.ObjectMapper;
public class Inserir {
    void inserir(String moeda, String abreviatura, URL json, Connection conn){
        try{
        ObjectMapper leitura = new ObjectMapper();
        bacen resposta = leitura.readValue(json, bacen.class);
        Cotacao cotacao = resposta.getValue().get(0);
        System.out.println("Compra: " + cotacao.getCotacaoCompra());
        System.out.println("Venda: " + cotacao.getCotacaoVenda());
        String dataHoraCotacao = cotacao.getdataHoraCotacao();
        float cotacao_compra = cotacao.getCotacaoCompra();
        float cotacao_venda = cotacao.getCotacaoVenda();
        String input = "INSERT INTO valores (nome, abreviatura, cotacao_compra, cotacao_venda, dia) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(input);
        stmt.setString(1, moeda);
        stmt.setString(2, abreviatura);
        stmt.setFloat(3, cotacao_compra);
        stmt.setFloat(4, cotacao_venda);
        stmt.setString(5, dataHoraCotacao);
        stmt.executeUpdate();
        System.out.println("Inserido no banco!");
        stmt.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
