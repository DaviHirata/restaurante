<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Funcionario" %>
<%@ page import="dao.FuncionarioDAO" %>

<html>
    <head>
        <title>Editar Funcionário</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-3">Editar Funcionário</h1>

            <%
                int funcionarioId = Integer.parseInt(request.getParameter("id"));
                Funcionario funcionario = new FuncionarioDAO().getFuncionarioById(funcionarioId);

                if (funcionario != null) {
            %>
            <form method="post" action="funcionario">
                <input type="hidden" name="action" value="atualizar" />
                <input type="hidden" name="id" value="<%= funcionario.getId() %>" />

                <div class="mb-3">
                    <label for="nome" class="form-label">Nome:</label>
                    <input type="text" id="nome" name="nome" value="<%= funcionario.getNome() %>" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label for="login" class="form-label">Login:</label>
                    <input type="text" id="login" name="login" value="<%= funcionario.getLogin() %>" class="form-control" required />
                </div>

                <div class="mb-3">
                    <label for="senha" class="form-label">Senha:</label>
                    <input type="password" id="senha" name="senha" value="<%= funcionario.getSenha() %>" class="form-control" required />
                </div>

                <input type="submit" value="Atualizar" class="btn btn-primary" />
            </form>
            <% } else { %>
            <p>O funcionário não foi encontrado.</p>
            <% } %>

            <a href="gerenciarFuncionarios.jsp" class="btn btn-link mt-3">Voltar</a>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>