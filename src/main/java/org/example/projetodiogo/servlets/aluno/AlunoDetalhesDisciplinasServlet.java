package org.example.projetodiogo.servlets.aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.*;
import org.example.projetodiogo.dao.DTO.DisciplinasResumoDtoDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.*;
import org.example.projetodiogo.model.DTO.DisciplinasResumoDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet("/aluno/detalhesDisciplina")
public class AlunoDetalhesDisciplinasServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        DisciplinasResumoDtoDAO resumoDAO = new DisciplinasResumoDtoDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        ObservacaoDAO observacaoDAO = new ObservacaoDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        BoletimDAO boletimDAO = new BoletimDAO();

        try {
            ArrayList<DisciplinasResumoDTO> disciplinaList = resumoDAO.visualizarDisciplinasResumo();

            Optional<Aluno> alunoOpt = alunoDAO.buscarPorIdUsuario(usuario.getId());
            int idAluno = alunoOpt.get().getId();

            String idDiscParam = req.getParameter("idDisciplina");
            if (idDiscParam != null && !idDiscParam.isEmpty()) {
                int idDisciplina = Integer.parseInt(idDiscParam);

                Disciplina disciplina = disciplinaDAO.buscarPorId(idDisciplina);
                int idProfessor = disciplina.getIdProfessor();

                ArrayList<Observacao> obsList = observacaoDAO.buscarPorIdAlunoIdProfessor(idAluno, idProfessor);
                ArrayList<Boletim> boletimList = boletimDAO.visualizarNotasPorDisciplina(idAluno, idDisciplina);

                String nomeProfessor = usuarioDAO.buscarPorIdProfessor(idProfessor).get().getNome();

                req.setAttribute("obsList", obsList);
                req.setAttribute("boletimList", boletimList);
                req.setAttribute("nomeProfessor", nomeProfessor);
                req.setAttribute("nomeDisciplina", disciplina.getNome());
            }

            req.setAttribute("disciplinaList", disciplinaList);
            req.setAttribute("activePage", "disciplinas");

            req.getRequestDispatcher("/WEB-INF/aluno/aluno-detalhes-disciplinas.jsp").forward(req, resp);

        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao carregar detalhes da disciplina", e);
        }
    }
}