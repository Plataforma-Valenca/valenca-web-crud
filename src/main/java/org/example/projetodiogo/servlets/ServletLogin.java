package org.example.projetodiogo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Usuario;
import org.example.projetodiogo.util.HasherSenha;

import java.io.IOException;
import java.util.Optional;

@WebServlet (value = "/loginIdentificacao")
public class ServletLogin {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("loginUsuario");
        String senha = req.getParameter("senhaUsuario");

        if (login == null || login.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("erroLogin", "Email e senha são obrigatórios");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

        Optional<Usuario> usuario = usuarioDAO.buscarPorEmailOuNomeUsuario(login, senha);

        if (usuario.isPresent() && HasherSenha.verificaSenha(senha, usuario.get().getSenha())) {
            HttpSession session = req.getSession();
            session.setAttribute("usuarioLoggedIn", usuario);
            session.setAttribute("usuarioId", usuario.get().getId());
            session.setAttribute("usuarioNome", usuario.get().getNome());
            session.setAttribute("usuarioEmail", usuario.get().getEmail());

//            if (alunoDAO.existePorUsuario(usuario.get().getId())) {
//                resp.sendRedirect( req.getContextPath()+"/homeAluno");
//            } else if (professorDAO.existePorUsuario(usuario.get().getId())) {
//                resp.sendRedirect( req.getContextPath()+"/homeProfessor");
//            }
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            req.setAttribute("error", "Email ou senha inválidos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }
}
