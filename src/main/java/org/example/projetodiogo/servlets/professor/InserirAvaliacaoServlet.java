package org.example.projetodiogo.servlets.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.AvaliacaoDAO;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.dao.ProfessorDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Avaliacao;
import org.example.projetodiogo.model.Disciplina;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/professor/inserirAvaliacao")
public class InserirAvaliacaoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // Redireciona para a página
            req.getRequestDispatcher("/WEB-INF/professor/verPerfilAluno.jsp")
                    .forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("erro.jsp");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();

        int idAluno = Integer.parseInt(req.getParameter("idAluno"));


        String descricao = req.getParameter("descricao");
        Double valor = Double.parseDouble(req.getParameter("valor"));
        int semestre = Integer.parseInt(req.getParameter("semestre"));

        try {
            Avaliacao avaliacao = new Avaliacao(descricao, valor, semestre);

            int idProfessor = professorDAO.buscarProfessorPorIdUsuario(usuario.getId()).get().getId();

            Optional<Disciplina> disciplina = disciplinaDAO.buscarPorIdProfessor(idProfessor);

            int idDisciplina = disciplina.get().getId();

            avaliacaoDAO.inserirAvaliacao(idAluno, idDisciplina, descricao, valor, semestre);

            req.getSession().setAttribute("mensagemSucesso", "Observação editada com sucesso!");
            resp.sendRedirect(req.getContextPath() + "/professor/verPerfilAluno?idAluno=" + idAluno);
        } catch (SQLException sqle) {
            throw new DataAccessException("Erro ao inserir avaliação", sqle);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
