package org.example.projetodiogo.servlets.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.*;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/professor/verPerfilAluno")
public class VerPerfilAluno extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        int idUsuario = usuario.getId();
        AlunoDAO alunoDAO = new AlunoDAO();
        BoletimDAO boletimDAO = new BoletimDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        TurmasDAO turmasDAO = new TurmasDAO();
        ObservacaoDAO observacaoDAO = new ObservacaoDAO();
        Boletim boletim;
        int idAlunoParam = Integer.parseInt(req.getParameter("id"));


        try {
            int idProfessor = professorDAO.buscarProfessorPorIdUsuario(idUsuario).get().getId();
            int idDisciplina = disciplinaDAO.buscarPorId(idProfessor).getIdProfessor();

            boletim = boletimDAO.visualizarNotasPorDisciplina(idAlunoParam, idDisciplina);

            List<Observacao> obsList = observacaoDAO.buscarPorIdAluno(idAlunoParam);

            req.setAttribute("boletim", boletim);
            req.setAttribute("usuarioNome", usuario.getNome());
            req.setAttribute("matricula", alunoDAO.buscarPorIdAluno(idAlunoParam).get().getMatricula());
            req.setAttribute("turma", turmasDAO.buscarNomePorIdAluno(idAlunoParam));
            req.setAttribute("obsList", obsList);

            req.getRequestDispatcher("/WEB-INF/professor/perfilAluno.jsp")
                    .forward(req, resp);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
