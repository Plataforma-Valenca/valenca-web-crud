package org.example.projetodiogo.servlets.aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.model.Boletim;

import java.io.IOException;

@WebServlet("/notasPorDisciplina")
public class NotasPorDisciplinaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idUsuario = Integer.parseInt(req.getParameter("idUsuario"));
        int idDisciplina = Integer.parseInt(req.getParameter("idDisciplina"));
        DisciplinaDAO dao = new DisciplinaDAO();

        Boletim boletim = dao.visualizarPorDisciplina(idUsuario, idDisciplina);

        req.setAttribute("idUsuario", idUsuario);
        req.setAttribute("idDisciplina", idDisciplina);
        req.setAttribute("nomeDisciplina", dao.buscarPorId(idDisciplina).getNome());
        req.setAttribute("media1", boletim.getMedia1());
        req.setAttribute("media2", boletim.getMedia2());
        req.setAttribute("mediaFinal", boletim.getMediaFinal());
    }
}
