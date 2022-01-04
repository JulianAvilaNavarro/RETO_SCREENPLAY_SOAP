Feature: Operacion conversion celsius a fahrenheit
  Como usuario del conversor
  necesito validar que la funcionalidad de conversion de grados celsius a fahrenheit trabaje adecuadamente
  para poder tener seguridad en las operaciones.

  Scenario: Convertir una temperatura de celsius a fahrenheit
    Given que el usuario del conversor ha definido la temperatura 25 grados celsius
    When el usuario del conversor ejecuta el cálculo
    Then el ususario debería obtener el resultado 77

  Scenario: Error de conversion
    Given que el usuario de la conversor ha definido la temperatura 25C grados celcius
    When el usuario del conversor ejecuta el cálculo
    Then el ususario debería obtener el resultado "Error"