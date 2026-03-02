package org.example.projetodiogo.servlets.admin;

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

@WebServlet("/admin/verAlunos")
public class VerAlunosServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AlunoConsultaDtoDAO alunoConsultaDao = new AlunoConsultaDtoDAO();
        ArrayList<AlunoConsultaDTO> alunosList = new ArrayList<>();
        AlunoConsultaDTO aluno;

        String busca = req.getParameter("busca");

        try {
            if (busca != null && !busca.isEmpty()) {
                aluno = alunoConsultaDao.buscarPorMatricula(busca);
                if (aluno != null) {
                    alunosList.add(aluno);
                }
            } else {
                alunosList = alunoConsultaDao.buscarAlunos();
            }

            req.setAttribute("alunosList", alunosList);
            req.setAttribute("busca", busca);

            req.getRequestDispatcher("/WEB-INF/admin/listarAlunos.jsp")
                .forward(req, resp);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}