package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.ProfessorDAO;
import org.example.projetodiogo.dao.UsuarioDAO;

import java.io.IOException;

@WebServlet("/admin/DeletarProfessor")
public class DeletarProfessorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = req.getParameter("idProfessor");

        if (idStr != null && !idStr.isEmpty()) {

            try {
                int id = Integer.parseInt(idStr);

                ProfessorDAO dao = new ProfessorDAO();
                boolean deletar = dao.delete(id);

                req.getSession().setAttribute("mensagemSucesso", "Professor deletado com sucesso!");

            } catch (Exception e) {
                e.printStackTrace();
                req.getSession().setAttribute("mensagemErro", "Erro ao deletar professor.");
            }
        }

        response.sendRedirect(req.getContextPath() + "/admin/verProfessores");
    }
}