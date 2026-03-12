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
public class VerAlunosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AlunoConsultaDtoDAO alunoConsultaDao = new AlunoConsultaDtoDAO();
        TurmasDAO turmaDAO = new TurmasDAO();
        ArrayList<AlunoConsultaDTO> alunosList = new ArrayList<>();
        AlunoConsultaDTO aluno;

        String busca = req.getParameter("busca");

        try {
            if (busca != null && !busca.isEmpty()) {

                Long matricula = Long.parseLong(busca);

                aluno = alunoConsultaDao.buscarPorMatricula(matricula, 1);

                if (aluno != null) {
                    alunosList.add(aluno);
                }

            } else {
                alunosList = alunoConsultaDao.buscarAlunos();
            }

            List<Turma> turmas = turmaDAO.buscarTurmas();

            req.setAttribute("alunosList", alunosList);
            req.setAttribute("turmasList", turmas);
            req.setAttribute("busca", busca);

            req.getRequestDispatcher("/WEB-INF/admin/listarAlunos.jsp")
                .forward(req, resp);
        } catch (DataAccessException | SQLException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}