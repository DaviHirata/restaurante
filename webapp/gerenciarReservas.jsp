<%@ page import="model.Reserva" %>
<%@ page import="dao.ReservaDAO" %>
<%@ page import="model.Mesa" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Reservas</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-3">Reservas</h1>

            <a href="/restaurante_teste/cadastrarReserva.jsp" class="btn btn-primary">Cadastrar nova reserva</a>

            <table class="table mt-3">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Mesa</th>
                    <th>Localização</th>
                    <th>Nome do Cliente</th>
                    <th>Número de Pessoas</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<Reserva> reservas = new ReservaDAO().getReservas();
                    for (Reserva reserva : reservas) {
                %>
                <tr>
                    <td><%= reserva.getId() %></td>
                    <td><%= reserva.getMesa().getId() %></td>
                    <td><%=reserva.getMesa().getLocalizacao()%></td>
                    <td><%= reserva.getNomeCliente() %></td>
                    <td><%= reserva.getNumeroPessoas() %></td>
                    <td>
                        <a href="editarReserva.jsp?id=<%= reserva.getId() %>" class="btn btn-primary">Editar</a>
                        <form method="post" action="reserva" style="display: inline;">
                            <input type="hidden" name="action" value="excluir" />
                            <input type="hidden" name="id" value="<%= reserva.getId() %>" />
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Deseja realmente excluir esta reserva?')">Excluir</button>
                        </form>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>

            <a href="principal.jsp" class="btn btn-link mt-3">Voltar</a>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>