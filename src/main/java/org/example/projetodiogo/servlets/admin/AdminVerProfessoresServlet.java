package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DTO.BuscarProfessoresDtoDAO;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.model.DTO.ProfessorConsultaDTO;
import org.example.projetodiogo.model.Disciplina;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin/verProfessores")
public class AdminVerProfessoresServlet extends HttpServlet {

    private BuscarProfessoresDtoDAO professoresDAO = new BuscarProfessoresDtoDAO();
    private DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String busca = request.getParameter("busca");

        ArrayList<ProfessorConsultaDTO> professoresList;

        if (busca != null && !busca.isEmpty()) {
            professoresList = professoresDAO.buscarProfessoresFiltro(busca);
        } else {
            professoresList = professoresDAO.buscarProfessores();
        }

        ArrayList<Disciplina> disciplinas = disciplinaDAO.visualizarDisciplinas();

        request.setAttribute("professoresList", professoresList);
        request.setAttribute("disciplinas", disciplinas);
        request.setAttribute("busca", busca);

        request.getRequestDispatcher("/WEB-INF/admin/listarProfessores.jsp")
                .forward(request, response);
    }
}