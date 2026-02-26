# 🧾 API Convênios

API REST em DESENVOLVIMENTO em **Java com Spring Boot** para gerenciamento de convênios e operações relacionadas.  
O projeto foi construído com foco em organização arquitetural, segurança, boas práticas REST e escalabilidade.

Esta API faz parte de um ecossistema de aplicações backend integradas a sistemas web e mobile.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA / Hibernate**
- **MySQL / PostgreSQL**
- **Maven**
- **Docker / Docker Compose**
- **Git**

---

## 🧱 Arquitetura

A aplicação segue o padrão de **arquitetura em camadas**, promovendo separação clara de responsabilidades:

- **Controller** → Exposição dos endpoints REST
- **Service** → Regras de negócio
- **Repository** → Acesso ao banco via JPA
- **DTOs** → Transferência segura de dados
- **Config** → Configurações gerais (segurança, beans)
- **Exception Handler** → Tratamento global de erros

Essa organização facilita manutenção, testes e evolução do sistema.

---

## 🔐 Segurança

A API pode implementar controle de acesso utilizando:

- Autenticação baseada em **JWT**
- Configuração via **Spring Security**
- Proteção de rotas por perfil/permissão

Exemplo de uso do token no header:

```
Authorization: Bearer {token}
```

---

## 📌 Funcionalidades Principais

✔️ CRUD completo de convênios  
✔️ Validação de dados com Bean Validation  
✔️ Tratamento global de exceções  
✔️ Respostas HTTP padronizadas  
✔️ Paginação e filtros para listagens (se aplicável)  
✔️ Integração com frontend e aplicações mobile  
✔️ Estrutura preparada para expansão futura  

---

## 📁 Estrutura do Projeto

```
api-convenios
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── config
│   │   │   ├── controller
│   │   │   ├── service
│   │   │   ├── repository
│   │   │   ├── dto
│   │   │   ├── exception
│   │   │   └── model
│   │   └── resources
│   │       ├── application.properties
│   │       └── data.sql (opcional)
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

## 🚪 Endpoints (Exemplo)

> Ajuste conforme os nomes reais do seu projeto

### 📌 Convênios

| Método | Endpoint              | Descrição                     |
|--------|-----------------------|-------------------------------|
| GET    | `/convenios`          | Listar convênios              |
| GET    | `/convenios/{id}`     | Buscar convênio por ID        |
| POST   | `/convenios`          | Criar novo convênio           |
| PUT    | `/convenios/{id}`     | Atualizar convênio            |
| DELETE | `/convenios/{id}`     | Remover convênio              |

---

## 🛠 Como Rodar Localmente

### 🔹 Pré-requisitos

- Java 17+
- Maven
- Banco MySQL ou PostgreSQL
- Docker (opcional)

---

### ▶️ Executando com Maven

```bash
git clone https://github.com/davicruz23/api-convenios.git
cd api-convenios

mvn clean install
mvn spring-boot:run
```

A aplicação ficará disponível em:

```
http://localhost:8080
```

---

### 🐳 Executando com Docker

```bash
docker compose up --build
```

Isso irá iniciar a aplicação juntamente com o banco configurado no `docker-compose.yml`.

---

## ⚙️ Configuração

As configurações principais estão em:

```
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/conveniosdb
spring.datasource.username=root
spring.datasource.password=senha

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=chaveSecreta
jwt.expiration=86400000
```

Recomenda-se utilizar variáveis de ambiente para dados sensíveis em ambiente de produção.

---

## 📊 Padrões REST Utilizados

- **GET** → Consulta de dados
- **POST** → Criação
- **PUT** → Atualização
- **DELETE** → Remoção
- Uso adequado de códigos HTTP (200, 201, 400, 401, 404, 500)

---

## 🧠 Boas Práticas Aplicadas

✔️ Separação clara de responsabilidades  
✔️ Uso de DTOs para proteger entidades  
✔️ Tratamento centralizado de exceções  
✔️ Código organizado seguindo princípios SOLID  
✔️ Estrutura preparada para crescimento  

---

## 🔗 Integração

Esta API pode ser integrada com:

- Aplicação Web (React)
- Aplicação Mobile (Flutter)
- Outros serviços REST

---

## 👨‍💻 Autor

**Davi Cruz**  
Desenvolvedor Back-End Java  

GitHub: https://github.com/davicruz23  
Email: davifieledeus@gmail.com
