package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.TurmasDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Turma;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/inserirAluno")
public class InserirAlunoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            TurmasDAO turmaDAO = new TurmasDAO();

            // Busca todas as turmas
            List<Turma> turmas = turmaDAO.buscarTurmas();

            // Envia para a JSP
            request.setAttribute("turmas", turmas);

            // Redireciona para a página
            request.getRequestDispatcher("/admin/verAlunos.jsp").forward(request, response);
            System.out.println("===== LISTA DE TURMAS =====");

            for (Turma t : turmas) {
                System.out.println("ID: " + t.getId() + " | Nome: " + t.getNome());
            }
            System.out.println("fim");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("erro.jsp");
        }
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
            int idTurma = turmasDAO.buscarPorIdAluno(idAluno).getId();
            alunoDAO.vincularAlunoADisciplinasTurma(idAluno, idTurma);

            req.getSession().setAttribute("mensagemSucesso", "Aluno pré-cadastrado com sucesso!");
            resp.sendRedirect(req.getContextPath() + "/admin/verAlunos");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
