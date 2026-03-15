package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.TurmasDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/inserirAluno")
public class AdminInserirAlunoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            // Redireciona para a página
            req.getRequestDispatcher("/WEB-INF/admin/admin-ver-professores.jsp")
                    .forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("erro.jsp");
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        TurmasDAO turmasDAO = new TurmasDAO();
        AlunoDAO alunoDAO = new AlunoDAO();

        String senha = req.getParameter("senhaProvisoria");
        String cpf = req.getParameter("cpf");

        int idTurma = Integer.parseInt(req.getParameter("idTurma").trim());

        try {
            usuario.setSenha(senha);
            usuario.setCpf(cpf);
            int idUsuarioCriado = usuarioDAO.inserirNovoAluno(usuario);
            int idAluno = alunoDAO.inserir(idUsuarioCriado);
            alunoDAO.vincularAlunoADisciplinasTurma(idAluno, idTurma);

            req.getSession().setAttribute("mensagemSucesso", "Aluno pré-cadastrado com sucesso!");


        } catch (SQLException e) {
            req.getSession().setAttribute("mensagemErro", "Erro ao cadastrar aluno.");
            throw new RuntimeException(e);
        }
        resp.sendRedirect(req.getContextPath() + "/admin/verAlunos?idTurma=" + idTurma);
    }
}
