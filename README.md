# Day2EJColores

MENÚ: Pantalla de banderes amb diverses opcions

LOGIN: és l'única opció que es pot escollir sense estar loguejat i porta a la pantalla de login, on es pot escollir si autentitzar
un usuari existent, entrar amb twitter o accedir al registre d'un usuari nou. Fa uns dies es va veure una mena de bug extrany que 
no permetía clicar al botó de twitter (no s'havia alterat el codi de fabric). Es va arreglar sol (sense canviar res).
        REGISTER: Per crear un usuari nou
        Secret: A la icona del corb a la base de dades, si es pulsa 4 cops hauria de sortir un TextEdit on al posar "raiz"
        aparegués una vista amb tots els usuaris i contrasenyes. Però hi ha errors i la recycleView no funciona bé. (Després
        de donar-li moltes voltes vaig decidir posposar-ho per tenir temps de fer altres coses).

La resta d'activities accedibles des de menú tenen totes un menú on es pot fer Logout que retorna al menú principal.

CALCULADORA: A sota de login trobem la calculadora, en la qual podem accedir al menú d'opcions per trucades o per navegar per
internet. També fa càlculs amb nombres enters. Botons especials: PI entra el nombre pi amb els decimals truncats. ANS entra
l'últim nombre que s'ha obtingut com a resultat (al fer = o quan es fan cadenes de operacions va calculant per tenir només 
dos nombres).

COUNT TEST: exercici de AsyncTasks que conta fins a 10 en un thread secundari.

A la part superior:

CORXERES: Accès al reproductor. Només consta d'una canço pertanyent a la app.

ROSA DELS VENTS: Accès a l'activity GPS. Codi donat pels professors. Crec que quan no troba l'adreça dona error.

Enmig tenim un missatge de status del Login i un botó per actualitzar-lo.

MEMORY: Accés al joc de memòria. No consta de ranking. Hi ha comentat un intent de girar les cartes en un thread secundari
però donava errors per no poder tractar les ImageViews des de un thread diferent del principal, que és on ho vaig haver
d'implementar al final.
