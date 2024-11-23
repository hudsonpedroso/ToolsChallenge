# Tools Challenge - API de Pagamentos

## 📚 Descrição
Este projeto é uma API RESTful desenvolvida como parte do **Tools Challenge**, um desafio técnico focado em resolver problemas práticos enfrentados no dia a dia de um desenvolvedor. A API é projetada para gerenciar transações de pagamento em um cenário de banco, especificamente na área de cartões de crédito. 

A API inclui operações como:
- **Pagamento**: Processamento de transações.
- **Estorno**: Cancelamento de transações já realizadas.
- **Consulta**: Obtenção de transações por ID, listagem completa com ou sem paginação.

## 🛠 Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**
- **Banco de Dados H2** (embarcado, utilizado para simulação)
- **Cache** para otimização de consultas.
- **Design Patterns** aplicando **DDD (Domain-Driven Design)**.
- **Controle de Exceções** para respostas HTTP padronizadas.

## 📑 Requisitos das Transações
- **ID da Transação**: Deve ser único.
- **Status da Transação**: Pode assumir os valores:
  - `AUTORIZADO`
  - `NEGADO`
- **Forma de Pagamento**:
  - `AVISTA`
  - `PARCELADO LOJA`
  - `PARCELADO EMISSOR`

## 📬 Endpoints da API

### Realizar Pagamento
- **POST /api/pagamentos**
  - **Descrição**: Realiza o processamento de um pagamento.
  - **Request**:
    ```json
    {
      "transacao": {
        "id": 1,
        "cartao": "4444********1234",
        "descricao": {
          "valor": 150.75,
          "dataHora": "22/11/2024 14:30:00",
          "estabelecimento": "Loja XYZ"
        }
      },
      "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": 1
      }
    }
    ```
  - **Response**:
    ```json
    {
      "transacao": {
        "id": 1,
        "cartao": "4444********1234",
        "descricao": {
          "valor": 150.75,
          "dataHora": "22/11/2024 14:30:00",
          "estabelecimento": "Loja XYZ",
          "status": "AUTORIZADO"
        }
      },
      "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": 1
      }
    }
    ```
  - **Status HTTP**: `201 Created`

### Realizar Estorno
- **POST /api/pagamentos/{id}/estorno**
  - **Descrição**: Realiza o estorno de uma transação existente.
  - **Response**:
    ```json
    {
      "transacao": {
        "id": 1,
        "cartao": "4444********1234",
        "descricao": {
          "valor": 150.75,
          "dataHora": "22/11/2024 14:30:00",
          "estabelecimento": "Loja XYZ",
          "status": "ESTORNADO"
        }
      },
      "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": 1
      }
    }
    ```
  - **Status HTTP**: `200 OK`

### Consultar Transação por ID
- **GET /api/pagamentos/{id}**
  - **Descrição**: Retorna os detalhes de uma transação específica.
  - **Response**:
    ```json
    {
      "transacao": {
        "id": 1,
        "cartao": "4444********1234",
        "descricao": {
          "valor": 150.75,
          "dataHora": "22/11/2024 14:30:00",
          "estabelecimento": "Loja XYZ",
          "status": "AUTORIZADO"
        }
      },
      "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": 1
      }
    }
    ```
  - **Status HTTP**: `200 OK`

### Consultar Todas as Transações
- **GET /api/pagamentos/transacoes**
  - **Descrição**: Retorna todas as transações cadastradas.
  - **Response**:
    ```json
    [
      {
        "transacao": {
          "id": 1,
          "cartao": "4444********1234",
          "descricao": {
            "valor": 150.75,
            "dataHora": "22/11/2024 14:30:00",
            "estabelecimento": "Loja XYZ",
            "status": "AUTORIZADO"
          }
        },
        "formaPagamento": {
          "tipo": "AVISTA",
          "parcelas": 1
        }
      }
    ]
    ```
  - **Status HTTP**: `200 OK`

### Consultar Transações com Paginação
- **GET /api/pagamentos/transacoes/{pages}/inicio/{start}**
  - **Descrição**: Retorna as transações com suporte a paginação.
  - **Response**:
    ```json
    {
      "content": [
        {
          "transacao": {
            "id": 1,
            "cartao": "4444********1234",
            "descricao": {
              "valor": 150.75,
              "dataHora": "22/11/2024 14:30:00",
              "estabelecimento": "Loja XYZ",
              "status": "AUTORIZADO"
            }
          },
          "formaPagamento": {
            "tipo": "AVISTA",
            "parcelas": 1
          }
        }
      ],
      "pageable": {
        "pageNumber": 0,
        "pageSize": 10
      },
      "totalPages": 1
    }
    ```
  - **Status HTTP**: `200 OK`


## 🚀 Como Rodar o Projeto
1. **Pré-requisitos**:
   - JDK 21 ou superior.
   - Maven 3.8+ instalado.

2. **Clone o Repositório**:
   ```bash
   git clone https://github.com/hudsonpedroso/ToolsChallenge.git
   cd ToolsChallenge
