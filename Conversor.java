package igor.conversor;
import java.net.URL;
import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Conversor implements CommandLineRunner{
    private DataSource dataSource;
    private Inserir inserir;
    public Conversor(DataSource dataSource, Inserir inserir){
        this.dataSource = dataSource;
        this.inserir = inserir;
    }
    public void run(String... args) {
        try {
            Connection conn = dataSource.getConnection();
            System.out.println("sucesso");
            URL urlcot = new URL("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaPeriodo(moeda=@moeda,dataInicial=@dataInicial,dataFinalCotacao=@dataFinalCotacao)?@moeda='USD'&@dataInicial='03-19-2026'&@dataFinalCotacao='03-26-2026'&$top=1&$orderby=dataHoraCotacao%20desc&$format=json&$select=cotacaoCompra,cotacaoVenda,dataHoraCotacao");
            inserir.inserir("Dolar", "USD", urlcot, conn);
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
