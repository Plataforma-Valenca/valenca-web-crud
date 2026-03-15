package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DTO.DisciplinasResumoDtoDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.DTO.DisciplinasResumoDTO;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/admin/verDisciplinas")
public class AdminVerDisciplinasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DisciplinasResumoDtoDAO disciplinasResumoDtoDAO = new DisciplinasResumoDtoDAO();
        ArrayList<DisciplinasResumoDTO> resumoList;

        try {
            resumoList = disciplinasResumoDtoDAO.visualizarDisciplinasResumo();

            req.setAttribute("resumoList", resumoList);
            System.out.println(resumoList.size());

            req.getRequestDispatcher("/WEB-INF/admin/listarDisciplinas.jsp")
                    .forward(req, resp);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
