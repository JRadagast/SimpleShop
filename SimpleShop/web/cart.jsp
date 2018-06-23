<%-- 
    Document   : ShoppingCart.jsp
    Created on : Jun 22, 2018, 5:11:48 PM
    Author     : JRadagast
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Shopping Cart</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>
 
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<p><font face="Helvetica"><strong>Shopping Cart</strong></font></p>
<p><a href="<c:url value='/store.jsp' />" mce_href="store.jsp">Loja</a></p>
<table width="75%" border="1">
  <tr bgcolor="#CCCCCC">
    <td><strong><font size="2" face="Helvetica">Item</font></strong></td>
    <td><strong><font size="2" face="Helvetica">Descrição</font></strong></td>
    <td><strong><font size="2" face="Helvetica">Quantidade</font></strong></td>
  </tr>
  <jsp:useBean id="cart" scope="session" class="shop.ShoppingCart" />
  <c:if test=" ${ cart.getNumberOfItems() == 0 } " >
  <tr>
  <td colspan="4"><font size="2" face="Helvetica">- Carrinho está vazio -<br/>
  </tr>
  </c:if>
  <c:forEach var="cartItem" items="${cart.cartItems}" varStatus="counter">
  <form name="item" method="POST" action="<c:url value='/loja' />">
  <tr>
    <td><font size="2" face="Helvetica"><b><c:out value="${cartItem.getItemNome()}"/></b><br/></td>
    <td><c:out value="${cartItem.getItemDescricao()}"/></font></td>
    <td><font size="2" face="Helvetica"><input type='hidden' name='itemIndex' value='<c:out value="${counter.count}"/>'><input type='text' name="quantity" value='<c:out value="${cartItem.getQuantidade()}"/>' size='2'> <input type="submit" name="action" value="Update">
 <br/>         <input type="submit" name="action" value="Delete"></font></td>
  </tr>
  </form>
  </c:forEach>
</table>
</body>
</html>