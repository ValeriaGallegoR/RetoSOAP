Feature: Como usuario de la plataforma DataFlex
  necesito validar que la funcionalidad para obtener el codigo celular de cada pais este correcta
  para ahorrar tiempo y dinero al momento de identificar el codigo celular de cada pais

  Scenario: obtener codigo indicativo correcto
    Given el usuario proporciona el codigo iso del pais que es "COL"
    When el usuario ejecuta la accion
    Then el usuario obtiene el codigo celular del pais debe ser 57

  Scenario: obtener codigo indicativo erroneo
    Given el usuario escribe "HOLA" como el codigo iso del pais que quiere identificar
    When el usuario ejecuta la accion de identificar el indicativo del pais
    Then el usuario deberá obtener como respuesta "Country not found in the database"