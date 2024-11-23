# Tools Challenge - API de Pagamentos

## 📚 Descrição
Este projeto é uma API RESTful desenvolvida como parte do **Tools Challenge**, um desafio técnico focado em resolver problemas práticos enfrentados no dia a dia de um desenvolvedor. A API é projetada para gerenciar transações de pagamento em um cenário de banco, especificamente na área de cartões de crédito. 

A API inclui operações como:
- **Pagamento**: Processamento de transações.
- **Estorno**: Cancelamento de transações já realizadas.
- **Consulta**: Obtenção de transações por ID, listagem completa ou completa com paginação.

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

## 🚀 Como Rodar o Projeto
1. **Pré-requisitos**:
   - JDK 21 ou superior.
   - Maven 3.8+ instalado.

2. **Clone o Repositório**:
   ```bash
   git clone https://github.com/hudsonpedroso/ToolsChallenge.git
   cd ToolsChallenge
