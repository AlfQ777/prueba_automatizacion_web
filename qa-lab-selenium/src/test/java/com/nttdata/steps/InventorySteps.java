package com.nttdata.steps;

import com.nttdata.page.InventoryPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class InventorySteps {

    private WebDriver driver;

    //contrsuctor
    public InventorySteps(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Obtener el título de la pantalla de productos
     * @return el valor del título de la pantalla de productos
     */
    public String getTitle(){

        return this.driver.findElement(InventoryPage.productsTitle).getText();
    }

    /**
     * Retorna la cantidad de items
     * @return la cantidad de items
     */
    public int getItemSize(){
        List<WebElement> items = this.driver.findElements(InventoryPage.itemsCards);
        return items.size();
    }

    /**
     * Agregar nuevo item al carro de compras
     */
    public void addCard(){
        this.driver.findElement(InventoryPage.addToCardButton).click();
        //add-to-cart-sauce-labs-fleece-jacket
    }

    /**
     * Hacemos click en el icono carro de compras
     */
    public void accesoCart(){
        this.driver.findElement(InventoryPage.linkCart).click();
    }

}
