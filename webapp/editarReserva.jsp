<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Reserva" %>
<%@ page import="dao.ReservaDAO" %>
<%@ page import="model.Mesa" %>
<% int reservaId = Integer.parseInt(request.getParameter("id"));
    Reserva reserva = new ReservaDAO().getReserva(reservaId); %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Mesa" %>
<%@ page import="dao.MesaDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Editar Reserva</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-3">Editar Reserva</h1>

            <%
                if (reserva != null) {
            %>
            <form method="post" action="reserva">
                <input type="hidden" name="action" value="atualizar" />
                <input type="hidden" name="id" value="<%=reserva.getId()%>" />

                <div class="mb-3">
                    <label for="idMesa" class="form-label">Mesa:</label>
                    <select id="idMesa" name="idMesa" class="form-select" required>
                        <% ArrayList<Mesa> mesas = new MesaDAO().getMesas();
                            for (Mesa mesa : mesas) { %>
                        <option value="<%= mesa.getId() %>" <% if (reserva.getMesa().getId() == mesa.getId()) { %>selected<% } %>><%= mesa.getId() %></option>
                        <% } %>
                    </select>
                </div>

                <div class="mb-3">
                    <label for="nomeCliente" class="form-label">Nome do Cliente:</label>
                    <input type="text" id="nomeCliente" name="nomeCliente" value="<%=reserva.getNomeCliente()%>" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label for="numeroPessoas" class="form-label">Número de Pessoas:</label>
                    <input type="number" id="numeroPessoas" name="numeroPessoas" value="<%=reserva.getNumeroPessoas()%>" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label for="localizacao" class="form-label">Localização da Mesa:</label>
                    <input type="text" id="localizacao" name="localizacao" value="<%=reserva.getMesa().getLocalizacao()%>" class="form-control" readonly />
                </div>

                <input type="submit" value="Atualizar" class="btn btn-primary" />
            </form>
            <% } else { %>
            <p>A reserva não foi encontrada.</p>
            <% } %>
            <a href="gerenciarReservas.jsp" class="btn btn-link mt-3">Voltar</a>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>