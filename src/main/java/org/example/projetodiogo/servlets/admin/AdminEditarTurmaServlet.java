package org.example.projetodiogo.servlets.admin;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.example.projetodiogo.dao.TurmasDAO;

import java.io.IOException;

@WebServlet("/admin/editarTurma")
public class AdminEditarTurmaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int ano = Integer.parseInt(request.getParameter("ano"));
        String nome = request.getParameter("nome");

        TurmasDAO dao = new TurmasDAO();

        dao.editarTurma(id, nome, ano);

        request.getSession().setAttribute("mensagemSucesso", "Turma atualizada com sucesso!");

        response.sendRedirect(request.getContextPath()+"/admin/verTurmas");
    }
}
