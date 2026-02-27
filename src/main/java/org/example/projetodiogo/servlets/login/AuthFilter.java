package org.example.projetodiogo.servlets.login;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.projetodiogo.model.Usuario;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        boolean rotaPublica =
                uri.endsWith("index.jsp") ||
                        uri.endsWith("validarPreCadastro") ||
                        uri.endsWith("FinalizarCadastroAluno") ||
                        uri.endsWith("login") ||
                        uri.endsWith("logout") ||
                        uri.contains("/assets/");

        if (rotaPublica) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        if (usuario == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        String tipo = usuario.getTipoUsuario();

        if (uri.contains("/admin/") && !tipo.equalsIgnoreCase("admin")) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        if (uri.contains("/professor/") && !tipo.equalsIgnoreCase("professor")) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        if (uri.contains("/aluno/") && !tipo.equalsIgnoreCase("aluno")) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}