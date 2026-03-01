package org.example.projetodiogo.servlets.aluno;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.BoletimDAO;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.model.Aluno;
import org.example.projetodiogo.model.Boletim;
import org.example.projetodiogo.model.Disciplina;
import org.example.projetodiogo.model.Usuario;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/aluno/gerarBoletim")
public class GerarBoletimServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        AlunoDAO alunoDAO = new AlunoDAO();
        Optional<Aluno> aluno = alunoDAO.buscarPorIdUsuario(usuario.getId());
        BoletimDAO boletimDAO = new BoletimDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment; filename=boletim.pdf");

        List<Boletim> boletimList = boletimDAO.visualizarBoletim(aluno.get().getId());
        ArrayList<Disciplina> disciplinasList = disciplinaDAO.visualizarDisciplinas();

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, resp.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph(("Boletim - " + usuario.getNome()), titleFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" "));

            PdfPTable tabela = new PdfPTable(4);
            tabela.setWidthPercentage(100);

            tabela.addCell("Disciplina");
            tabela.addCell("N1");
            tabela.addCell("N2");
            tabela.addCell("Media Final");

            for (int i = 0; i < boletimList.size(); i++) {
                Boletim boletim = boletimList.get(i);
                String nomeDisciplina = disciplinasList.get(i).getNome();
                tabela.addCell(nomeDisciplina);
                tabela.addCell(String.valueOf(boletim.getMedia1()));
                tabela.addCell(String.valueOf(boletim.getMedia2()));
                tabela.addCell(String.valueOf(boletim.getMediaFinal()));
            }

            document.add(tabela);
            document.close();

        } catch (DocumentException e) {
            throw new IOException("Erro ao gerar PDF", e);
        }
    }
}
