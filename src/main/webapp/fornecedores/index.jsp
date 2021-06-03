<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<c:if test="${empty fornecedores && empty mensagem}">
    <jsp:forward page="listar" />
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro Fornecedores DPW</title>
        <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
        <!-- ARQUIVOS .js NECESSARIOS PARA DATATABLES -->
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/jquery.dataTables.min.js" integrity="sha256-d0qcJpwLkJL+K8wbZdFutWDK0aNMgLJ4sSLIV9o4AlE=" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.24/js/dataTables.bootstrap.min.js" integrity="sha256-H/ZJHj902eqGocNJYjkD3OButj68n+T2NSBjnfV2Qok=" crossorigin="anonymous"></script>
        <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
        <!-- ARQUIVOS .css NECESSARIOS PARA DATATABLES -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.24/css/dataTables.bootstrap.min.css">
        <!-- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -->
        <!-- SCRIPT PARA CARREGAR DATATABLES EM PORTUGUES -->
        <script type="text/javascript">
            $(document).ready(function () {
                $('#fornecedores').DataTable({
                    "language": {
                        "url": "https://cdn.datatables.net/plug-ins/1.10.24/i18n/Portuguese-Brasil.json"
                    }
                });
            });
        </script>
    </head>
    <body>
        <!-- - - - - - - - - -  ESSE É O INDEX DE FORNECEDORES - - - - - - - - - -->
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="../index.jsp">Cadastrar Fornecedor DPW</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.jsp">Fornecedores</a></li>
                    <li><a href="../contatos/index.jsp">Contatos</a></li>
                    <li><a href="../produtos/index.jsp">Produtos</a></li>
                    <li><a href="../cotacoes/index.jsp">Cotações</a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav>
    <div class="container">
        <form class="form-horizontal" action="inserir">
            <div class="form-group">
                <label class="control-label col-sm-2" for="razaoSocial">Razão Social:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="razaoSocial" placeholder="Digite a razao social">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="nomeFantasia">Nome Fantasia:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="nomeFantasia" placeholder="Digite o nome fantasia">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="cnpj">CNPJ:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="cnpj" placeholder="Digite o CNPJ">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Salvar</button>
                </div>
            </div>
        </form>
        <c:choose>
            <c:when test="${not empty fornecedores}">
                <div style="width: 600px">
                    <table class="table" id="fornecedores">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Razao Social</th>
                                <th>Nome Fantasia</th>
                                <th>CNPJ</th>
                                <th>Editar</th>
                                <th>Excluir</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="fornecedor" items="${fornecedores}">
                                <tr>
                                    <td>${fornecedor.id}</td>
                                    <td>${fornecedor.razaoSocial}</td>
                                    <td>${fornecedor.nomeFantasia}</td>
                                    <td>${fornecedor.cnpj}</td>
                                    <!--<td><a href="">editar</a></td>-->
                                    <td><a href="editar?id=${fornecedor.id}"><img src="../imagens/editar_registro.png"></a></td>
                                    <td><a href="excluir?id=${fornecedor.id}"><img src="../imagens/deletar_registro.png"></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>

                    </table>
                </div>
            </c:when>
            <c:when test="${empty fornecedores}">
                    <h2 style="color: red">Não há fornecedores cadastrados</h2>
            </c:when>
        </c:choose>
            <c:if test="${not empty mensagem}">
                    <h2 style="color: red">${mensagem}</h2>
            </c:if>
    </div>
</body>
</html>
