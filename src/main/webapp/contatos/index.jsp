<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
    </head>
    <body>
        <!-- - - - - - - - - -  ESSE É O INDEX DE CONTATOS - - - - - - - - - - - -->
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="../index.jsp">Cadastrar Fornecedor DPW</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="../fornecedores/index.jsp">Fornecedores</a></li>
                    <li class="active"><a href="index.jsp">Contatos</a></li>
                    <li><a href="../produtos/index.jsp">Produtos</a></li>
                    <li><a href="../cotacoes/index.jsp">Cotações</a></li>
                </ul>

            </div><!-- /.navbar-collapse -->
        </div><!-- /.container -->
    </nav>
    <div class="container">
        <form class="form-horizontal" action="">
            <div class="form-group">
                <label class="control-label col-sm-2" for="nome">Nome:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="nome" placeholder="Digite o nome do contato">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="email">Email:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="email" placeholder="Digite o email">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="fone">Telefone:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="fone" placeholder="Digite o telefone">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Salvar</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
