package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DisciplinaDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/editarDisciplina")
public class AdminEditarDisciplinaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String idDisciplinaStr = req.getParameter("idDisciplina");
        String nomeProfessor = req.getParameter("nomeProfessor");

        if (nomeProfessor == null || nomeProfessor.trim().isEmpty()) {
            req.getSession().setAttribute("mensagemErro", "Informe o nome do professor.");
            res.sendRedirect(req.getContextPath() + "/admin/verDisciplinas");
            return;
        }

        try {
            int idDisciplina = Integer.parseInt(idDisciplinaStr);
            DisciplinaDAO dao = new DisciplinaDAO();
            dao.atualizarProfessorPorNome(idDisciplina, nomeProfessor);
            req.getSession().setAttribute("mensagemSucesso", "Disciplina atualizada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getMessage().contains("já vinculado")) {
                req.getSession().setAttribute("mensagemErro", "Este professor já possui uma disciplina.");
            } else {
                req.getSession().setAttribute("mensagemErro", "Professor não encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("mensagemErro", "Erro ao atualizar disciplina.");
        }

        res.sendRedirect(req.getContextPath() + "/admin/verDisciplinas");
    }
}