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

@WebServlet("/FinalizarCadastroAluno")
public class FinalizarCadastroAluno extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String cpfOuMatricula = (String) session.getAttribute("cpfOuMatriculaValidacao");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String confirmarSenha = req.getParameter("confirmarSenha");
        Usuario usuario = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int id = 0;

        if (!senha.equals(confirmarSenha)) {
            req.setAttribute("erroLogin", "As senhas não coincidem.");
            req.getRequestDispatcher("/WEB-INF/aluno/finalizarCadastroAluno.jsp")
                    .forward(req, resp);
        } else {
            Optional<Usuario> usuarioOpt = usuarioDAO.buscarPorCpfOuMatricula(cpfOuMatricula);
            if (usuarioOpt.isPresent()) {
                usuario = new Usuario(usuarioOpt.get().getId(), nome, email, senha, true);
                if (usuarioDAO.atualizarPreCadastro(usuario)) {
                    req.setAttribute("mensagemSucesso", "Cadastro finalizado com sucesso.");
                    req.getRequestDispatcher("index.jsp")
                            .forward(req, resp);
                }
            }
        }
    }
}
