package shop;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
/**
 *
 * @author JRadagast
 */
public class ShoppingCart {
    private ArrayList allProducts = new ArrayList();
    
    /* Função para pegar o numero de produtos no carrinho */
    public int getNumberOfItems() {
        return allProducts.size();
    }
    
    /* Função para deletar items do carrinho de compras */
    public void deleteCartItem(String strItemIndex) {
        int iItemIndex = 0;
        try {
            iItemIndex = Integer.parseInt(strItemIndex);
            allProducts.remove(iItemIndex - 1);
        } catch(NumberFormatException nfe) {
            System.out.println("Erro deletenado item do carrinho: "+nfe.getMessage());
            nfe.printStackTrace();
        }   
    }
    
    /* Adiciona item ao carrinho */
    public void addCartItem(String itemNome, String itemDescricao,
                            String quantidade) {
        int numberofitems = 0;
        CartItems cartItem = new CartItems();
        try {
         numberofitems = Integer.parseInt(quantidade);
         if(numberofitems > 0) {          
          cartItem.setItemNome(itemNome);
          cartItem.setItemDescricao(itemDescricao);
          cartItem.setQuantidade(numberofitems);
          allProducts.add(cartItem);
         }

        } catch (NumberFormatException nfe) {
         System.out.println("Erro no parsing de string pra outros tipos: "+nfe.getMessage());
         nfe.printStackTrace();
        }
    }
    
    public void updateCartItem(String strItemIndex, String strQuantity) {
        int itemQuantidade = 0;
        int itemIndice = 0;
        CartItems cartItem = null;
        try {
         itemIndice = Integer.parseInt(strItemIndex);
         itemQuantidade = Integer.parseInt(strQuantity);
         if(itemQuantidade>0) {
          cartItem = (CartItems)allProducts.get(itemIndice-1);
          cartItem.setQuantidade(itemQuantidade);
         }
        } catch (NumberFormatException nfe) {
         System.out.println("Erro atualizando o carrinho: "+nfe.getMessage());
         nfe.printStackTrace();
        }
    }
    
    /* Override da para adicionar items no carrinho */
    public void addCartItem(CartItems cartItem) {
        allProducts.add(cartItem);
    }
    
    public ArrayList getCartItems() {
        return allProducts;
    }
    
    public void setCartItems(ArrayList allProducts) {
        this.allProducts = allProducts;
    }
    
}