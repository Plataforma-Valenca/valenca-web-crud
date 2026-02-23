package org.example.projetodiogo.servlets.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.ObservacaoDAO;
import org.example.projetodiogo.dao.ProfessorDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.RequiredFieldException;
import org.example.projetodiogo.model.Aluno;
import org.example.projetodiogo.model.Observacao;
import org.example.projetodiogo.model.Professor;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

@WebServlet("/professor/cadastrarObservacao")
public class IncluirObservacaoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/professor/adicionarObservacao.jsp")
                .forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Observacao observacao = new Observacao();
        ObservacaoDAO observacaoDAO = new ObservacaoDAO();
        Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
        ProfessorDAO professorDAO = new ProfessorDAO();
        Optional<Professor> professorOpt = professorDAO.buscarProfessorPorIdUsuario(usuario.getId());
        AlunoDAO alunoDAO = new AlunoDAO();
        Optional<Aluno> alunoOpt = alunoDAO.buscarPorIdUsuario(usuario.getId());
        String descricaoObs = req.getParameter("descricaoObs");


        try {
            if (descricaoObs != null && !descricaoObs.isEmpty()) {
                int idAluno = alunoOpt.get().getId();
                int idProfessor = professorOpt.get().getId();

                observacao.setDescricao(descricaoObs);
                observacao.setIdAluno(idAluno);
                observacao.setIdProfessor(idProfessor);
            }

            if (observacaoDAO.inserir(observacao)) {
                req.getSession().setAttribute("mensagemSucesso", "Administrador adicionado com sucesso!");
                resp.sendRedirect(req.getContextPath() + "/professor/verAlunos");
            }

        } catch (RequiredFieldException rfe) {
            System.err.println("[ERRO DE CAMPO OBRIGATÓRIO]: " + rfe);
            req.setAttribute("errorMessage", "Ops! " + rfe.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/admin/adicionar.jsp").forward(req, resp);
        } catch (DataAccessException dae) {
            System.err.println("[ERRO INTERNO]: " + dae);
            req.getRequestDispatcher("/WEB-INF/views/erro.jsp");
        }
    }
}
