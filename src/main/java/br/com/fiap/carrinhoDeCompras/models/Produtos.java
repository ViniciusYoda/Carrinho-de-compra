package br.com.fiap.carrinhoDeCompras.models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.data.domain.Pageable;

import br.com.fiap.carrinhoDeCompras.controllers.ProdutosController;
import br.com.fiap.carrinhoDeCompras.controllers.PagamentoController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

   @ManyToOne
   private Pagamento pagamento;   
   
   public EntityModel<Produtos> toEntityModel(){
      return EntityModel.of(
          this, 
          linkTo(methodOn(ProdutosController.class).show(id)).withSelfRel(),
          linkTo(methodOn(ProdutosController.class).destroy(id)).withRel("delete"),
          linkTo(methodOn(ProdutosController.class).lista(null, Pageable.unpaged())).withRel("all"),
          linkTo(methodOn(PagamentoController.class).show(this.getPagamento().getId())).withRel("pagamento")
      );
  }



   
}