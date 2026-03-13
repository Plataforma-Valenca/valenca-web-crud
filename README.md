# valenca-web-crud

Sistema web desenvolvido para gerenciamento acadêmico do Colégio Valença, permitindo a administração de alunos, professores, disciplinas e notas através de diferentes perfis de acesso.

## 👥 Tipos de Usuários

- 👨‍🎓 **Aluno** - Acesso às informações acadêmicas e desempenho
- 👨‍🏫 **Professor** - Gestão de notas e acompanhamento de turmas
- 🛠 **Administrador** - Controle total do sistema

---

## 🚀 Funcionalidades

### 👨‍🎓 Área do Aluno

O aluno pode acompanhar suas informações acadêmicas e desempenho escolar.

#### 🏠 Home
- Visualização das informações gerais
- **Dados exibidos:**
  - Nome
  - Turma
  - Número de matrícula
  - Email acadêmico

#### 📚 Disciplinas
- Lista de disciplinas em que o aluno está matriculado
- **Informações por disciplina:**
  - Nome da disciplina
  - Professor responsável
  - Acesso aos detalhes

#### 📊 Boletim
- Acompanhamento do desempenho escolar
- **Notas e médias:**
  - Notas N1 e N2
  - Média do 1º semestre
  - Notas do 2º semestre
  - Média final
  - Situação (Aprovado / Reprovado)

### 👨‍🏫 Área do Professor

O professor possui acesso às disciplinas que leciona e pode gerenciar as notas dos alunos.

**Funcionalidades principais:**
- Visualizar disciplinas atribuídas
- Listar alunos por disciplina
- Lançar e editar notas
- Acompanhar médias dos alunos

### 🛠 Área do Administrador

O administrador possui controle total sobre o sistema.

**Funcionalidades:**
- Cadastro de alunos
- Cadastro de professores
- Cadastro de disciplinas
- Gerenciamento de turmas
- Associação de professores às disciplinas

---

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

---

## ⚙️ Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/valenca-web-crud.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd valenca-web-crud
   ```
3. Instale as dependências:
   ```bash
   npm install
   ```
4. Configure o banco de dados no arquivo `.env`.
5. Inicie o servidor:
   ```bash
   npm start
   ```

---

## 👨‍💻 Desenvolvedores
 
Projeto desenvolvido por alunos do 2ºF Tech 2026.