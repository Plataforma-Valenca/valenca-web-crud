package org.example.projetodiogo.servlets.aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/aluno/EditarAluno")
public class EditarAlunoServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String confirmarSenha = req.getParameter("confirmarSenha");
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if (!senha.equals(confirmarSenha)) {
            req.setAttribute("erroLogin", "As senhas não coincidem.");
            req.getRequestDispatcher("/WEB-INF/aluno/editarAluno.jsp")
                    .forward(req, resp);
        } else {
            Optional<Usuario> usuarioOpt = usuarioDAO.buscarPorCpfOuMatricula(usuario.getCpf());
            if (usuarioOpt.isPresent()) {
                usuario = new Usuario(usuarioOpt.get().getId(), nome, email, senha, true);
                if (usuarioDAO.atualizar(usuario)) {
                    req.setAttribute("mensagemSucesso", "Dados atualizados com sucesso.");
                    req.getRequestDispatcher("/WEB-INF/aluno/aluno-home.jsp")
                        .forward(req, resp);
                }
            }
        }
    }
    
}
