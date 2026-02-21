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
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;

            String uri = req.getRequestURI();

            if (uri.endsWith("index.jsp") ||
                    uri.endsWith("validarPreCadastro") ||
                    uri.endsWith("FinalizarCadastroAluno") ||
                    uri.endsWith("login") ||
                    uri.endsWith("logout") ||
                    uri.contains("/assets/")) {

                chain.doFilter(request, response);
                return;
            }

            Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

            if (usuario == null) {
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
                return;
            }

            String tipo = usuario.getTipoUsuario();

            if (uri.contains("/admin/") && !tipo.equalsIgnoreCase("ADMIN")) {
                resp.sendRedirect("acessoNegado.jsp");
                return;
            }

            if (uri.contains("/professor/") && !tipo.equalsIgnoreCase("PROFESSOR")) {
                resp.sendRedirect("acessoNegado.jsp");
                return;
            }

            if (uri.contains("/aluno/") && !tipo.equalsIgnoreCase("ALUNO")) {
                resp.sendRedirect("acessoNegado.jsp");
                return;
            }

            chain.doFilter(request, response);
        }
}
