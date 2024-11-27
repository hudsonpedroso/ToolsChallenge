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
- **Controle de Rollback** utilizando @Transactional.
- **Controle de Exceções** para respostas HTTP padronizadas.
- **Swagger** para documentação da api e acionamento dos endpoints.
- **IDE** IntelliJ IDEA Community Edition 2024.2.4.

## 📑 Requisitos das Transações
- **ID da Transação**: Deve ser único.
- **Para simular pagamento NEGADO, usar o cartão:**
  - `1111********1111`
- **Para simular pagamento AUTORIZADO, usar qualquer outro cartão, exemplo:**
  - `4444********1234`
- **Status da Transação**: Pode assumir os valores:
  - `AUTORIZADO`
  - `NEGADO`
  - `CANCELADO`
- **Forma de Pagamento**:
  - `AVISTA`
  - `PARCELADO LOJA`
  - `PARCELADO EMISSOR`

## 💾 Banco de dados
- **Diagrama Entidade-Relacionamento** dbpagamentos:
  
  - ![image](https://github.com/user-attachments/assets/dd3d609c-7e1a-4087-b210-2b7d475b9fa6)

## 📬 Endpoints da API

- **Opção 1: Acesse via Swager UI conforme será instruido no console:**
  
  - ![image](https://github.com/user-attachments/assets/91047de2-fdf0-46d8-abee-24418af072c3)

- **Opção 2: Acesse via Postman, importando o arquivo *api-pagamentos.postman_collection* presente no diretório:**
  - `\api-pagamentos\src\main\resources\postman\api-pagamentos.postman_collection`

## 🚀 Como Rodar o Projeto

1. **Pré-requisitos**:
   - JDK 21 ou superior.
   - Maven 3.8+ instalado.
   - Estar com a porta 8080 liberada para acesso local.


