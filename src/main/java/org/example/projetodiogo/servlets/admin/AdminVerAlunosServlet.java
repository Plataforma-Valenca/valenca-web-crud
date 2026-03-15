package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DTO.AlunoConsultaDtoDAO;
import org.example.projetodiogo.dao.TurmasDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.DTO.AlunoConsultaDTO;
import org.example.projetodiogo.model.Turma;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/verAlunos")
public class AdminVerAlunosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AlunoConsultaDtoDAO alunoConsultaDao = new AlunoConsultaDtoDAO();
        TurmasDAO turmaDAO = new TurmasDAO();
        ArrayList<AlunoConsultaDTO> alunosList = new ArrayList<>();

        String busca = req.getParameter("busca");
        String idTurmaStr = req.getParameter("idTurma");
        String nomeTurma = req.getParameter("nomeTurma");
        int idTurma = Integer.parseInt(idTurmaStr);

        try {
            if (busca != null && !busca.isEmpty()) {
                Long matricula = Long.parseLong(busca);
                AlunoConsultaDTO aluno = alunoConsultaDao.buscarPorMatricula(matricula);
                if (aluno != null) {
                    alunosList.add(aluno);
                }
            } else {
                alunosList = (ArrayList<AlunoConsultaDTO>) alunoConsultaDao.buscarAlunosPorTurma(idTurma);
            }

            req.setAttribute("idTurma", idTurmaStr);
            req.setAttribute("nomeTurma", nomeTurma);
            req.setAttribute("alunos", alunosList);

            req.getRequestDispatcher("/WEB-INF/admin/admin-ver-alunos.jsp")
                    .forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}