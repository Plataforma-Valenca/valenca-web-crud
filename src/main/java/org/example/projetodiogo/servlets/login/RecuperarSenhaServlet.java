package org.example.projetodiogo.servlets.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.RecuperacaoSenhaDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Usuario;
import org.example.projetodiogo.util.EmailUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/recuperarSenha")
public class RecuperarSenhaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login/recuperarSenha.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Optional<Usuario> usuario = usuarioDAO.buscarPorEmail(email);

        if (usuario.isPresent()) {

            String token = UUID.randomUUID().toString();
            LocalDateTime expiracao = LocalDateTime.now().plusMinutes(15);

            RecuperacaoSenhaDAO dao = new RecuperacaoSenhaDAO();
            dao.salvaToken(usuario.get().getId(), token, expiracao);

            String link = req.getRequestURL().toString()
                    .replace("recuperarSenha", "novaSenha")
                    + "?token=" + token;

            try {
                EmailUtil.enviarEmail(email,"Recuperação de Senha","Clique no link para redefinir sua senha:\n"
                        + link);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("login.jsp");
    }
}