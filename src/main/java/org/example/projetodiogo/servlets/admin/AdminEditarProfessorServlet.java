package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.dao.ProfessorDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Professor;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;

@WebServlet("/admin/editarProfessor")
public class AdminEditarProfessorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            int idProfessor = Integer.parseInt(request.getParameter("idProfessor"));
            int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
            int idDisciplina = Integer.parseInt(request.getParameter("idDisciplina"));

            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String username = request.getParameter("username");
            String cpf = request.getParameter("cpf");
            String nomeDisciplina = request.getParameter("disciplina");

            ProfessorDAO professorDAO = new ProfessorDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

            /* -------------------------
               Atualizar USUARIO
             ------------------------- */
            Usuario usuario = new Usuario();
            usuario.setId(idUsuario);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setUsername(username);
            usuario.setCpf(cpf);

            usuarioDAO.atualizar(usuario);


            /* -------------------------
               Atualizar PROFESSOR
             ------------------------- */
            Professor professor = new Professor();
            professor.setIdUsuario(idProfessor);
            professor.setIdUsuario(idUsuario);

            professorDAO.update(professor);


            /* -------------------------
               Atualizar DISCIPLINA
             ------------------------- */
            disciplinaDAO.atualizarNomeDisciplina(idDisciplina, nomeDisciplina);


            response.sendRedirect(request.getHeader("referer"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}