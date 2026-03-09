```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <html>

    <head>
        <title>Colégio Valença</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/page-grid.css">
        <link rel="icon" type="image/x-icon"
            href="${pageContext.request.contextPath}/assets/img/icone-colegio-valenca.svg">
        <script>
            function validarSenhas() {
                const senha = document.querySelector('input[name="senha"]').value;
                const confirmarSenha = document.querySelector('input[name="confirmarSenha"]').value;
                
                if (senha !== confirmarSenha) {
                    alert('As senhas não coincidem!');
                    return false;
                }
                return true;
            }
        </script>
    </head>

    <body>

        <jsp:include page="/WEB-INF/views/componentes/sidebarAdm.jsp">
            <jsp:param name="activePage" value="turmas" />
        </jsp:include>

        <div class="page-content">
            <header class="page-grid-header">
                <div class="page-grid-header-state">
                    <a href="${pageContext.request.contextPath}/admin/verTurmas">Turmas</a>
                    <p>></p>
                    <a href="${pageContext.request.contextPath}/admin/verAlunos">Alunos</a>
                    <p>></p>
                    <b>Editar Aluno</b>
                </div>

                <h1 class="page-grid-header-title">
                    Editar dados do aluno
                </h1>
            </header>

            <main class="page-grid-main" style="gap: 5vh; padding-bottom: 80px;">

                <% if(request.getAttribute("erroLogin") !=null){ %>
                    <div>
                        <%= request.getAttribute("erroLogin") %>
                    </div>
                    <% } %>

                        <% if(request.getAttribute("mensagemSucesso") !=null){ %>
                            <div>
                                <%= request.getAttribute("mensagemSucesso") %>
                            </div>
                            <% } %>

                                <form action="${pageContext.request.contextPath}/admin/EditarAluno" method="post" onsubmit="return validarSenhas()">

                                    <div class="form-control">
                                        <h5>Matrícula</h5>
                                        <div class="form-control-action input-primary">
                                            <input type="text" name="matricula" value="${param.matricula}" readonly>
                                        </div>
                                    </div>

                                    <div class="form-control">
                                        <h5>Nome</h5>
                                        <div class="form-control-action input-primary">
                                            <input type="text" name="nome" value="${param.nomeAluno}" required>
                                        </div>
                                    </div>

                                    <div class="form-control">
                                        <h5>Email</h5>
                                        <div class="form-control-action input-primary">
                                            <input type="email" name="email" value="${param.email}" required>
                                        </div>
                                    </div>

                                    <div class="form-control">
                                        <h5>Senha</h5>
                                        <div class="form-control-action input-primary">
                                            <input type="password" name="senha" value="${param.senha}" required>
                                        </div>
                                    </div>

                                    <div class="form-control">
                                        <h5>Confirmar Senha</h5>
                                        <div class="form-control-action input-primary">
                                            <input type="password" name="confirmarSenha" required>
                                        </div>
                                    </div>

                                    <br>

                                    <button type="submit" class="btn btn-primary">Salvar</button>

                                </form>

            </main>
        </div>

    </body>

    </html>
    ```