package br.com.fiap.carrinhoDeCompras.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private String tipoDePagamento;
    private Boolean sucessBoolean;

    public Pagamento() {

    }

    public Pagamento(Long id, Double valor, String tipoDePagamento, Boolean sucessBoolean) {
        this.id = id;
        this.valor = valor;
        this.tipoDePagamento = tipoDePagamento;
        this.sucessBoolean = sucessBoolean;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getTipoDePagamento() {
        return tipoDePagamento;
    }

    public void setTipoDePagamento(String tipoDePagamento) {
        this.tipoDePagamento = tipoDePagamento;
    }

    public Boolean getSucessBoolean() {
        return sucessBoolean;
    }

    public void setSucessBoolean(Boolean sucessBoolean) {
        this.sucessBoolean = sucessBoolean;
    }

    @Override
    public String toString() {
        return "Pagamento [id=" + id + ", valor=" + valor + ", tipoDePagamento=" + tipoDePagamento + ", sucessBoolean="
                + sucessBoolean + "]";
    }

    



        
    
    
    
    
    
}
