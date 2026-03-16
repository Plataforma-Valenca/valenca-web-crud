package org.example.projetodiogo.servlets.professor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.NotaDAO;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/professor/inserirNota")
public class ProfessorInserirNotaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        int idAluno = Integer.parseInt(req.getParameter("idAluno"));

        NotaDAO notaDAO = new NotaDAO();

        Enumeration<String> params = req.getParameterNames();

        while (params.hasMoreElements()) {
            String param = params.nextElement();

            if (param.startsWith("n1_")) {
                int idDisciplina = Integer.parseInt(param.substring(3));

                String n1Str = req.getParameter("n1_" + idDisciplina);
                String n2Str = req.getParameter("n2_" + idDisciplina);

                Double n1 = (n1Str != null && !n1Str.isBlank())
                        ? Double.parseDouble(n1Str) : null;
                Double n2 = (n2Str != null && !n2Str.isBlank())
                        ? Double.parseDouble(n2Str) : null;

                notaDAO.salvarOuAtualizarNota(idAluno, idDisciplina, n1, n2);
            }
        }

        session.setAttribute("mensagemSucesso", "Notas lançadas com sucesso!");
        resp.sendRedirect(req.getContextPath() + "/professor/verPerfilAluno?idAluno=" + idAluno);
    }
}