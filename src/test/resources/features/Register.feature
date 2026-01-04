Feature: Användarregistrering på basketportalen

  Scenario Outline: Registrering av användare
    Given att användaren öppnar registreringssidan
    When användaren fyller i giltig födelsedag
    And användaren anger sitt förnamn
    And användaren anger sitt efternamn "<efternamn>"
    And användaren skriver in e-post och bekräftar den
    And användaren fyller i lösenord "<losenord>" och bekräftar "<bekraftaLosenord>"
    And användaren godkänner villkoren "<villkor>"
    And användaren skickar in formuläret
    Then ska resultatet vara "<meddelande>"

    Examples:
      | efternamn  | losenord     | bekraftaLosenord | villkor | meddelande             |
      | finns      | Lösenord123  | Lösenord123      | ja      | OK                     |
      | saknas     | Lösenord123  | Lösenord123      | ja      | Last Name is required  |
      | finns      | Lösenord123  | Lösenord1234     | ja      | Password did not match |
      | finns      | Lösenord123  | Lösenord123      | nej     | Terms and Conditions   |




