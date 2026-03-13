package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Disciplina;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;

@WebServlet("/admin/editarProfessor")
public class EditarProfessorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        int idProfessor = Integer.parseInt(req.getParameter("idProfessor"));

        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String cpf = req.getParameter("cpf");
        String username = req.getParameter("username");
        String nomeDisciplina = req.getParameter("nomeDisciplina");

        Usuario usuario = new Usuario();
        usuario.setId(idUsuario);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setCpf(cpf);
        usuario.setUsername(username);

        usuarioDAO.updateProfessor(usuario);

        Disciplina disciplina = new Disciplina();
        disciplina.setIdProfessor(idProfessor);
        disciplina.setNome(nomeDisciplina);

        disciplinaDAO.updateDisciplinaProfessor(disciplina);

        req.getSession().setAttribute("mensagemSucesso", "Professor atualizado com sucesso!");
        resp.sendRedirect(req.getContextPath() + "/admin/verProfessores");
    }
}