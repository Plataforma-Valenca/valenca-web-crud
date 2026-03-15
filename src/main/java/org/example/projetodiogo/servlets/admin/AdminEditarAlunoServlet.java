package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/admin/editarAluno")
public class AdminEditarAlunoServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matricula = req.getParameter("matricula");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String confirmarSenha = req.getParameter("confirmarSenha");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (!senha.equals(confirmarSenha)) {
            req.setAttribute("erroLogin", "As senhas não coincidem.");
            req.getRequestDispatcher("/WEB-INF/admin/editarAluno.jsp")
                    .forward(req, resp);
        } else {
            Optional<Usuario> usuarioOpt = usuarioDAO.buscarPorCpfOuMatricula(matricula);
            if (usuarioOpt.isPresent()) {
                Usuario usuario = new Usuario(usuarioOpt.get().getId(), nome, email, senha, true);
                if (usuarioDAO.atualizar(usuario)) {
                    req.setAttribute("mensagemSucesso", "Dados atualizados com sucesso.");
                    req.getRequestDispatcher("/WEB-INF/admin/admin-ver-aluno-ver-disciplinas.jsp")
                    .forward(req, resp);
                }
            }
        }
    }
}
    