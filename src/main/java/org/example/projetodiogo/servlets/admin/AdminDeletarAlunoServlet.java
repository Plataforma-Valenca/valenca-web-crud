package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.TurmasDAO;

import java.io.IOException;

@WebServlet("/admin/deletarAluno")
public class AdminDeletarAlunoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idAlunoStr = request.getParameter("idAluno");
        String idTurma = request.getParameter("idTurma");
        String nomeTurma = request.getParameter("nomeTurma");
        String nomeTurmaEncoded = java.net.URLEncoder.encode(nomeTurma != null ? nomeTurma : "", "UTF-8");

        if (idAlunoStr != null && !idAlunoStr.isEmpty()) {
            try {
                int idAluno = Integer.parseInt(idAlunoStr);
                AlunoDAO dao = new AlunoDAO();
                dao.delete(idAluno);

                request.getSession().setAttribute("mensagemSucesso", "Aluno deletado com sucesso!");

            } catch (NumberFormatException e) {
                e.printStackTrace();
                request.getSession().setAttribute("mensagemErro", "Erro ao deletar aluno.");
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/verAlunos?idTurma=" + idTurma + "&nomeTurma=" + nomeTurmaEncoded);
    }
}