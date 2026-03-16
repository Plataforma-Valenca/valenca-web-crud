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
            req.getRequestDispatcher("/WEB-INF/admin/admin-ver-alunos.jsp")
                    .forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("erro.jsp");
        }
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        AlunoDAO alunoDAO = new AlunoDAO();

        String senha = req.getParameter("senhaProvisoria");
        String cpf = req.getParameter("cpf");
        String nomeTurma = req.getParameter("nomeTurma");
        int idTurma = Integer.parseInt(req.getParameter("idTurma").trim());

        try {
            usuario.setSenha(senha);
            usuario.setCpf(cpf);
            int idUsuarioCriado = usuarioDAO.inserirNovoAluno(usuario);
            int idAluno = alunoDAO.inserir(idUsuarioCriado);
            alunoDAO.vincularAlunoADisciplinasTurma(idAluno, idTurma);

            req.getSession().setAttribute("mensagemSucesso", "Aluno pré-cadastrado com sucesso!");

            resp.sendRedirect(req.getContextPath() + "/admin/verAlunos?idTurma=" + idTurma
                    + "&nomeTurma=" + java.net.URLEncoder.encode(nomeTurma != null ? nomeTurma : "", "UTF-8"));

        } catch (SQLException e) {
            req.getSession().setAttribute("mensagemErro", "Erro ao cadastrar aluno.");

            resp.sendRedirect(req.getContextPath() + "/admin/verAlunos?idTurma=" + idTurma
                    + "&nomeTurma=" + java.net.URLEncoder.encode(nomeTurma != null ? nomeTurma : "", "UTF-8"));
        }
    }
}
