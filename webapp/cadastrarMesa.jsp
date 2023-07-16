<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Cadastrar Mesa</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h2 class="mt-3">Cadastrar Mesa</h2>
            <div>
                <form method="POST" action="mesa">
                    <div class="mb-3">
                        <label for="localizacao" class="form-label">Localização:</label>
                        <select id="localizacao" name="localizacao" class="form-select" required>
                            <option value="area-externa">Área Externa</option>
                            <option value="area-interna">Área Interna</option>
                            <option value="bar">Bar</option>
                        </select>
                    </div>

                    <button type="submit" class="btn btn-primary">Inserir</button>
                    <c:if test="${not empty cadastroFeito}">
                        <h2 class="mt-3">Cadastro efetuado ${cadastroFeito.id}</h2>
                    </c:if>
                </form>
            </div>

            <a href="gerenciarMesas.jsp" class="btn btn-link mt-3">Voltar</a>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>