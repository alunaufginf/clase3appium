Feature: Whatsapp

  @Regression @SmokeTest @AcceptanceTest @BVT
  Scenario:  COMO administrador
             QUIERO enviar y recibir videos grabados
             PARA QUE los usuario tengan una mejor

    Given yo tengo una cuenta en whatsappFree
    When yo grabo un video en 2080 p
    And yo edito el vieo
    Then yo envio el video a otro "administrado"
    And el usuario deberia de reproducir el video