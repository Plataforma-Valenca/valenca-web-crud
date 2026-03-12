# valenca-web-crud

## 📋 Descrição

Sistema **CRUD** (Create, Read, Update, Delete) desenvolvido como **plataforma de ensino** do **Colégio Valença**. A aplicação gerencia dados educacionais de forma segura e eficiente, permitindo que diferentes usuários (alunos, professores, administradores) acessem suas respectivas áreas.

## 🎯 Objetivo

Criar uma solução web que integre diferentes módulos de gerenciamento educacional, demonstrando conceitos essenciais de desenvolvimento backend e frontend com padrões de arquitetura profissionais.

## ✨ Funcionalidades

- ✅ **Área de Aluno** - Visualizar notas, frequência, atividades e comunicados
- ✅ **Área de Professor** - Gerenciar turmas, lançar notas e frequência
- ✅ **Área de Administrador** - Administrar dados de alunos, professores e disciplinas

## 🛠️ Tecnologias Utilizadas

### Frontend
- **HTML5** - Estrutura semântica
- **CSS3** - Estilização responsiva
- **JavaScript** - Interatividade e validação cliente

### Backend
- **Java** - Linguagem de programação principal
- **Servlets** - Controladores HTTP e processamento de requisições
- **JSP** (JavaServer Pages) - Templates dinâmicos para renderização de views

### Persistência de Dados
- **DAO** (Data Access Object) - Padrão de acesso aos dados
- **JDBC** - Comunicação com banco de dados
- **SQL** - Operações no banco de dados relacional

### Servidor
- **Apache Tomcat** - Servidor de aplicação Java
- **Banco de Dados**: PostgreSQL

## 📐 Arquitetura

### Padrão MVC (Model-View-Controller)

```
┌─────────────┐
│   Cliente   │ (Browser - HTML/CSS/JS)
└──────┬──────┘
       │
       ↓
┌─────────────────────────────┐
│    Servlets (Controllers)   │ (Processam requisições HTTP)
└──────┬──────────────────────┘
       │
       ↓
┌─────────────────────────────┐
│  DAO (Data Access Object)   │ (Gerenciam acesso aos dados)
└──────┬──────────────────────┘
       │
       ↓
┌─────────────────────────────┐
│    Banco de Dados SQL       │ (Persistência)
└─────────────────────────────┘
```

### Componentes Principais

#### **Servlets**
Controladores HTTP que tratam requisições GET/POST do cliente:
- Recebem parâmetros da requisição
- Chamam métodos do DAO
- Redirecionam para JSPs apropriadas
- Gerenciam sessões e segurança

#### **JSP (JavaServer Pages)**
Templates dinâmicos que renderizam a interface:
- Exibem dados retornados pelos Servlets
- Contêm formulários para entrada de dados
- Integram HTML com lógica Java
- Geram HTML final enviado ao navegador

#### **DAO (Data Access Object)**
Padrão de projeto para isolamento de acesso aos dados:
- Implementam operações CRUD
- Executam queries SQL
- Convertem resultados em objetos Java
- Abstraem detalhes do banco de dados

## 🚀 Como Executar

### Pré-requisitos
- Java JDK 8+ instalado
- Apache Tomcat 9+
- PostgreSQL configurado
- IDE (Eclipse, IntelliJ ou VS Code com extensões)

### Passo a Passo

1. **Clone o repositório**
   ```bash
   git clone https://github.com/Plataforma-Valenca/valenca-web-crud.git
   cd valenca-web-crud
   ```

## 👨‍💻 Desenvolvedores

Projeto desenvolvido por alunos do 2ºF Tech 2026.