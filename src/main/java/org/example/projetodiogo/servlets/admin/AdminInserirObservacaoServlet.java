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

            // volta para a página anterior
            response.sendRedirect(request.getHeader("referer"));

            request.getSession().setAttribute("mensagemSucesso", "Professor cadastrado com sucesso!");

        } catch (Exception e) {
            request.getSession().setAttribute("mensagemErro", "Erro ao cadastrar professor.");
            e.printStackTrace();
            response.sendRedirect(request.getHeader("referer"));

        }

    }
}
