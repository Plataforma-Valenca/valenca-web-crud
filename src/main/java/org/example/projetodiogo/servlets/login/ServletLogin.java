package org.example.projetodiogo.servlets.login;

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

@WebServlet ("/login")
public class ServletLogin {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("loginUsuario");
        String senha = req.getParameter("senhaUsuario");
        Optional<Usuario> usuario = null;

        if (login == null || login.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("erroLogin", "Login e senha são obrigatórios");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            usuario = usuarioDAO.validarLogin(login, senha);
        }

        if (usuario.isPresent() && HasherSenha.verificaSenha(senha, usuario.get().getSenha())
                && usuario.get().getTipoUsuario().equalsIgnoreCase("professor")) {
            HttpSession session = req.getSession();
            session.setAttribute("usuarioLogado", usuario);
            session.setAttribute("usuarioId", usuario.get().getId());
            session.setAttribute("usuarioNome", usuario.get().getNome());
            session.setAttribute("usuarioEmail", usuario.get().getEmail());
            session.setAttribute("username", usuario.get().getUsername());

            req.getRequestDispatcher("/WEB-INF/aluno/homeProfessor.jsp")
                    .forward(req, resp);
        } else if (usuario.isPresent() && HasherSenha.verificaSenha(senha, usuario.get().getSenha())
                && usuario.get().getTipoUsuario().equalsIgnoreCase("aluno")) {
            HttpSession session = req.getSession();
            session.setAttribute("usuarioLogado", usuario);
            session.setAttribute("usuarioId", usuario.get().getId());
            session.setAttribute("usuarioNome", usuario.get().getNome());
            session.setAttribute("usuarioEmail", usuario.get().getEmail());

            req.getRequestDispatcher("/WEB-INF/aluno/homeAluno.jsp")
                    .forward(req, resp);
        } else if (usuario.isPresent() && HasherSenha.verificaSenha(senha, usuario.get().getSenha())
                && usuario.get().getTipoUsuario().equalsIgnoreCase("administrador")) {
            HttpSession session = req.getSession();
            session.setAttribute("usuarioLogado", usuario);
            session.setAttribute("usuarioId", usuario.get().getId());
            session.setAttribute("usuarioNome", usuario.get().getNome());
            session.setAttribute("usuarioEmail", usuario.get().getEmail());

            req.getRequestDispatcher("/WEB-INF/aluno/homeAdmin.jsp")
                    .forward(req, resp);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            req.setAttribute("error", "Email ou senha inválidos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }
}
