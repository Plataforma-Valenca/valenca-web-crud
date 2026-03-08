package org.example.projetodiogo.servlets.login;

import com.sun.net.httpserver.HttpsServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Usuario;
import org.example.projetodiogo.util.HasherSenha;

import java.io.IOException;
import java.util.Optional;

@WebServlet ("/login")
public class ServletLogin extends HttpServlet {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("loginUsuario");
        String senha = req.getParameter("senhaUsuario");
        Optional<Usuario> usuarioOpt = Optional.empty();
        Usuario usuario = new Usuario();

        if (login == null || login.trim().isEmpty() ||
                senha == null || senha.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("erroLogin", "Login e senha são obrigatórios");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            usuarioOpt = usuarioDAO.validarLogin(login);
        }


        if (usuarioOpt.isEmpty()) {
            req.setAttribute("erroLogin", "Login ou senha inválidos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        } else {
            usuario = usuarioOpt.get();
            req.getSession().setAttribute("usuario", usuario);
        }

        if (!HasherSenha.verificaSenha(senha, usuario.getSenha())) {
            req.setAttribute("erroLogin", "Email ou senha inválidos");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }

        switch (usuario.getTipoUsuario().toLowerCase()) {
            case "professor":
                HttpSession session = req.getSession();
                session.setAttribute("usuarioLogado", usuario);
                resp.sendRedirect(req.getContextPath() + "/professor/home");
                break;

            case "aluno":
                session = req.getSession();
                session.setAttribute("usuarioLogado", usuario);
                resp.sendRedirect(req.getContextPath() + "/aluno/homeAluno");
                break;

            case "admin":
                session = req.getSession();
                session.setAttribute("usuarioLogado", usuario);
                resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
                break;
        }
    }
}
