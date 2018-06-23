<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : Loja Virtual
    Author     : JRadagast
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Simple Store</title>        
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="<c:url value='/main.js' />"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value='/main.css' />">
    </head>
    <body>
        <jsp:useBean scope="session" id="cart" class="shop.ShoppingCart" />
        <header>
            <div class="store-title">
                <h1> Simple Store </h1>
            </div>
            <div class="shop-cart">
                <a href="<c:url value='/cart.jsp' />" mce_href="cart.jsp"><img src="<c:url value='/assets/img/shopping-cart.png' />" alt="Shopping Cart"/></a>
                <div class="number-cart-items"><c:out value="${cart.getNumberOfItems()}" /></div>
            </div>
        </header>
        
        <div class="shop-items">
            <h4>Lista de produtos:</h4>
            <sql:query var="product" dataSource="jdbc/sovis">
                SELECT * FROM produtos
            </sql:query>
            
            <div class="all-items">
            <c:forEach var="row" items="${product.rows}">
                <div class="items">
                    <h2 class="item_nome"><c:out value="${row.nome}" /></h2>
                    <div class="item_imagem" style=" background: url(<c:out value="${row.foto_url}" />) center center; background-size: cover;"> </div>
                    <p class="item_desc"><c:out value="${row.descricao}" /></p>
                    <h4 class="item_category">Categoria: <c:out value="${row.categoria}" /></h4>
                    <p>Quantidade: </p>
                    <input type="number" name="quantidade" value="1" min='1'>
                    
                    <div class="cart_button">Adicionar ao Carrinho</div>
                </div>                                       
            </c:forEach>
            </div>  
        </div>
                <div class="finalizar" <c:if test='${cart.getNumberOfItems() == 0}'> style="display:none;" </c:if> >Finalizar Compra</div>
    </body>
</html>
