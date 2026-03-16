package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/admin/editarAluno")
public class AdminEditarAlunoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idAluno = req.getParameter("idAluno");
        String nome = req.getParameter("nome");
        String matricula = req.getParameter("matricula");
        String idTurma = req.getParameter("idTurma");
        String nomeTurma = req.getParameter("nomeTurma");

        AlunoDAO alunoDAO = new AlunoDAO();

        try {
            alunoDAO.update(Integer.parseInt(idAluno), nome, matricula);
            req.getSession().setAttribute("mensagemSucesso", "Aluno atualizado com sucesso!");
        } catch (Exception e) {
            req.getSession().setAttribute("mensagemErro", "Erro ao atualizar aluno.");
        }

        resp.sendRedirect(req.getContextPath() + "/admin/verAlunos?idTurma=" + (idTurma != null ? idTurma : "")
                + "&nomeTurma=" + java.net.URLEncoder.encode(nomeTurma != null ? nomeTurma : "", "UTF-8"));
    }
}
    