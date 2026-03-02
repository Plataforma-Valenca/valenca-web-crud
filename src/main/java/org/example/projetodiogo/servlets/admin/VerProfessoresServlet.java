package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.ProfessorDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/verProfessores")
public class VerProfessoresServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        ProfessorDAO professorDAO = new ProfessorDAO();
        List<Usuario> listaProfessores;

        String busca = req.getParameter("busca");

        try {
            if (busca != null && !busca.isEmpty()) {
                listaProfessores = professorDAO.buscarPorNomeOuEmail(busca);
            } else {
                listaProfessores = professorDAO.buscarTodosComUsuario();
            }

            req.setAttribute("listaProfessores", listaProfessores);
            req.setAttribute("busca", busca);

            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("/WEB-INF/admin/listarProfessores.jsp");

            dispatcher.forward(req, resp);

        } catch (DataAccessException e) {
            throw new RuntimeException("Erro ao buscar professores", e);
        }
    }
}