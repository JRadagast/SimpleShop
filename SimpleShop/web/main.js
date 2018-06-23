/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

jQuery( document ).ready(function($) {
    /* Adiciona o produto no carrinho. */
    
    $('.cart_button').on('click', function(){
        var nome = $(this).parent().find('.item_nome').html();
        var desc = $(this).parent().find('.item_desc').html();
        var quantidade = $(this).parent().find('input[name=quantidade]').val();
        var action = 'add';
        
        quantidade.toString();
        
        $.ajax({
            url: "https://localhost:8181/SimpleShop/loja",
            type: "POST",
            data: { action: action, quantidade: quantidade, desc: desc, nome: nome },
            success: function(e){
                var numItems = parseInt($('.number-cart-items').html()) + parseInt(quantidade);
                $('.number-cart-items').html(numItems);
                alert('Item adicionado com sucesso!');
                $('.finalizar').css('display','block');
            },
            error: function(e){
                console.log(e);
            }
        });
    });
    
    $('.finalizar').on('click', function(){
        var action = 'email';
        $.ajax({
            url: "https://localhost:8181/SimpleShop/loja",
            type: "POST",
            data: { action: action },
            success: function(e){
                alert('Email enviado com sucesso!');
            },
            error: function(e){
                console.log(e);
            }
        });
    });
});
