package org.example.projetodiogo.servlets.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Aluno;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/validarPreCadastro")
public class ValidarPreCadastro {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matriculaOuCpf = req.getParameter("inputValidacao");
        Usuario usuario = null;
        Optional<Aluno> aluno = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        AlunoDAO alunoDAO = new AlunoDAO();


        if (matriculaOuCpf == null || matriculaOuCpf.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("erro", "Campo obrigatório");
            req.getRequestDispatcher("/WEB-INF/aluno/validarPreCadastro.jsp")
                    .forward(req, resp);
        } else {
            usuario = usuarioDAO.validarPrimeiroAcesso(matriculaOuCpf);
            if (usuario == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                req.setAttribute("erro", "Cadastro já realizado ou acesso inválido! Contate a escola.");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                aluno = alunoDAO.buscarPorIdUsuario(usuario.getId());
                req.setAttribute("matricula", aluno.get().getMatricula());
                req.getRequestDispatcher("/WEB-INF/aluno/finalizarCadastroAluno.jsp")
                        .forward(req, resp);
            }
        }
    }
}
