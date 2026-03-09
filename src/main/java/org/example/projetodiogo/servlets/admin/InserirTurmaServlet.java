package org.example.projetodiogo.servlets.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.TurmasDAO;

@WebServlet("/admin/inserirTurma")
public class InserirTurmaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        int ano = Integer.parseInt(request.getParameter("ano"));

        TurmasDAO turmaDAO = new TurmasDAO();

        turmaDAO.inserirTurma(nome, ano);

        response.sendRedirect(request.getContextPath() + "/admin/verTurmas");
    }
}