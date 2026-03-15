package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DisciplinaDAO;

import java.io.IOException;

@WebServlet("/admin/deletarDisciplina")
public class AdminDeletarDisciplinaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String idString = req.getParameter("idDisciplina");

        if (idString != null && !idString.isEmpty()) {
            try {
                int id = Integer.parseInt(idString);
                DisciplinaDAO dao = new DisciplinaDAO();
                dao.delete(id);

                req.getSession().setAttribute("mensagemSucesso", "Disciplina deletada com sucesso!");

            } catch (NumberFormatException e) {
                e.printStackTrace();
                req.getSession().setAttribute("mensagemErro", "Erro ao deletar disciplina.");
            }
        }

        res.sendRedirect(req.getContextPath() + "/admin/verDisciplinas");
    }
}
