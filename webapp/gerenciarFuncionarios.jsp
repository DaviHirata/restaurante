<%@ page import="model.Funcionario" %>
<%@ page import="dao.FuncionarioDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Funcionários</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-3">Funcionários</h1>

            <table class="table mt-3">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Login</th>
                    <th>Ações</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ArrayList<Funcionario> funcionarios = new FuncionarioDAO().getFuncionarios();
                    for (Funcionario funcionario : funcionarios) {
                %>
                <tr>
                    <td><%= funcionario.getId() %></td>
                    <td><%= funcionario.getNome() %></td>
                    <td><%= funcionario.getLogin() %></td>
                    <td>
                        <a href="editarFuncionario.jsp?id=<%= funcionario.getId() %>" class="btn btn-primary">Editar</a>
                        <form method="post" action="funcionario" style="display: inline;">
                            <input type="hidden" name="action" value="excluir" />
                            <input type="hidden" name="id" value="<%= funcionario.getId() %>" />
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Deseja realmente excluir este funcionário?')">Excluir</button>
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