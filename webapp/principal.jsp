<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Restaurante</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1 class="mt-3">Restaurante</h1>

            <form method="post">
                <button type="submit" class="btn btn-secondary mt-3">Encerrar Sessão</button>
            </form>
            <a href="gerenciarMesas.jsp" class="btn btn-primary mt-3">Gerenciar Mesas</a>
            <a href="gerenciarFuncionarios.jsp" class="btn btn-primary mt-3">Gerenciar Funcionários</a>
            <a href="gerenciarReservas.jsp" class="btn btn-primary mt-3">Gerenciar Reservas</a>
            <a href="gerenciarFeedbacks.jsp" class="btn btn-primary mt-3">Gerenciar Feedbacks</a>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.2/js/bootstrap.bundle.min.js"></script>
    </body>
</html>