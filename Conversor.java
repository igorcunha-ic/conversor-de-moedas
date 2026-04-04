package igor.conversor;
import java.net.URL;
import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import igor.conversor.Links.Moeda;

@Component
public class Conversor implements CommandLineRunner{
    private DataSource dataSource;
    private Inserir inserir;
    private Links link;
    String abv, nome;
    public Conversor(DataSource dataSource, Inserir inserir, Links link){
        this.dataSource = dataSource;
        this.inserir = inserir;
        this.link = link;
    }
    @Override
    public void run(String... args) {
        try {
            Connection conn = dataSource.getConnection();
            System.out.println("sucesso");
            for(int i = 1; i <= 10; i++){
                Moeda moeda = link.abrev(i);
                abv = moeda.abv();
                nome = moeda.nome();
                URL urlcot = new URL(link.link(abv));
                inserir.inserir(nome, abv, urlcot, conn);
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
