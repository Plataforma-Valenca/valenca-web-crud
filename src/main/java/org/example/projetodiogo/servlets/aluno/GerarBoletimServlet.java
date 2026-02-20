package org.example.projetodiogo.servlets.aluno;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.BoletimDAO;
import org.example.projetodiogo.model.Aluno;
import org.example.projetodiogo.model.Boletim;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@WebServlet(value="/gerarBoletim")
public class GerarBoletimServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idUsuario = req.getParameter("idUsuario");
        AlunoDAO alunoDAO = new AlunoDAO();
        Optional<Aluno> aluno = alunoDAO.buscarPorIdUsuario(Integer.parseInt(idUsuario));
        BoletimDAO boletimDAO = new BoletimDAO();

        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment; filename=boletim.pdf");

        ArrayList<Boletim> boletimList = boletimDAO.visualizarBoletim(aluno.get().getId());

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, resp.getOutputStream());
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph titulo = new Paragraph("Boletim", titleFont);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" "));

            PdfPTable tabela = new PdfPTable(4);
            tabela.setWidthPercentage(100);

            tabela.addCell("Disciplina");
            tabela.addCell("N1");
            tabela.addCell("N2");
            tabela.addCell("Media Final");

            for (Boletim boletim : boletimList) {
                tabela.addCell(boletim.getNomeDisciplina());
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
