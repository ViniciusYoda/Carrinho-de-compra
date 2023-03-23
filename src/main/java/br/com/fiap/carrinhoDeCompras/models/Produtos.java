package br.com.fiap.carrinhoDeCompras.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produtos {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private double price;

   public Produtos(){}

   public Produtos(Long id, String name, double price){
      this.id = id;
      this.name = name;
      this.price = price;
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

   @Override
   public String toString() {
       return "Produto [id=" + id + ", name=" + name + ", price=" + price + "]";
   }

   
}