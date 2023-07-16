<%@ page import="model.Mesa" %>
<%@ page import="dao.MesaDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Editar Mesa</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-3">Editar Mesa</h1>

            <%
                int mesaId = Integer.parseInt(request.getParameter("id"));
                Mesa mesa = new MesaDAO().getMesa(mesaId);

                if (mesa != null) {
            %>
            <form method="post" action="mesa">
                <input type="hidden" name="action" value="atualizar" />
                <input type="hidden" name="id" value="<%= mesa.getId() %>" />

                <div class="mb-3">
                    <label for="localizacao" class="form-label">Localização:</label>
                    <select id="localizacao" name="localizacao" class="form-select" required>
                        <option value="area-externa" <%= mesa.getLocalizacao().equals("area-externa") ? "selected" : "" %>>Área Externa</option>
                        <option value="area-interna" <%= mesa.getLocalizacao().equals("area-interna") ? "selected" : "" %>>Área Interna</option>
                        <option value="bar" <%= mesa.getLocalizacao().equals("bar") ? "selected" : "" %>>Bar</option>
                    </select>
                </div>

                <input type="submit" value="Atualizar" class="btn btn-primary" />
            </form>
            <% } else { %>
            <p>A mesa não foi encontrada.</p>
            <% } %>

            <a href="gerenciarMesas.jsp" class="btn btn-link mt-3">Voltar</a>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>