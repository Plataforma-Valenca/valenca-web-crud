package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.*;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/admin/verPerfilAluno")
public class VerPerfilAluno extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AlunoDAO alunoDAO = new AlunoDAO();
        BoletimDAO boletimDAO = new BoletimDAO();
        TurmasDAO turmasDAO = new TurmasDAO();
        AlunoConsultaDtoDAO alunoConsultaDAO = new AlunoConsultaDtoDAO();

        List<Boletim> boletimList;
        int idAlunoParam = Integer.parseInt(req.getParameter("idAluno"));


        try {
            Optional<Aluno> alunoOpt = alunoDAO.buscarPorIdAluno(idAlunoParam);

            AlunoConsultaDTO alunoConsultaDTO = alunoConsultaDAO.buscarPorMatricula(alunoOpt.get().getMatricula());

            boletimList = boletimDAO.visualizarBoletim(idAlunoParam);

            req.setAttribute("boletimList", boletimList);
            req.setAttribute("alunoConsulta", alunoConsultaDTO);
            req.setAttribute("turma", turmasDAO.buscarNomePorIdAluno(idAlunoParam));

            req.getRequestDispatcher("/WEB-INF/professor/perfilAluno.jsp")
                    .forward(req, resp);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
