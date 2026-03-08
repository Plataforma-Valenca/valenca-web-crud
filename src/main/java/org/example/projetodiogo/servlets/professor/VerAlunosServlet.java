package org.example.projetodiogo.servlets.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DTO.AlunoConsultaDtoDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.DTO.AlunoConsultaDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/professor/verAlunos")
public class VerAlunosServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AlunoConsultaDtoDAO alunoConsultaDao = new AlunoConsultaDtoDAO();
        List<AlunoConsultaDTO> alunosList = new ArrayList<>();

        String busca = req.getParameter("busca");
        String idTurmaStr = req.getParameter("idTurma");

        try {

            if (busca != null && !busca.isEmpty()) {

                Long matricula = Long.parseLong(busca);

                AlunoConsultaDTO aluno = alunoConsultaDao.buscarPorMatricula(matricula);

                if (aluno != null) {
                    alunosList.add(aluno);
                }

            } else if (idTurmaStr != null && !idTurmaStr.isEmpty()) {

                int idTurma = Integer.parseInt(idTurmaStr);
                alunosList = alunoConsultaDao.buscarAlunosPorTurma(idTurma);

            } else {

                alunosList = alunoConsultaDao.buscarAlunos();

            }

            req.setAttribute("alunosList", alunosList);
            req.setAttribute("busca", busca);

            req.getRequestDispatcher("/WEB-INF/professor/listarAlunos.jsp")
                    .forward(req, resp);

        } catch (DataAccessException e) {
            throw new ServletException(e);
        }
    }
}