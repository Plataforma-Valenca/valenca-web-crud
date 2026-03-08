package org.example.projetodiogo.servlets.aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.dao.ObservacaoDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Aluno;
import org.example.projetodiogo.model.Disciplina;
import org.example.projetodiogo.model.Observacao;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet("/aluno/verObservacoes")
public class VerObservacoesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        int idDisciplina = Integer.parseInt(req.getParameter("idDisciplina"));

        AlunoDAO alunoDAO = new AlunoDAO();
        ObservacaoDAO observacaoDAO = new ObservacaoDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Disciplina disciplina = disciplinaDAO.buscarPorId(idDisciplina);

        int idProfessor = disciplina.getIdProfessor();
        String nomeDisciplina = disciplina.getNome();

        Optional<Aluno> alunoOpt = alunoDAO.buscarPorIdUsuario(usuario.getId());
        int idAluno = alunoOpt.get().getId();

        ArrayList<Observacao> obsList =
                observacaoDAO.buscarPorIdAlunoIdProfessor(idAluno, idProfessor);

        String nomeProfessor =
                usuarioDAO.buscarPorIdProfessor(idProfessor).get().getNome();

        req.setAttribute("obsList", obsList);
        req.setAttribute("nomeProfessor", nomeProfessor);
        req.setAttribute("nomeDisciplina", nomeDisciplina);
        req.setAttribute("idDisciplina", idDisciplina);

        req.getRequestDispatcher("/WEB-INF/aluno/obsPorDisciplina.jsp")
                .forward(req, resp);
    }
}