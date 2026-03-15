package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DTO.AlunoConsultaDtoDAO;
import org.example.projetodiogo.model.DTO.AlunoConsultaDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/detalhesAluno")
public class AdminDetalhesAlunoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idAlunoStr = request.getParameter("idAluno");
        String idTurmaStr = request.getParameter("idTurma");
        String nomeTurma = request.getParameter("nomeTurma");

        AlunoConsultaDtoDAO dao = new AlunoConsultaDtoDAO();
        AlunoConsultaDTO aluno = dao.buscarPorIdAluno(Integer.parseInt(idAlunoStr));

        request.setAttribute("alunoConsulta", aluno);
        request.setAttribute("idTurma", idTurmaStr);
        request.setAttribute("nomeTurma", nomeTurma);

        request.getRequestDispatcher("/WEB-INF/admin/admin-detalhes-aluno.jsp")
                .forward(request, response);
    }
}