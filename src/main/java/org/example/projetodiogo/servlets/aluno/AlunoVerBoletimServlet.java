package org.example.projetodiogo.servlets.aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.BoletimDAO;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.dao.UsuarioDAO;
import org.example.projetodiogo.model.Boletim;
import org.example.projetodiogo.model.Disciplina;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/aluno/VerBoletim")
public class AlunoVerBoletimServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        BoletimDAO boletimDAO = new BoletimDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        int idAluno = alunoDAO.buscarPorIdUsuario(usuario.getId()).get().getId();
        ArrayList<Disciplina> disciplinasList = disciplinaDAO.visualizarDisciplinas();

        ArrayList<Boletim> boletimList = boletimDAO.visualizarBoletim(idAluno);

        req.setAttribute("disciplinasList", disciplinasList);
        req.setAttribute("boletimList", boletimList);

        req.getRequestDispatcher("/WEB-INF/aluno/boletim.jsp")
                .forward(req, resp);
    }
}
