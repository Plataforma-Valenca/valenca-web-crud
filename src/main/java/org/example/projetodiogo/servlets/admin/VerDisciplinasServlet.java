package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.BoletimDAO;
import org.example.projetodiogo.dao.DTO.AlunoConsultaDtoDAO;
import org.example.projetodiogo.dao.DTO.DisciplinasResumoDtoDAO;
import org.example.projetodiogo.dao.TurmasDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Aluno;
import org.example.projetodiogo.model.Boletim;
import org.example.projetodiogo.model.DTO.AlunoConsultaDTO;
import org.example.projetodiogo.model.DTO.DisciplinasResumoDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/admin/verDisciplinasResumo")
public class VerDisciplinasServlet extends HttpServlet {
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
