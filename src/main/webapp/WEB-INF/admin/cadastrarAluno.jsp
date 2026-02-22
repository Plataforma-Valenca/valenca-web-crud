<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cadastrar Aluno</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebar.jsp">
    <jsp:param name="activePage" value="listarAluno"/>
</jsp:include>

<div class="main-content">

    <h1>Cadastrar Aluno</h1>

    <form method="post"
          action="${pageContext.request.contextPath}/alunos">

        <input type="hidden" name="acao" value="salvar">

        <label>Nome:</label>
        <input type="text" name="nome" required>

        <label>CPF:</label>
        <input type="text" name="cpf" required>

        <label>Matrícula:</label>
        <input type="text" name="matricula" required>

        <label>Turma:</label>
        <input type="text" name="turma" required>

        <br><br>

        <button type="submit">Salvar</button>

    </form>

</div>

</body>
</html>