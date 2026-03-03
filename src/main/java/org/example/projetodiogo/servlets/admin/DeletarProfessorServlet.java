package org.example.projetodiogo.servlets.admin;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.UsuarioDAO;

import java.io.IOException;

@WebServlet("/admin/DeletarProfessor")
public class DeletarProfessorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] ids = request.getParameterValues("professorId");

        if (ids != null) {
            UsuarioDAO dao = new UsuarioDAO();

            for (String idStr : ids) {
                try {
                    int id = Integer.parseInt(idStr);
                    dao.deletar(id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/verProfessores");
    }
}