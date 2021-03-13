Feature: Whatsapp

  # este es un comentario
  @Regression @SmokeTest @AcceptanceTest
  Scenario Outline:  COMO administrador
  QUIERO enviar y recibir videos grabados
  PARA QUE los usuario tengan una mejor

    Given yo tengo una cuenta en <applicationName>
    When yo grabo un video en <resolutionScreen> p
    And yo edito el vieo
    Then yo envio el video a otro "<user>"
    And el usuario deberia de reproducir el video


    Examples:
      |applicationName  | resolutionScreen   | user        |
      | facebook        |  1080              | admin       |
      | tiktok          | 2080               | singleUser  |
      | whatsapp        |  4080              | guest       |
      | telegram        |  8080              | share       |
