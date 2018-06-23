package shop;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author JRadagast
 */
@WebServlet("/loja")
public class ShopServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RequestDispatcher rd = request
                .getRequestDispatcher("/store.jsp");
        rd.forward(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);    
        
        String strAction = request.getParameter("action");

        if(strAction!=null && !strAction.equals("")) {
            if(strAction.equals("add")) {
                addToCart(request);
            } else if (strAction.equals("Update")) {
                updateCart(request);
                RequestDispatcher rd = request
                        .getRequestDispatcher("/store.jsp");
                rd.forward(request,response);
            } else if (strAction.equals("Delete")) {
                deleteCart(request);
                RequestDispatcher rd = request
                        .getRequestDispatcher("/store.jsp");
                rd.forward(request,response);
            } else if (strAction.equals("email")) {
                SendMail();
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Controle pro carrinho da loja.";
    }// </editor-fold>
    
    protected void addToCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String itemNome = request.getParameter("nome");
        String itemDescricao = request.getParameter("desc");
        String itemQuantidade = request.getParameter("quantidade");

        ShoppingCart cartBean = null;

        Object objCartBean = session.getAttribute("cart");

        if(objCartBean!=null) {
         cartBean = (ShoppingCart) objCartBean ;
        } else {
         cartBean = new ShoppingCart();
         session.setAttribute("cart", cartBean);
        }

        cartBean.addCartItem(itemNome, itemDescricao, itemQuantidade);
    }
    
    protected void deleteCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String strItemIndex = request.getParameter("itemIndex");
        ShoppingCart cartBean = null;

        Object objCartBean = session.getAttribute("cart");
        if(objCartBean!=null) {
         cartBean = (ShoppingCart) objCartBean ;
        } else {
         cartBean = new ShoppingCart();
        }
        cartBean.deleteCartItem(strItemIndex);
    }
    
    protected void updateCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String strQuantity = request.getParameter("quantity");
        String strItemIndex = request.getParameter("itemIndex");

        ShoppingCart cartBean = null;

        Object objCartBean = session.getAttribute("cart");
        if(objCartBean!=null) {
         cartBean = (ShoppingCart) objCartBean ;
        } else {
         cartBean = new ShoppingCart();
        }
        cartBean.updateCartItem(strItemIndex, strQuantity);
    }
    
    public static void SendMail() {
        
        String  d_email = "remetente@gmail.com",
            d_uname = "login-gmail",
            d_password = "senha-gmail",
            d_host = "smtp.gmail.com",
            d_port  = "465",
            d_targetemail = "receptor@gmail.com";
        
        Properties props = new Properties();
        props.put("mail.smtp.user", d_email);
        props.put("mail.smtp.host", d_host);
        props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", d_port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        
        Session session = Session.getInstance(props,new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(d_uname,d_password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(d_email));
            message.setRecipients(Message.RecipientType.TO,
                                     InternetAddress.parse( d_targetemail ));

            message.setSubject("Finalizando Compra");
            message.setText("Seja bem vindo a nossa loja.\n E obrigado por comprar aqui!");
            
            Transport transport = session.getTransport("smtps");
            transport.connect(d_host, Integer.valueOf(d_port), d_uname, d_password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            
            System.out.println("sent");

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
         
    }
}