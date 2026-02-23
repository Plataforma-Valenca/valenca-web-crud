<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cadastrar Disciplina</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/style.css">
</head>

<body>

<jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
    <jsp:param name="activePage" value="listarDisciplina"/>
</jsp:include>

<div class="main-content">

    <h1>Cadastrar Disciplina</h1>

    <form method="post"
          action="${pageContext.request.contextPath}/cadastrarDisciplina">
        <input type="text" name="nome" placeholder="Digite o nome" required>
        <input type="text" name="nome"  placeholder="Digite o professor atribuído">


        <br><br>

        <button type="submit">Cadastrar</button>

    </form>

</div>

</body>
</html>