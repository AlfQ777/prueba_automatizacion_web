package com.nttdata.stepsdefinitions;

import com.nttdata.steps.InventorySteps;
import com.nttdata.steps.CartSteps;
import com.nttdata.steps.LoginSteps;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginStepsDef {

    private WebDriver driver;
    private Scenario scenario;

    private InventorySteps inventorySteps(WebDriver driver){
        return new InventorySteps(driver);
    }
    private CartSteps cartSteps(WebDriver driver){
        return new CartSteps(driver);
    }

    @Before(order = 0)
    public void setUp(){
        //setUp
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        //crear el driver
        driver = new ChromeDriver();
        //max
        driver.manage().window().maximize();
    }

    @Before(order = 1)
    public void setScenario(Scenario scenario){
        this.scenario = scenario;
    }

    @After
    public void quitDriver(){
        //driver.quit();
    }

    @Dado("que me encuentro en la página de login de Saucedemo")
    public void que_me_encuentro_en_la_página_de_login_de_sacedemo() {
        driver.get("https://www.saucedemo.com/");
        screenShot();
        System.out.println("Ubicados en el portal de login");
    }
    @Cuando("inicio sesión con las credenciales usuario: {string} y contraseña: {string}")
    public void inicio_sesión_con_las_credenciales_usuario_y_contraseña(String user, String password) {
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.typeUser(user);
        loginSteps.typePassword(password);
        loginSteps.login();
        screenShot();
        System.out.println("Logueados con exito");
    }
    @Entonces("valido que debería aparecer el título de {string}")
    public void valido_que_debería_aparecer_el_título_de(String expectedTitle) {
        String title =  inventorySteps(driver).getTitle();
        //prueba: validamos el título del producto
        Assertions.assertEquals(expectedTitle, title);
        System.out.println("Titulo: "+title+" confirmado");
    }
    @Y("también valido que al menos exista un item")
    public void también_valido_que_al_menos_exista_un_item() {
        int itemsListSize = inventorySteps(driver).getItemSize();
        //prueba: validar que al menos exista un item en inventario
        screenShot();
        Assertions.assertTrue(itemsListSize > 0, "El tamaño de la lista inventario es: " + itemsListSize);
        System.out.println("El tamaño de la lista inventario es: " + itemsListSize);
    }
    @Y("agrego un item al carrito de compras")
    public void agrego_item_al_carro_de_compras() {
        //prueba: agregar item al carro de compras
        inventorySteps(driver).addCard();
        screenShot();
        System.out.println("Item añadido con exito");
    }

    @Y("reviso que el carrito de compras tenga un item agregado")
    public void reviso_que_el_carrito_de_compras_tenga_un_item_agregado() {
        //Accedemos a la pagina del carro de compras
        inventorySteps(driver).accesoCart();
        //Obtenemos el tamaño de items registrados en el carro de compras
        int itemsListSize = cartSteps(driver).getItemSize();
        screenShot();
        //prueba: validar que al menos exista un item
        Assertions.assertTrue(itemsListSize > 0);
        System.out.println("El tamaño de la lista carro es: " + itemsListSize);
    }
    @Y("valido que el item agregado se llame {string}")
    public void valido_que_el_item_agregado_se_llame(String expectedItem) {
        //obtenemos el titulo del item agregado
        String itemRegistrado = cartSteps(driver).validarItemAgregado();
        //prueba: validar que al menos exista un item
        screenShot();
        //comparamos el titulo del item previamente registrado con el titulo de consulta
        Assertions.assertEquals(itemRegistrado, expectedItem);
        System.out.println("El item agregado es: " + itemRegistrado);

    }

    public void screenShot(){
        byte[] evidencia = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        this.scenario.attach(evidencia, "image/png", "evidencias");
    }

}
