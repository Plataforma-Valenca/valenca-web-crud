package org.example.projetodiogo.servlets.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.RecuperacaoSenhaDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/novaSenha")
public class NovaSenhaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = req.getParameter("token");

        RecuperacaoSenhaDAO dao = new RecuperacaoSenhaDAO();
        int idUsuario = dao.validaToken(token);

        if (idUsuario == -1) {
            req.setAttribute("mensagemErro", "Token inválido ou expirado.");
            return;
        }

        req.setAttribute("token", token);
        req.getRequestDispatcher("/WEB-INF/login/login-nova-senha.jsp")
                .forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        String token = req.getParameter("token");
        String novaSenha = req.getParameter("senha");

        RecuperacaoSenhaDAO dao = new RecuperacaoSenhaDAO();
        int idUsuario = dao.validaToken(token);

        if (idUsuario == -1) {
            resp.getWriter().println("Token inválido.");
            return;
        }

        String hash = BCrypt.hashpw(novaSenha, BCrypt.gensalt());

        usuarioDAO.atualizarSenha(idUsuario, hash);

        dao.marcarComoUsado(token);

        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}