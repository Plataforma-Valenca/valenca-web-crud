package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.BoletimDAO;
import org.example.projetodiogo.dao.DTO.AlunoConsultaDtoDAO;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.model.Boletim;
import org.example.projetodiogo.model.DTO.AlunoConsultaDTO;
import org.example.projetodiogo.model.Disciplina;

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
        int idAluno = Integer.parseInt(idAlunoStr);

        AlunoConsultaDtoDAO dao = new AlunoConsultaDtoDAO();
        BoletimDAO boletimDAO = new BoletimDAO();

        AlunoConsultaDTO aluno = dao.buscarPorIdAluno(idAluno);
        ArrayList<Boletim> boletimList = boletimDAO.visualizarBoletim(idAluno);

        request.setAttribute("alunoConsulta", aluno);
        request.setAttribute("idTurma", idTurmaStr);
        request.setAttribute("nomeTurma", nomeTurma);
        request.setAttribute("boletimList", boletimList);

        request.getRequestDispatcher("/WEB-INF/admin/admin-detalhes-aluno.jsp")
                .forward(request, response);
    }
}