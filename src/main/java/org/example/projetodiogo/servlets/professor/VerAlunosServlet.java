package org.example.projetodiogo.servlets.professor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AlunoConsultaDtoDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.AlunoConsultaDTO;

import java.io.IOException;
import java.lang.management.OperatingSystemMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/professor/verAlunos")
public class VerAlunosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AlunoConsultaDtoDAO alunoConsultaDao = new AlunoConsultaDtoDAO();
        List<AlunoConsultaDTO> alunosList = new ArrayList<>();
        AlunoConsultaDTO aluno;

        String busca = req.getParameter("busca");

        try {
            if (busca != null && !busca.isEmpty()) {
                aluno = alunoConsultaDao.buscarPorMatricula(busca);
                if (aluno != null) {
                    req.setAttribute("alunosBusca", aluno);
                }
            } else {
                alunosList = alunoConsultaDao.buscarAlunos();
            }

            req.setAttribute("alunosList", alunosList);
            req.setAttribute("busca", busca);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/professor/listarAlunos.jsp");
            dispatcher.forward(req, resp);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}