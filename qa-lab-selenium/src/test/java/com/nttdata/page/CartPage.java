package com.nttdata.page;

import org.openqa.selenium.By;

public class CartPage {
    //lista de items dentro del carro de compras
    public static By itemsCart = By.cssSelector("div.cart_item");

    //Atributo Titulo del item
    public static By itemName = By.cssSelector("div.inventory_item_name");


}
