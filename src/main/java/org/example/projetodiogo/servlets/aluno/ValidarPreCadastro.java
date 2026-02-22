package org.example.projetodiogo.servlets.aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Aluno;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/validarPreCadastro")
public class ValidarPreCadastro extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login/validarPreCadastro.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String matriculaOuCpf = req.getParameter("inputValidacao");
        Usuario usuario = null;
        Optional<Aluno> aluno = null;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        AlunoDAO alunoDAO = new AlunoDAO();


        if (matriculaOuCpf == null || matriculaOuCpf.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            req.setAttribute("erroLogin", "Campo obrigatório");
            req.getRequestDispatcher("/WEB-INF/login/validarPreCadastro.jsp")
                    .forward(req, resp);
        } else {
            usuario = usuarioDAO.validarPrimeiroAcesso(matriculaOuCpf);
            if (usuario == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                req.setAttribute("erroLogin", "Cadastro já realizado ou acesso inválido! Contate a escola.");
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("cpfOuMatriculaValidacao", matriculaOuCpf);
                req.getRequestDispatcher("/WEB-INF/login/finalizarCadastroAluno.jsp")
                        .forward(req, resp);
            }
        }
    }
}
