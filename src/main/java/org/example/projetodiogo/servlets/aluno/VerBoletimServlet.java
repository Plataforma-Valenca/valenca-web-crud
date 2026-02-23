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
import org.example.projetodiogo.exceptions.DataAccessException;
import org.example.projetodiogo.model.Boletim;

import java.io.IOException;
import java.util.List;

@WebServlet("/VerBoletim")
public class VerBoletimServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Boletim> boletimList;
        BoletimDAO boletimDAO = new BoletimDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        int idUsuario = (int) session.getAttribute("usuarioId");
        int idAluno = alunoDAO.buscarPorIdUsuario(idUsuario).get().getId();

        try {
            boletimList = boletimDAO.visualizarBoletim(idAluno);

            req.setAttribute("boletimList", boletimList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/aluno/boletim.jsp");
            dispatcher.forward(req, resp);
        } catch (DataAccessException e) {
            throw new DataAccessException("Erro ao acessar o banco de dados", e);
        }
    }
}
