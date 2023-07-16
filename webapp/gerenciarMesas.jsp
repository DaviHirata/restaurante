<%@ page import="model.Mesa" %>
<%@ page import="dao.MesaDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Mesas</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-3">Mesas</h1>

            <a href="/restaurante_teste/cadastrarMesa.jsp" class="btn btn-primary">Cadastrar nova mesa</a>

            <table class="table mt-3">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Localização</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<Mesa> mesas = new MesaDAO().getMesas();
                    for (Mesa mesa : mesas) {
                %>
                <tr>
                    <td><%= mesa.getId() %></td>
                    <td><%= mesa.getLocalizacao() %></td>
                    <td>
                        <a href="editarMesa.jsp?id=<%= mesa.getId() %>" class="btn btn-primary">Editar</a>
                        <form method="post" action="mesa" style="display: inline;">
                            <input type="hidden" name="action" value="excluir" />
                            <input type="hidden" name="id" value="<%= mesa.getId() %>" />
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Deseja realmente excluir esta mesa?')">Excluir</button>
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