package igor.conversor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cotacao")
public class Connect {
    private final DataSource dataSource;
    public Connect(DataSource dataSource){
        this.dataSource = dataSource;
    }
    public record Valor(double compra, double venda){}
    @GetMapping
    public Valor getcotacao(@RequestParam String abvmoeda){
        try{
            Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM valores WHERE abreviatura = ? ORDER BY dia DESC LIMIT 1");
            stmt.setString(1, abvmoeda);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                double compra = rs.getDouble("cotacao_compra");
                double venda = rs.getDouble("cotacao_venda");
                conn.close();
                return new Valor(compra , venda);
            }
            conn.close();
            return new Valor(0,0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Valor(0, 0);
    }
}