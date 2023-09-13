package br.com.fiap.carrinhoDeCompras.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.carrinhoDeCompras.exceptions.RestNotFoundException;
import br.com.fiap.carrinhoDeCompras.models.Produtos;
import br.com.fiap.carrinhoDeCompras.repository.PagamentoRepository;
import br.com.fiap.carrinhoDeCompras.repository.ProdutosRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/produtos")
@Slf4j
// @SecurityRequirement(name = "bearer-key")
@Tag(name = "produtos")
public class ProdutosController {

   @Autowired
   ProdutosRepository produtosRepository;

   @Autowired
   PagamentoRepository pagamentoRepository;

   @Autowired
   PagedResourcesAssembler<Object> assembler;

   @GetMapping
   @Operation(
      summary = "Listar produtos",
      description = "Endpoint que retorna todos os produtos criados"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "200", description = "produto listado com sucesso"),
      @ApiResponse(responseCode = "400", description = "produtos não encontrado")
   })
   public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 5) Pageable pageable){
      Page<Produtos> produtos = (busca == null)?
         produtosRepository.findAll(pageable):
         produtosRepository.findByNameContaining(busca, pageable);

      return assembler.toModel(produtos.map(Produtos::toEntityModel));
   }

   @PostMapping
   @Operation(
      summary = "Criar produto",
      description = "Endpoint que cria o produto, com id, nome, preço e categoria. O id deve ser único para cada produto"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "201", description = "produto cadastrado com sucesso"),
      @ApiResponse(responseCode = "400", description = "os campos enviados são inválidos")
   })
   public ResponseEntity<Object> create(@RequestBody @Valid Produtos produto){
      log.info("Cadastro do produto " + produto);
      produtosRepository.save(produto);
      produto.setPagamento(pagamentoRepository.findById(produto.getPagamento().getId()).get());
      return ResponseEntity
      .status(HttpStatus.CREATED).body(produto.toEntityModel());

   }

   @GetMapping("{id}")
   @Operation(
      summary = "Detalhar produto",
      description = "Endpoint que recebe um id e retorna os dados do produto. O id deve ser único para cada produto"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "201", description = "dados retornados com sucesso"),
      @ApiResponse(responseCode = "400", description = "não existe produto com o id informado")
   })
   public EntityModel<Produtos> show(@PathVariable Long id){
      log.info("Detalhe do produto" + id);
      var produto = produtosRepository.findById(id)
            .orElseThrow(() -> new RestNotFoundException("produto não encontrado"));

      return produto.toEntityModel();
   }

   @DeleteMapping("{id}")
   @Operation(
      summary = "Deletar produto",
      description = "Endpoint que recebe um id e deleta o produto. O id deve ser único para cada produto"
   )
   @ApiResponses ({
      @ApiResponse(responseCode = "201", description = "produto apagado com sucesso"),
      @ApiResponse(responseCode = "400", description = "não existe produto com o id informado")
   })
   public ResponseEntity<Produtos> destroy(@PathVariable Long id){
      log.info("Apagando o produto" + id);
      var produto = produtosRepository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, produto não encontrado"));

      produtosRepository.delete(produto);   

      return ResponseEntity.noContent().build();
   }

   @PutMapping("{id}")
   @Operation(
      summary = "Atualizar produto",
      description = "Endpoint que recebe um id e retorna os dados do produto atualizado. O id deve ser único para cada produto"
   )
   public EntityModel<Produtos> update(@PathVariable Long id, @RequestBody @Valid Produtos produto){
      log.info("Produto atualizado " + id);
      produtosRepository.findById(id)
         .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, produto não encontrado"));

      produto.setId(id);
      produtosRepository.save(produto);

      return produto.toEntityModel();

   }
   
}