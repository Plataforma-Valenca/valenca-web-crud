package org.example.projetodiogo.servlets.aluno;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.BoletimDAO;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.model.Boletim;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/aluno/notasPorDisciplina")
public class NotasPorDisciplinaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        AlunoDAO alunoDAO = new AlunoDAO();
        BoletimDAO boletimDAO = new BoletimDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        int idAluno = alunoDAO.buscarPorIdUsuario(usuario.getId()).get().getId();
        int idDisciplina = Integer.parseInt(req.getParameter("idDisciplina"));

        ArrayList<Boletim> boletimList = boletimDAO.visualizarNotasPorDisciplina(idAluno, idDisciplina);

        req.setAttribute("idDisciplina", idDisciplina);
        req.setAttribute("nomeDisciplina", disciplinaDAO.buscarPorId(idDisciplina).getNome());
        req.setAttribute("boletimList", boletimList);

        req.getRequestDispatcher("/WEB-INF/aluno/notasPorDisciplina.jsp")
                .forward(req, resp);
    }
}
