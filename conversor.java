package br.dev.selenium;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.fasterxml.jackson.databind.ObjectMapper;

public class conversor {
    public static void main(String[] args) {
        String user = "root";
        String senha_banco = "VocEnAOSabe123@";
        String url = "jdbc:mysql://localhost:3306/moedas";
        try {
            Connection conn = DriverManager.getConnection(url, user, senha_banco);
            System.out.println("sucesso");

            ObjectMapper mapper = new ObjectMapper();
            URL urlcot = new URL("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaPeriodo(moeda=@moeda,dataInicial=@dataInicial,dataFinalCotacao=@dataFinalCotacao)?@moeda='USD'&@dataInicial='03-17-2026'&@dataFinalCotacao='03-24-2026'&$top=1&$orderby=dataHoraCotacao%20desc&$format=json&$select=cotacaoCompra,cotacaoVenda,dataHoraCotacao");
            bacen resposta = mapper.readValue(urlcot, bacen.class);
            Cotacao cotacao = resposta.getvalue().get(0);
            System.out.println("Compra: " + cotacao.getCotacaoCompra());
            System.out.println("Venda: " + cotacao.getCotacaoVenda());
            
            String dataHoraCotacao = cotacao.getdataHoraCotacao();
            float cotacao_compra = cotacao.getCotacaoCompra();
            float cotacao_venda = cotacao.getCotacaoVenda();
            String sql = "INSERT INTO valores (nome, abreviatura, cotacao_compra, cotacao_venda, dia) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Dolar");
            stmt.setString(2, "USD");
            stmt.setFloat(3, cotacao_compra);
            stmt.setFloat(4, cotacao_venda);
            stmt.setString(5, dataHoraCotacao);
            stmt.executeUpdate();
            System.out.println("Inserido no banco!");
            stmt.close();
            conn.close();

        } catch (Exception e) {
            System.out.println("Erro ao conectar com o banco");
            e.printStackTrace();
        }
    }
}