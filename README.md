# Documentação das Requisições e Respostas da API

## 1. OBJETIVO

Este documento tem por objetivo especificar a parte técnica relacionada ao consumo
do serviço de cadastro do motorista. Para tal, serão descritas abaixo as URIs que
respondem as requisições to front-end, assim como os dados (payload) esperados por
essas URIs com algumas informações pessoais de exemplo, descrição dos campos
obrigatórios, os métodos de requisição.

## 2. MÉTODOS E URI

### 2.1. POST /MOTORISTA

Requisição do tipo POST para /motorista e payload enviado:

```json
{
    "nome":"Fulano de Tall",
    "telefone":"+55(62)98745-6321",
    "cpf" : "000.000.000-00",
    "cnh": "123456789",
    "dataDeNascimento":"1984-08-30",
    "endereco": {
        "cep":"76400-000",
        "cidade":"Goiânia",
        "bairro":"Alto da Glória",
        "complemento":"Próximo ao Carrefour",
        "numero":"123",
        "localidade":"GO",
        "logradouro":"Avenida 2 Radial"
    },
    "automovel": {
        "modelo":"Renault",
        "nomeCarro":"Master",
        "placa":"DKV0J01"
    }
}
```

Resposta esperada para esta requisição:

```json
{
    "id": 33,
    "nome": "Fulano de Tall",
    "cpf": "000.000.000-00",
    "cnh": "123456789",
    "endereco": {
        "cep": "76400-000",
        "cidade": "Goiânia",
        "numero": "123",
        "logradouro": "Avenida 2 Radial",
        "complemento": "Próximo ao Carrefour",
        "bairro": "Alto da Glória",
        "localidade": "GO"
    },
    "telefone": "+55(62)98745-6321",
    "dataDeNascimento": "1984-08-30T00:00:00.000+00:00",
    "automovel": {
        "id": 33,
        "modelo": "Renault",
        "nomeCarro": "Master",
        "placa": "DKV0J01"
    }
}
```

## 2.2. GET /MOTORISTA/ID

Requisição do tipo GET para /motorista/id e a resposta recebida:

```json
{
    "id": 1,
    "nome": "Fulano de Tall",
    "cpf": "000.000.000-00",
    "cnh": "123456789",
    "endereco": {
        "cep": "76400-000",
        "cidade": "Goiânia",
        "numero": "123",
        "logradouro": "Avenida 2 Radial",
        "complemento": "Próximo ao Carrefour",
        "bairro": "Alto da Glória",
        "localidade": "GO"
    },
    "telefone": "+55(62)98745-6321",
    "dataDeNascimento": "1984-08-30T00:00:00.000+00:00",
    "automovel": {
        "id": 1,
        "modelo": "Renault",
        "nomeCarro": "Master",
        "placa": "DKV0J01"
    }
}
```

## 2.3. PUT /MOTORISTA/ID

Requisição do tipo PUT para /motorista/id e payload enviado:

{
    "nome":"Motoristaatualiza21",
    "telefone":"231232132323232",
    "cpf" : "23232323232323",
    "cnh": "23123123212",
    "dataNascimento":"2222-00-22",
    "endereco": {
        "cep":"03101010",
        "cidade":"suzano",
        "bairro":"aaa",
        "complemento":"apto 47",
        "numero":"331",
        "localidade":"asd",
        "logradouro":"asd"
    },
    "automovel": {
        "modelo":"testee",
        "nomeCarro":"poa",
        "placa":"nova poa"
    }
}

Resposta esperada para esta requisição:
    
{
    "id": 1,
    "nome": "Motoristaatualiza21",
    "cpf": "23232323232323",
    "cnh": "23123123212",
    "endereco": {
        "cep": "03101010",
        "cidade": "suzano",
        "numero": "331",
        "logradouro": "asd",
        "complemento": "apto 47",
        "bairro": "aaa",
    "localidade": "asd"
    },
    "telefone": "231232132323232",
    "automovel": {
        "id": null,
        "modelo": "testee",
        "nomeCarro": "poa",
        "placa": "nova poa"
    }
}