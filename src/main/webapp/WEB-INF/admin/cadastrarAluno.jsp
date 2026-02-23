<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cadastrar Aluno</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="listarAluno"/>
</jsp:include>

<div class="main-content">

    <h1>Cadastrar Aluno</h1>

    <form method="post"
          action="${pageContext.request.contextPath}/cadastrarAluno">
        <label>CPF:</label>
        <input type="text" name="cpf" required>
        <br><br>

        <button type="submit">Salvar</button>

    </form>

</div>

</body>
</html>