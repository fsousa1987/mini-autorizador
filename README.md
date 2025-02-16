### 📌 **README.md para o Mini Autorizador**

# 🏦 Mini Autorizador VR

🚀 Projeto de um **Mini Autorizador** de transações baseado no desafio técnico da **VR**.  
Este sistema permite **criação de cartões, consulta de saldo e processamento de transações**, garantindo regras de autorização e concorrência segura.

---

## 📜 **Requisitos Atendidos**
✔ **Criar cartões** com saldo inicial de **R$500,00**  
✔ **Consultar saldo** de um cartão existente  
✔ **Realizar transações**, garantindo regras de autorização  
✔ **Segurança** com **Basic Auth**  
✔ **Concorrência controlada** para evitar saldo negativo em múltiplas transações simultâneas  
✔ **Testes unitários e de integração completos**  

---

## 🛠 **Tecnologias Utilizadas**
🔹 **Java 21**  
🔹 **Spring Boot 3**  
🔹 **Spring Data JPA**  
🔹 **Spring Security (Basic Auth)**  
🔹 **MySQL Database**  
🔹 **JUnit 5 & Mockito**  
🔹 **Docker & Docker Compose**  

---

## 🚀 **Como Executar o Projeto**
### 1️⃣ **Pré-requisitos**
🔹 Instale o **Docker** e **Docker Compose**  

### 2️⃣ **Rodando a Aplicação**
```sh
docker-compose up -d
./mvnw spring-boot:run
```

---

## 🎯 **Endpoints da API**
### 📌 **1️⃣ Criar um Novo Cartão**
```
POST /cartoes
Autenticação: Basic Auth (username/password)
```
🔹 **Exemplo de Requisição:**
```json
{
  "numeroCartao": "1234567890123456",
  "senha": "1234"
}
```
🔹 **Respostas:**
| Código | Resposta |
|--------|----------|
| **201 Created** ✅ | Cartão criado com sucesso |
| **422 Unprocessable Entity** ❌ | Cartão já existe |

---

### 📌 **2️⃣ Consultar Saldo**
```
GET /cartoes/{numeroCartao}
Autenticação: Basic Auth (username/password)
```
🔹 **Respostas:**
| Código | Resposta |
|--------|----------|
| **200 OK** ✅ | Retorna o saldo do cartão |
| **404 Not Found** ❌ | Cartão não encontrado |

---

### 📌 **3️⃣ Realizar uma Transação**
```
POST /transacoes
Autenticação: Basic Auth (username/password)
```
🔹 **Exemplo de Requisição:**
```json
{
  "numeroCartao": "1234567890123456",
  "senhaCartao": "1234",
  "valor": 10.00
}
```
🔹 **Respostas:**
| Código | Resposta |
|--------|----------|
| **201 Created** ✅ | Transação aprovada |
| **422 Unprocessable Entity** ❌ | `SALDO_INSUFICIENTE`, `SENHA_INVALIDA`, `CARTAO_INEXISTENTE` |

---

## 🧪 **Rodando os Testes**
Para rodar os testes unitários e de integração:
```sh
./mvnw test
```
✔ **Cobertura completa das regras de negócio**  
✔ **Testes de Service e Controller com `MockMvc`**  
✔ **Verificação de concorrência e segurança**

---

## 📌 **Autor**
👤 **Francisco Edglei de Sousa**

---