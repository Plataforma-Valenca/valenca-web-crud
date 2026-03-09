package org.example.projetodiogo.servlets.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.DTO.AlunoConsultaDtoDAO;
import org.example.projetodiogo.model.DTO.AlunoConsultaDTO;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/verAlunosTurma")
public class VerAlunosTurmaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idTurma = Integer.parseInt(request.getParameter("idTurma"));
        System.out.println("Id da turma: "+idTurma);

        AlunoConsultaDtoDAO dao = new AlunoConsultaDtoDAO();

        List<AlunoConsultaDTO> alunos = dao.buscarAlunosPorTurma(idTurma);
    System.out.println("Listar alunos"+ alunos);

        request.setAttribute("alunos", alunos);

        request.getRequestDispatcher("/WEB-INF/admin/listarAlunos.jsp")
                .forward(request, response);
    }
}