package com.nttdata.page;

import org.openqa.selenium.By;

public class InventoryPage {

    //Titulo de inventario
    public static By productsTitle = By.cssSelector("span.title");

    //Lista de items en inventario
    public static By itemsCards = By.cssSelector("div.inventory_item");

    //Boton añadir item especifico
    public static By addToCardButton = By.id("add-to-cart-sauce-labs-bike-light");

    //Icono para acceder al carro de compras
    public static By linkCart = By.id("shopping_cart_container");
}
