package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Aluno;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/admin/editarAluno")
public class EditarAlunoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int idAluno = Integer.parseInt(request.getParameter("idAluno"));
            String nome = request.getParameter("nome");
            long matricula = Long.parseLong(request.getParameter("matricula"));

            AlunoDAO alunoDAO = new AlunoDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();

            // atualizar aluno
            Aluno aluno = new Aluno();
            aluno.setId(idAluno);
            aluno.setMatricula(matricula);

            alunoDAO.update(aluno);

            // atualizar nome no usuario
            usuarioDAO.atualizarNomePorAluno(idAluno, nome);

            response.sendRedirect(request.getHeader("referer"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}