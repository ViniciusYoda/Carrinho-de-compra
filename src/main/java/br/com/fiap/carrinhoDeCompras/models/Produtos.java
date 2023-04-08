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
@Data
@NoArgsConstructor
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

   
}