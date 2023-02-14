package com.nttdata.steps;

import com.nttdata.page.CartPage;
import com.nttdata.page.InventoryPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartSteps {

    private WebDriver driver;

    //contrsuctor
    public CartSteps(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Retorna la cantidad de items en el carro de compras
     * @return la cantidad de items en el carro de compras
     */
    public int getItemSize(){
        List<WebElement> items = this.driver.findElements(CartPage.itemsCart);
        return items.size();
    }

    /**
     * Retorna titulo del item en posicion "0" dentro del carro
     * @return titulo del item en posicion "0" dentro del carro
     */
    public String validarItemAgregado(){
        List<WebElement> items = this.driver.findElements(CartPage.itemsCart);
        return items.get(0).findElement(CartPage.itemName).getText();

    }

}
