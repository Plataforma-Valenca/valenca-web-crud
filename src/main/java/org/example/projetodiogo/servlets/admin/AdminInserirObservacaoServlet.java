package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.example.projetodiogo.dao.ObservacaoDAO;
import org.example.projetodiogo.model.Observacao;

import java.io.IOException;

@WebServlet("/admin/inserirObservacao")
public class AdminInserirObservacaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idAluno = Integer.parseInt(request.getParameter("idAluno"));
            int idProfessor = Integer.parseInt(request.getParameter("idProfessor"));
            String descricao = request.getParameter("descricao");

            Observacao obs = new Observacao();
            obs.setIdAluno(idAluno);
            obs.setIdProfessor(idProfessor);
            obs.setDescricao(descricao);

            ObservacaoDAO dao = new ObservacaoDAO();
            dao.inserir(obs);

            request.getSession().setAttribute("mensagemSucesso", "Observação adicionada com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("mensagemErro", "Erro ao adicionar observação.");
        }

        response.sendRedirect(request.getHeader("referer"));
    }
}