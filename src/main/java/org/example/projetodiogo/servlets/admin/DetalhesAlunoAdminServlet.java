package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.*;
import org.example.projetodiogo.dao.DTO.AlunoConsultaDtoDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.*;
import org.example.projetodiogo.model.DTO.AlunoConsultaDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/admin/detalhesAluno")
public class DetalhesAlunoAdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        AlunoDAO alunoDAO = new AlunoDAO();
        BoletimDAO boletimDAO = new BoletimDAO();
        TurmasDAO turmasDAO = new TurmasDAO();
        ObservacaoDAO observacaoDAO = new ObservacaoDAO();
        AlunoConsultaDtoDAO alunoConsultaDAO = new AlunoConsultaDtoDAO();

        int idAlunoParam = Integer.parseInt(req.getParameter("idAluno").trim());
        String matriculaParam = req.getParameter("matricula");
        String turmaParam = req.getParameter("turma");
        String idTurmaParam = req.getParameter("idTurma");
        String nomeTurmaParam = req.getParameter("nomeTurma");

        try {

            Optional<Aluno> alunoOpt = alunoDAO.buscarPorIdAluno(idAlunoParam);
            if (alunoOpt.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            Aluno aluno = alunoOpt.get();

            AlunoConsultaDTO alunoConsultaDTO =
                    alunoConsultaDAO.buscarPorMatricula(aluno.getMatricula());

            ArrayList<Boletim> boletimList =
                    boletimDAO.visualizarBoletim(idAlunoParam);

            List<Observacao> obsList =
                    observacaoDAO.buscarPorIdAluno(idAlunoParam);

            String turmaNome = turmasDAO.buscarNomePorIdAluno(idAlunoParam);

            req.setAttribute("boletimList", boletimList);
            req.setAttribute("alunoConsulta", alunoConsultaDTO);
            req.setAttribute("obsList", obsList);
            req.setAttribute("turma", turmaParam != null ? turmaParam : turmaNome);
            req.setAttribute("matricula", matriculaParam);
            req.setAttribute("idTurma", idTurmaParam);
            req.setAttribute("nomeTurma", nomeTurmaParam);

            req.getRequestDispatcher("/WEB-INF/admin/perfilAluno.jsp")
                    .forward(req, resp);

        } catch (DataAccessException e) {
            throw new ServletException(e);
        }
    }
}