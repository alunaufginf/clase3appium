package runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MyStepdefs {

    /**
     *  {}  cualquier cadena
     *  {int} cualquier dato numerico
     *  {string} cualquier cadena ----> "valor valor"
     */

    @Given("yo tengo una cuenta en {}")
    public void yoTengoUnaCuentaEnWhatsapp(String nombreApp) {
        System.out.println("cuenta "+nombreApp);
    }

    @When("yo grabo un video en {int} p")
    public void yoGraboUnVideoEnP(int tamanioDelVideo) {
        System.out.println("tamanio del video "+tamanioDelVideo);
    }

    @And("yo edito el vieo")
    public void yoEditoElVieo() {
        System.out.println("edicion");
    }


    @And("el usuario deberia de reproducir el video")
    public void elUsuarioDeberiaDeReproducirElVideo() {
        System.out.println("reproducir video");
    }


    @Then("yo envio el video a otro {string}")
    public void yoEnvioElVideoAOtro(String persona) {
        System.out.println("envio  video "+persona);
    }
}
