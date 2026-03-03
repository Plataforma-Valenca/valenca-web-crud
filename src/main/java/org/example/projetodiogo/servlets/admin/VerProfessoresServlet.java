package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DTO.BuscarProfessoresDtoDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.DTO.ProfessorConsultaDTO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin/verProfessores")
public class VerProfessoresServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BuscarProfessoresDtoDAO buscarProfessoresDtoDAO = new BuscarProfessoresDtoDAO();
        ArrayList<ProfessorConsultaDTO> professoresList = new ArrayList<>();
        ProfessorConsultaDTO professor;

        String busca = req.getParameter("busca");

        try {
            if (busca != null && !busca.isEmpty()) {
                professoresList = buscarProfessoresDtoDAO.buscarProfessoresFiltro(busca);
            } else {
                professoresList = buscarProfessoresDtoDAO.buscarProfessores();
            }

            req.setAttribute("professoresList", professoresList);
            req.setAttribute("busca", busca);

            req.getRequestDispatcher("/WEB-INF/admin/listarProfessores.jsp")
                    .forward(req, resp);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
