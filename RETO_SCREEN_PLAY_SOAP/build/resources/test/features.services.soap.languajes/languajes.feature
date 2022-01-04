Feature: Busqueda de lenguaje por codigo ISO
  Como usuario del buscador
  necesito validar que la funcionalidad de busqueda de un idioma medidiante su codigo ISO trabaje adecuadamente
  para poder tener seguridad en las operaciones.

    Scenario: Buscar un idioma por su codigo ISO
    Given que el usuario del buscador ha definido el codigo ISO "es"
    When el usuario del buscador ejecuta el servicio
    Then el ususario debería obtener el resultado "Spanish"

  Scenario: Codigo ISO incorrecto
    Given que el usuario del buscador ha definido el codigo ISO "esp"
    When el usuario del buscador ejecuta el servicio
    Then el ususario debería obtener el resultado "Language ISO Code not found!"