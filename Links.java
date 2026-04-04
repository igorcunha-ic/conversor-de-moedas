package igor.conversor;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;
@Component
public class Links {
    public record Moeda(String abv, String nome){}
    public Moeda abrev(int n){
        return switch (n) {
            case 1 -> new Moeda("DKK", "coroa dinamarquesa");
            case 2 -> new Moeda("NOK", "coroa norueguesa");
            case 3 -> new Moeda("SEK", "coroa sueca");
            case 4 -> new Moeda("USD", "dolar americano");
            case 5 -> new Moeda("AUD", "dolar australiano");
            case 6 -> new Moeda("CAD", "dolar canadense");
            case 7 -> new Moeda("EUR", "euro");
            case 8 -> new Moeda("CHF", "franco suico");
            case 9 -> new Moeda("JPY", "iene");
            case 10 -> new Moeda("GBP", "libra esterlina");
            default -> throw new AssertionError();
        };
    }
    public String link(String abv){
        LocalDate data = LocalDate.now();
        LocalDate data_inicial = data.minusDays(5);
        DateTimeFormatter form = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String date_inicial = data_inicial.format(form);
        String date = data.format(form);
        String l1 = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaPeriodo(moeda=@moeda,dataInicial=@dataInicial,dataFinalCotacao=@dataFinalCotacao)?@moeda='";
        String l2 = "'&@dataInicial='";
        String l3 = "'&@dataFinalCotacao='";
        String l4 = "'&$top=1&$orderby=dataHoraCotacao%20desc&$format=json&$select=cotacaoCompra,cotacaoVenda,dataHoraCotacao";
        String link = l1 + abv + l2 + date_inicial + l3 + date + l4;
        return link;
    }
}
//https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaPeriodo(moeda=@moeda,dataInicial=@dataInicial,dataFinalCotacao=@dataFinalCotacao)?@moeda='USD'&@dataInicial='03-26-2026'&@dataFinalCotacao='04-01-2026'&$top=1&$orderby=dataHoraCotacao%20desc&$format=json&$select=cotacaoCompra,cotacaoVenda,dataHoraCotacao