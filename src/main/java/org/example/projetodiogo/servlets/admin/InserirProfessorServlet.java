package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.TurmasDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/inserirProfessor")
public class InserirProfessorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/inserirAluno.jsp")
                .forward(req, resp);;
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

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
            usuario.setCpf(cpf);
            usuario.setUsername(username);

            usuarioDAO.inserirProfessorComDisciplina(usuario, disciplina);


            req.getSession().setAttribute("mensagemSucesso", "Professor cadastrado com sucesso!");
            resp.sendRedirect(req.getContextPath() + "/admin/verProfessores");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
