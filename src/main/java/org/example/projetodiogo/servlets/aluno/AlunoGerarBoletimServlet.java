package org.example.projetodiogo.servlets.aluno;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.example.projetodiogo.dao.AlunoDAO;
import org.example.projetodiogo.dao.BoletimDAO;
import org.example.projetodiogo.dao.DisciplinaDAO;
import org.example.projetodiogo.model.*;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/aluno/gerarBoletim")
public class AlunoGerarBoletimServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        AlunoDAO alunoDAO = new AlunoDAO();
        Optional<Aluno> aluno = alunoDAO.buscarPorIdUsuario(usuario.getId());

        if (aluno.isEmpty()) {
            resp.getWriter().println("Aluno não encontrado");
            return;
        }

        BoletimDAO boletimDAO = new BoletimDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

        List<Boletim> boletimList = boletimDAO.visualizarBoletim(aluno.get().getId());
        ArrayList<Disciplina> disciplinasList = disciplinaDAO.visualizarDisciplinas();

        resp.reset();
        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment; filename=boletim.pdf");

        Document document = new Document(PageSize.A4);

        try {

            PdfWriter.getInstance(document, resp.getOutputStream());
            document.open();

            // FONTES
            Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22);
            Font subtituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

            // TITULO ESCOLA
            Paragraph escola = new Paragraph("COLÉGIO VALENÇA", tituloFont);
            escola.setAlignment(Element.ALIGN_CENTER);
            document.add(escola);

            // SUBTITULO
            Paragraph boletimTitulo = new Paragraph("Boletim Escolar", subtituloFont);
            boletimTitulo.setAlignment(Element.ALIGN_CENTER);
            document.add(boletimTitulo);

            document.add(new Paragraph(" "));

            // NOME DO ALUNO
            Paragraph alunoNome = new Paragraph("Aluno: " + usuario.getNome(), normalFont);
            alunoNome.setAlignment(Element.ALIGN_LEFT);
            document.add(alunoNome);

            // DATA
            LocalDate data = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            Paragraph dataGeracao = new Paragraph("Gerado em: " + data.format(formatter), normalFont);
            dataGeracao.setAlignment(Element.ALIGN_LEFT);
            document.add(dataGeracao);

            document.add(new Paragraph(" "));

            // LINHA SEPARADORA
            LineSeparator linha = new LineSeparator();
            linha.setLineWidth(1);
            document.add(new Chunk(linha));

            document.add(new Paragraph(" "));

            // TABELA
            PdfPTable tabela = new PdfPTable(4);
            tabela.setWidthPercentage(100);
            tabela.setSpacingBefore(10);
            tabela.setSpacingAfter(10);

            float[] largura = {4, 1, 1, 1};
            tabela.setWidths(largura);

            PdfPCell cell;

            // CABEÇALHO
            cell = new PdfPCell(new Phrase("Disciplina", headerFont));
            cell.setBackgroundColor(new Color(220,230,240));
            tabela.addCell(cell);

            cell = new PdfPCell(new Phrase("N1", headerFont));
            cell.setBackgroundColor(new Color(220,230,240));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cell);

            cell = new PdfPCell(new Phrase("N2", headerFont));
            cell.setBackgroundColor(new Color(220,230,240));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cell);

            cell = new PdfPCell(new Phrase("Média", headerFont));
            cell.setBackgroundColor(new Color(220,230,240));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabela.addCell(cell);

            // DADOS
            for (int i = 0; i < boletimList.size(); i++) {

                Boletim boletim = boletimList.get(i);
                String nomeDisciplina = disciplinasList.get(i).getNome();

                tabela.addCell(nomeDisciplina);

                PdfPCell n1 = new PdfPCell(new Phrase(String.valueOf(boletim.getMedia1())));
                n1.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabela.addCell(n1);

                PdfPCell n2 = new PdfPCell(new Phrase(String.valueOf(boletim.getMedia2())));
                n2.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabela.addCell(n2);

                PdfPCell media = new PdfPCell(new Phrase(String.valueOf(boletim.getMediaFinal())));
                media.setHorizontalAlignment(Element.ALIGN_CENTER);

                if (boletim.getMediaFinal() >= 6) {
                    media.setBackgroundColor(new Color(200,255,200));
                } else {
                    media.setBackgroundColor(new Color(255,200,200));
                }

                tabela.addCell(media);
            }

            document.add(tabela);

            // RODAPÉ
            document.add(new Paragraph(" "));
            Paragraph rodape = new Paragraph("Documento gerado automaticamente pelo sistema.");
            rodape.setAlignment(Element.ALIGN_CENTER);
            document.add(rodape);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}