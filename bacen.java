package br.dev.selenium;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class bacen {
    private List<Cotacao> value;
    public List<Cotacao> getvalue(){
        return value;
    }
    public void setvalue(List<Cotacao> value) {
        this.value = value;
    }
}
