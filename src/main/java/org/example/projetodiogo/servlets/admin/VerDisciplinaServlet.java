package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Disciplina;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/verDisciplinas")
public class VerDisciplinaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        List<Disciplina> disciplinasList = new ArrayList<>();

        String busca = req.getParameter("busca");

        try {

            // 🔍 BUSCA
            if (busca != null && !busca.isEmpty()) {


                List<Disciplina> disciplinas = disciplinaDAO.buscarPorNome(busca);
                disciplinasList.addAll(disciplinas);

            } else {
                // 📋 LISTAR TODAS
                disciplinasList = disciplinaDAO.visualizarDisciplinas();
            }

            req.setAttribute("disciplinasList", disciplinasList);
            req.setAttribute("busca", busca);

            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("/WEB-INF/admin/listarDisciplinas.jsp");

            dispatcher.forward(req, resp);

        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao buscar disciplinas", e);
        }
    }
}