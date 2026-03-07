package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.*;
import org.example.projetodiogo.dao.DTO.AlunoConsultaDtoDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.*;
import org.example.projetodiogo.model.DTO.AlunoConsultaDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@WebServlet("/admin/verPerfilAluno")
public class VerPerfilAluno extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        AlunoConsultaDtoDAO alunoConsultaDAO = new AlunoConsultaDtoDAO();
        BoletimDAO boletimDAO = new BoletimDAO();
        TurmasDAO turmasDAO = new TurmasDAO();

        try {

            String cpf = req.getParameter("cpf");

            AlunoConsultaDTO alunoConsulta =
                    alunoConsultaDAO.buscarPorMatricula(cpf);

            int idAluno = alunoConsulta.getIdAluno();

            List<Boletim> boletimList =
                    boletimDAO.visualizarBoletim(idAluno);

            String turma =
                    turmasDAO.buscarNomePorIdAluno(idAluno);

            req.setAttribute("boletimList", boletimList);
            req.setAttribute("alunoConsulta", alunoConsulta);
            req.setAttribute("turma", turma);
            System.out.println(turma);
            System.out.println(alunoConsulta);
            System.out.println(boletimList);

            req.getRequestDispatcher("/WEB-INF/admin/perfilAluno.jsp")
                    .forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();

            resp.sendRedirect(req.getContextPath() + "/admin/verTurmas");

        }
    }
}