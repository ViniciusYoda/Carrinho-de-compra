# Carrinho de compra

Api para controle de compras de um carrinhos

## Endpoints

- Produtos
    - [Cadastrar](#cadastrar-produto)
    - [Mostrar detalhes](#detalhar-produto)
    - [Apagar](#apagar-produto)
    - [Atualizar](#detalhar-atualização-produto)
    - [Listar todas](#listar-produto)
- Pagamento
    - [Cadastrar](#cadstrar-pagamento)
    - [Mostrar pagamento](#dedtalhar-pagamento)
    - [Apagar](#apagar-pagamento)
    - [Atualizar](#detalhar-atualização-pagamento)
    - [Listar todos](#listar-pagamentos)

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
| 404 | não existe produto com o id informado

---

### Apagar Produto

`DELETE`/api/produto/{id}

| código | descrição
|-|-
| 200 | produto apagado com sucesso
| 404 | não existe produto com o id informado

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

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | produto atualizado com sucesso
| 404 | produto com id não encontrado

--- 

### Listar Produto

`GET` /api/produtos

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

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | produto listado com sucesso
| 404 | produtos não encontrado

---

### Cadastrar pagamento

`POST` /api/pagamento

**Campos da requisição**

| campo | tipo | obrigatório | descrição
|-------|------|:-------------:|---
| id | id | sim | O id precisa ser diferente para cada produto
| Valor | Double | sim | Valor para pagar
| tipoDePagamento | String | sim | Qual tipo de pagamento 
| sucessBoolean | Boolean | sim | Se o pagamento foi um sucesso ou não

**Exemplo de corpo de requisição**

```js
{
    id: 1,
    valor: 54.60,
    tipoDePagamento: 'Débito',
    sucessBoolean: true
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 201 | pagamento cadastrado com sucesso
| 400 | os campos enviados são inválidos

---

### Detalhar Pagamento

`GET` /api/pagamento/{id}

**Exemplo de Corpo da Resposta**

```js
{
    id: 1,
    valor: 54.60,
    tipoDePagamento: 'Débito',
    sucessBoolean: true
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | dados retornados com sucesso
| 404 | não existe pagamento com o id informado

---

### Atualizar Pagamento

`PUT` /api/pagamento/{id}


**Exemplo de corpo de requisição**

```js
{
    id: 1,
    valor: 54.60,
    tipoDePagamento: 'Débito',
    sucessBoolean: true
}
```

**Exemplo de Corpo da Resposta**

```js
{
    id: 1,
    valor: 54.60,
    tipoDePagamento: 'Boleto',
    sucessBoolean: true
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | pagamento atualizado com sucesso
| 404 | pagamento com id não encontrado

--- 

### Apagar Produto

`DELETE`/api/pagamento/{id}

| código | descrição
|-|-
| 200 | pagamento apagado com sucesso
| 404 | não existe pagamento com o id informado

---

### Listar Pagamento

`GET` /api/pagamentos

**Exemplo de Corpo da Resposta**

```js
{
    id: 1,
    valor: 54.60,
    tipoDePagamento: 'Débito',
    sucessBoolean: true
},
{
    id: 2,
    valor: 20.90,
    tipoDePagamento: 'Crédito',
    sucessBoolean: false
},
{
    id: 3,
    valor: 200.00,
    tipoDePagamento: 'Boleto',
    sucessBoolean: true
},
{
    id: 4,
    valor: 89.99,
    tipoDePagamento: 'Transferência',
    sucessBoolean: true
}
```

**Códigos de Resposta**

| código | descrição
|-|-
| 200 | pagamento listado com sucesso
| 404 | pagamentos não encontrado

---
