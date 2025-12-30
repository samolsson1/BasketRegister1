Feature: Användarregistrering på basketportalen

  Scenario: Användare registrerar sig med korrekt information
    Given att användaren öppnar registreringssidan
    When användaren fyller i giltig födelsedag
    And användaren anger sitt förnamn
    And användaren anger sitt efternamn
    And användaren skriver in e-post och bekräftar den
    And användaren väljer ett lösenord och bekräftar det
    And användaren godkänner villkoren
    And användaren skickar in formuläret
    Then ska ett konto skapas

  Scenario: Registrering misslyckas p.g.a. saknat efternamn
    Given att användaren öppnar registreringssidan
    When användaren fyller i giltig födelsedag
    And användaren anger sitt förnamn
    And användaren skriver in e-post och bekräftar den
    And användaren väljer ett lösenord och bekräftar det
    And användaren godkänner villkoren
    And användaren skickar in formuläret
    Then ska ett felmeddelande om efternamn visas

  Scenario: Registrering misslyckas p.g.a. olika lösenord
    Given att användaren öppnar registreringssidan
    When användaren fyller i giltig födelsedag
    And användaren anger sitt förnamn
    And användaren anger sitt efternamn
    And användaren skriver in e-post och bekräftar den
    And användaren fyller i två olika lösenord
    And användaren godkänner villkoren
    And användaren skickar in formuläret
    Then ska ett felmeddelande om lösenord visas

  Scenario: Registrering misslyckas p.g.a. ej godkända villkor
    Given att användaren öppnar registreringssidan
    When användaren fyller i giltig födelsedag
    And användaren anger sitt förnamn
    And användaren anger sitt efternamn
    And användaren skriver in e-post och bekräftar den
    And användaren väljer ett lösenord och bekräftar det
    And användaren skickar in formuläret
    Then ska ett felmeddelande om villkoren visas





