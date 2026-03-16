<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flash-message.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/loading-button.css">
<script src="${pageContext.request.contextPath}/assets/js/loading-button.js" defer></script>

<%
    String mensagemSucesso = (String) session.getAttribute("mensagemSucesso");
    String mensagemErro = (String) session.getAttribute("mensagemErro");
    String mensagemAviso = (String) session.getAttribute("mensagemAviso");
%>

<% if (mensagemSucesso != null) { %>

<div class="flash-message">
    <div class="modal-content success">
        <h4><%= mensagemSucesso %></h4>
    </div>
</div>

<%
        session.removeAttribute("mensagemSucesso");
    }
%>


<% if (mensagemErro != null) { %>

<div class="flash-message">
    <div class="modal-content error">
        <h4><%= mensagemErro %></h4>
    </div>
</div>

<%
        session.removeAttribute("mensagemErro");
    }
%>


<% if (mensagemAviso != null) { %>

<div class="flash-message">
    <div class="modal-content warning">
        <h4><%= mensagemAviso %></h4>
    </div>
</div>

<%
        session.removeAttribute("mensagemAviso");
    }
%>


<script>

    document.addEventListener("DOMContentLoaded", function(){

        const popups = document.querySelectorAll(".flash-message");

        popups.forEach(function(popup){

            setTimeout(function(){

                popup.style.opacity = "0";

                setTimeout(function(){
                    popup.remove();
                },500);

            },2500);

        });

    });

</script>