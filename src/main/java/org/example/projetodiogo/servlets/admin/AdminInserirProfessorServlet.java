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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();

        String nomeCompleto = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String username = req.getParameter("username");
        String cpf = req.getParameter("cpf");
        String disciplina = req.getParameter("disciplina");

        try {

            usuario.setNome(nomeCompleto);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setUsername(username);
            usuario.setCpf(cpf);

            usuarioDAO.inserirProfessorComDisciplina(usuario, disciplina);

            req.getSession().setAttribute("mensagemSucesso", "Professor cadastrado com sucesso!");

        } catch (SQLException e) {

            e.printStackTrace();
            req.getSession().setAttribute("mensagemErro", "Erro ao cadastrar professor.");

        }

        resp.sendRedirect(req.getContextPath() + "/admin/verProfessores");
    }
}