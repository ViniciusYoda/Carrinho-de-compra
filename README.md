# Carrinho de compra

Api para controle de compras de um carrinhos

## Endpoints

- Produtos
    - [Cadastrar](#cadastrar-produto)
    - [Mostrar detalhes](#detalhar-produto)
    - [Apagar](#apagar-produto)
    - [Atualizar](#detalhar-atualização)
    - [Listar todas](#listar-produto)
- Contas
- Categorias

---

### Cadastrar produto

`POST` /api/produto

**Campos da requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| id | id | sim | O id precisa ser diferente para cada produto
| Nome | string | sim | O nome do produto a ser colocado
| Preço | float | sim | O preço do produto, precisa ser maior que R$0,05

**Exemplo de corpo de requisição**

```js
{
    id: 1,
    nome: 'Arroz',
    preco: 4.15
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 201 | produto cadastrado com sucesso
| 400 | os campos enviados são inválidos

---

### Detalhar Produto

`GET` /api/produto/{id}

**Exemplo de Corpo da Resposta**

```js
{
    id: 1,
    nome: 'Arroz',
    preco: 4.15
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | dados retornados com sucesso
| 404 | não existe despesa com o id informado

---

### Apagar Produto

`DELETE`/api/produto/{id}

| código | descrição
|-|-
| 200 | dados retornados com sucesso
| 404 | não existe despesa com o id informado

---

### Atualizar Produto

`PUT` /api/produto/{id}


**Exemplo de corpo de requisição**

```js
{
    id: 1,
    nome: 'Arroz',
    preco: 4.15
}
```

**Exemplo de Corpo da Resposta**

```js
{
    id: 1,
    nome: 'Arroz',
    preco: 4.30
}
```

--- 

### Listar Produto

`GET` /api/produto

**Exemplo de Corpo da Resposta**

```js
{
    id: 1,
    nome: 'Arroz',
    preco: 4.30
},
{
    id: 2,
    nome: 'Feijão',
    preco: 8.98
},
{
    id: 3,
    nome: 'Macarrão',
    preco: 3.98
}
```
