package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.dao.ProfessorDAO;
import org.example.projetodiogo.dao.TurmasDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Disciplina;
import org.example.projetodiogo.model.Professor;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/inserirProfessor")
public class InserirProfessorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/admin/inserirAluno.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        TurmasDAO turmasDAO = new TurmasDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        String nomeCompleto = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");
        String cpf = req.getParameter("cpf");
        String username = req.getParameter("username");
//        int idTurma = Integer.parseInt(req.getParameter("idTurma"));
        String nomeDisciplina = req.getParameter("nomeDisciplina");

        Usuario usuario = new Usuario();
        usuario.setNome(nomeCompleto);
        usuario.setSenha(senha);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setUsername(username);

        int idUsuario = usuarioDAO.inserirProfessor(usuario);

        Professor professor = new Professor();
        professor.setIdUsuario(idUsuario);
        int idProfessor = professorDAO.insert(professor);

    System.out.println("Nome: "+ nomeCompleto);
        System.out.println();

        Disciplina disciplina = new Disciplina();
        disciplina.setIdProfessor(idProfessor);
        disciplina.setNome(nomeDisciplina);
        int inserirDisciplina = disciplinaDAO.inserirDisciplina(disciplina);

        req.getSession().setAttribute("mensagemSucesso", "Professor cadastrado com sucesso!");
        resp.sendRedirect(req.getContextPath() + "/admin/verProfessores");

    }
}