package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.TurmasDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.exceptions.DuplicateEmailException;
import org.example.projetodiogo.model.Usuario;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/admin/inserirAluno")
public class InserirAlunoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/inserirAluno.jsp")
                .forward(req, resp);;
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        TurmasDAO turmasDAO = new TurmasDAO();
        AlunoDAO alunoDAO = new AlunoDAO();

        String senha = req.getParameter("senha");
        String cpf = req.getParameter("cpf");
        String turma = req.getParameter("turma");

        try {
            usuario.setSenha(senha);
            usuario.setCpf(cpf);

            int idAluno = usuarioDAO.inserirNovoAluno(usuario);
            int idTurma = turmasDAO.buscarPorNome(turma);
            alunoDAO.vincularAlunoADisciplinasTurma(idAluno, idTurma);

            req.getSession().setAttribute("mensagemSucesso", "Aluno pré-cadastrado com sucesso!");
            resp.sendRedirect(req.getContextPath() + "/admin/verAlunos");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
