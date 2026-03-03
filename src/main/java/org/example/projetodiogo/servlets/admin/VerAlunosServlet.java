package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AlunoConsultaDtoDAO;
import org.example.projetodiogo.dao.TurmasDAO;
import org.example.projetodiogo.model.AlunoConsultaDTO;
import org.example.projetodiogo.model.Turma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/verAlunos")
public class VerAlunosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AlunoConsultaDtoDAO alunoConsultaDao = new AlunoConsultaDtoDAO();
        TurmasDAO turmaDAO = new TurmasDAO();

        List<AlunoConsultaDTO> alunosList = new ArrayList<>();
        List<Turma> turmas = new ArrayList<>();

        String busca = req.getParameter("busca");

        try {
            if (busca != null && !busca.isEmpty()) {
                AlunoConsultaDTO aluno = alunoConsultaDao.buscarPorMatricula(busca);
                if (aluno != null) {
                    alunosList.add(aluno);
                }
            } else {
                alunosList = alunoConsultaDao.buscarAlunos();
            }

            turmas = turmaDAO.buscarTurmas();

            req.setAttribute("alunosList", alunosList);
            req.setAttribute("turmas", turmas);
            req.getRequestDispatcher("/WEB-INF/admin/listarAlunos.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("erro.jsp");
        }
    }
}