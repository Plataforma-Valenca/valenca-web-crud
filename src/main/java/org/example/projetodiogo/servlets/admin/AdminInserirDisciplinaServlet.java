package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DisciplinaDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/inserirDisciplina")
public class AdminInserirDisciplinaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String nome = req.getParameter("nome");
        String professor = req.getParameter("professor");

        try {
            DisciplinaDAO dao = new DisciplinaDAO();
            dao.inserir(nome, professor);
            req.getSession().setAttribute("mensagemSucesso", "Disciplina cadastrada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getMessage().contains("já vinculado")) {
                req.getSession().setAttribute("mensagemErro", "Este professor já possui uma disciplina.");
            } else {
                req.getSession().setAttribute("mensagemErro", "Professor não cadastrado em usuários.");
            }
        }

        resp.sendRedirect(req.getContextPath() + "/admin/verDisciplinas");
    }
}

