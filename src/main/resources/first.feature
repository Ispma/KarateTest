Feature: get city on ip

  Background:
    * url 'https://suggestions.dadata.ru'

  Scenario: first try
    Given path 'suggestions/api/4_1/rs/detectAddressByIp'
    And header Accept = "application/json"
    And header Authorization = "Token 3a7a25d6d5f2b2d5a9729c1f6321a99f32860dd0"
    And param ip = '46.226.227.20'
    When method GET
    Then status 200
    And match response.location.value == 'г Краснодар'