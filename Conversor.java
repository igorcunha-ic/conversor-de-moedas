package igor.conversor;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conversor {
    public static void main(String[] args) {
        String user = "root";
        String senha_banco = "senha falsa";
        String url = "jdbc:mysql://localhost:3306/moedas";
        try {
            Connection conn = DriverManager.getConnection(url, user, senha_banco);
            System.out.println("sucesso");
            URL urlcot = new URL("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaPeriodo(moeda=@moeda,dataInicial=@dataInicial,dataFinalCotacao=@dataFinalCotacao)?@moeda='USD'&@dataInicial='03-19-2026'&@dataFinalCotacao='03-26-2026'&$top=1&$orderby=dataHoraCotacao%20desc&$format=json&$select=cotacaoCompra,cotacaoVenda,dataHoraCotacao");
            Inserir inserir = new Inserir();
            inserir.inserir("Dolar", "USD", urlcot, conn);
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}