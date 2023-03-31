package br.com.fiap.carrinhoDeCompras.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Produtos {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   @NotBlank @Size(min = 4, max = 20)
   private String name;

   @Min(value = 0, message = "deve ser positivo")
   @NotNull
   private double price;

   @NotBlank @Size(min = 5, max = 255)
   private String categoria;

   public Produtos(){}

   public Produtos(Long id, String name, double price, String categoria){
      this.id = id;
      this.name = name;
      this.price = price;
      this.categoria = categoria;
   }

   public Long getId(){
      return id;
   }

   public void setId(Long id){
      this.id = id;
   }

   public String getName(){
      return name;
   }

   public void setName(String name){
      this.name = name;
   }

   public double getPrice(){
      return price;
   }

   public void setPrice(double price){
      this.price = price;
   }

   public String getCategoria() {
      return categoria;
   }

   public void setCategoria(String categoria) {
      this.categoria = categoria;
   }

   @Override
   public String toString() {
       return "Produto [id=" + id + ", name=" + name + ", price=" + price + ", categoria= " + categoria + "]";
   }

   
}