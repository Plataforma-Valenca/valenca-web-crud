package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/inserirProfessor")
public class AdminInserirProfessorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/admin/inserirProfessor.jsp")
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();

        usuario.setNome(req.getParameter("nome"));
        usuario.setEmail(req.getParameter("email"));
        usuario.setSenha(req.getParameter("senha"));
        usuario.setUsername(req.getParameter("username"));
        usuario.setCpf(req.getParameter("cpf"));

        try {
            usuarioDAO.inserirProfessor(usuario);
            req.getSession().setAttribute("mensagemSucesso", "Professor cadastrado com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            req.getSession().setAttribute("mensagemErro", "Erro ao cadastrar professor.");
        }

        resp.sendRedirect(req.getContextPath() + "/admin/verProfessores");
    }
}