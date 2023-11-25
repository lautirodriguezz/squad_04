Feature: Como Cliente, quiero crear un ticket de soporte
  para informar problemas específicos relacionados con la implementación o proyecto.

  Scenario: Successfully withdraw money when balance is enough
    Given Creo un ticket
    When Ingreso la información requerida sobre el problema
    Then El sistema debe registrar el ticket correctamente con el estado "Abierto"
    And la informacion provista
