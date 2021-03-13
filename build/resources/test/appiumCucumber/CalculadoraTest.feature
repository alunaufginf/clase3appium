Feature: Calculadora App

  @Calculadora
  Scenario: Como un usuario simple
            Quiero sumar dos numeros
            Para poder obtener el resulado correcto

    Given yo tengo abierto mi calculadora app
    When yo presiono el numero 2
    And yo presiono el boton suma
    And yo presiono el numero 9
    And yo presiono el boton igual
    Then el resultado deberia ser "11"