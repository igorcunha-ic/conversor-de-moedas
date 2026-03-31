package igor.conversor;

import java.sql.*;

import javax.sql.DataSource;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Cotacao")
public class Connect {
    private final DataSource dataSource;
    public Connect(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @GetMapping
    public String getcotacao(){
        try{
            Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM valores ORDER BY id DESC LIMIT 1");
            if(rs.next()){
                String compra = rs.getString("cotacao_compra");
                String venda = rs.getString("cotacao_venda");
                return "{ \"compra\": " + compra + ", \"venda\": " + venda + " }";
            }
            conn.close();
            return "drogas";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "{}";
    }
}
