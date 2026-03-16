package org.example.projetodiogo.servlets.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.NotaDAO;

import java.io.IOException;

@WebServlet("/professor/editarNota")
public class ProfessorEditarNotaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        int    idNota  = Integer.parseInt(req.getParameter("idNota"));
        int    idAluno = Integer.parseInt(req.getParameter("idAluno"));
        String n1Str   = req.getParameter("n1");
        String n2Str   = req.getParameter("n2");

        Double n1 = (n1Str != null && !n1Str.isBlank()) ? Double.parseDouble(n1Str) : null;
        Double n2 = (n2Str != null && !n2Str.isBlank()) ? Double.parseDouble(n2Str) : null;

        NotaDAO notaDAO = new NotaDAO();
        notaDAO.editarNota(idNota, n1, n2);

        session.setAttribute("mensagemSucesso", "Nota editada com sucesso!");
        resp.sendRedirect(req.getContextPath() + "/professor/verPerfilAluno?idAluno=" + idAluno);
    }
}