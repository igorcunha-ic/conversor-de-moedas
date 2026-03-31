package igor.conversor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Bacen {
    private List<Cotacao> value;
    public List<Cotacao> getValue(){
        return value;
    }
    public void setvalue(List<Cotacao> value) {
        this.value = value;
    }
}
