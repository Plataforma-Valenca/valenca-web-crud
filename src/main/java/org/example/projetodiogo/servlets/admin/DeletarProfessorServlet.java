package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.dao.ProfessorDAO;
import org.example.projetodiogo.dao.UsuarioDAO;

import java.io.IOException;

@WebServlet("/admin/deletarProfessor")
public class DeletarProfessorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idProfessorStr = req.getParameter("idProfessor");
        String idUsuarioStr = req.getParameter("idUsuario");

        System.out.println("ID PROFESSOR: " + idProfessorStr);
        System.out.println("ID USUARIO: " + idUsuarioStr);

        // valida se os parâmetros chegaram corretamente
        if (idProfessorStr == null || idUsuarioStr == null ||
                idProfessorStr.equals("undefined") || idUsuarioStr.equals("undefined")) {

            req.getSession().setAttribute("mensagemErro", "Erro ao identificar professor para exclusão.");
            resp.sendRedirect(req.getContextPath() + "/admin/verProfessores");
            return;
        }

        int idProfessor;
        int idUsuario;

        try {
            idProfessor = Integer.parseInt(idProfessorStr);
            idUsuario = Integer.parseInt(idUsuarioStr);
        } catch (NumberFormatException e) {

            req.getSession().setAttribute("mensagemErro", "IDs inválidos para exclusão.");
            resp.sendRedirect(req.getContextPath() + "/admin/verProfessores");
            return;
        }

        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        try {


            disciplinaDAO.deleteDisciplinaByProfessor(idProfessor);


            professorDAO.deleteProfessor(idProfessor, idUsuario);


            usuarioDAO.deletar(idUsuario);

            req.getSession().setAttribute("mensagemSucesso", "Professor removido com sucesso!");

        } catch (Exception e) {

            e.printStackTrace();
            req.getSession().setAttribute("mensagemErro", "Erro ao excluir professor.");

        }

        resp.sendRedirect(req.getContextPath() + "/admin/verProfessores");
    }
}