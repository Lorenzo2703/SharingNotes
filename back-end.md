# Back-end

## Back-end

### Pattern MVC (Model-View-Controller)

**L'MVC è un pattern** utilizzato per suddividere e organizzare il codice del programma in parti ben distinte. In particolare:

* **Model:** contiene i metodi e gli oggetti per l'accesso ai dati.
* **View:** il suo compito è quello di visualizzare i dati all'utente e ne gestisce l'interazione tra UI e backend.
* **Controller**: si occupa di gestire le richieste da parte della view e inviare le corrispondenti risposte in relazione ai dati ricevuti ed ai dati del model.

#### Architettura 3-tier

Nel caso specifico si è scelto di dividere la parte di view da quella di model e controller. È una scelta che offre una grande flessibilità, in quanto permette di separare la logica e le chiamate al database, dalla logica di visualizzazione.

Questa struttura oltre a fornire una maggiore organizzazione, risolve il problema di creare una applicazione monolitica.

Le tre principali parti che compongono questa architettura sono:

* **Front-End**: Realizzazione di una Single Page Application. Gestisce esclusivamente la parte di visualizzazione, inoltra le richieste dei dati al backend, riceve le risposte e mostra correttamente i dati.
* **Back-End**: Ha il compito di gestire l'autenticazione degli utenti, gestire le richieste in arrivo da parte del frontend, e di instradare le query verso il database. Inoltre gestisce la logica della piattaforma.
* **Database**: Il database non relazionale si occupa di mantenere tutti i dati che servono all'applicazione per funzionare. Verrà consultato esclusivamente dal backend.

#### Spring Boot

Spring Boot è una soluzione per il framework Spring di Java, che è stato rilasciato nel 2012 e **riduce la complessità di configurazione di nuovi progetti Spring**. A questo scopo, Spring Boot definisce una configurazione di base che include le linee guida per l'uso del framework e tutte le librerie di terze parti rilevanti, rendendo quindi l'avvio di nuovi progetti il più semplice possibile.

* Si è scelto di utilizzare Spring Boot, in quanto offre un ulteriore livello di astrazione rispetto a Spring Framework, di più complicato utilizzo e adatto a progetti aziendali più elaborati. Spring Boot ha numerosi vantaggi tra cui:
  * Incorporare direttamente applicazioni web server/container come Apache Tomcat, per cui non è necessario l'uso di file WAR (Web Application Archive);
  * Configurazione di Maven semplificata grazie ai POM "Starter" (Project Object Models);
  * Caratteristiche non funzionali come metriche o configurazioni esterne automatiche.

#### Angular

Angular è un framework JavaScript per creare interfacce utente. In particolare viene utilizzato per realizzare la parte del Frontend, ovvero la "V" del pattern MVC. Si occupa di gestire tutte le interazioni con l'utente, e visualizzare i dati corretti che provengono dal backend.

#### MongoDb

MongoDB è un DBMS non relazionale, orientato ai documenti. Classificato come un database di tipo NoSQL, MongoDB si allontana dalla struttura tradizionale basata su tabelle dei database relazionali in favore di documenti in stile JSON con schema dinamico, rendendo l'integrazione di dati di alcuni tipi di applicazioni più facile e veloce.

#### La struttura logica

I packages principali sono:

* I Controller: si occupano di fornire l'end-point, di chiamare il service di riferimento e restituire il risultato
* I Model: sono le classi su cui sono strutturati i diversi oggetti
* I Services: eseguono i metodi e passano la risposta ai controller

![Untitled](<.gitbook/assets/Untitled (12).png>)

### Class Diagram

Il Class Diagram mostra le classi che compongono il software e come esse si relazionano l’una con l’altra. Il diagramma in figura mostra le classi che costituiscono i “model” del progetto. Per questioni di visibilità grafica non sono stati riportati i costruttori e i metodi all’interno delle singole classi.

![Untitled](<.gitbook/assets/Untitled (1).png>)

### Metodi di scambio dati con MongoDb

Tramite queste funzioni è possibile accedere al database per leggere, inserire, cancellare, aggiornare i documenti. Il tutto rende possibile il caricamento e il salvataggio degli aggiornamenti.

![Untitled](<.gitbook/assets/Untitled (2) (1).png>)

### L'inserimento di un file

L'inserimento di un file nella piattaforma è affidato al **File controller** che prende come parametri il file, il titolo, la descrizione, l'id dello user, la categoria e li passa al metodo nel file service.

![Untitled](<.gitbook/assets/Untitled (3) (1).png>)

**Il file service** chiama il metodo di upload sul cloud e restituisce "Success" se l'upload è andato a buon fine.

![Untitled](<.gitbook/assets/Untitled (4) (1).png>)

**Il metodo uploadFile** utilizza la libreria Unirest, effettua una post di inserimento su Mega e contemporaneamente del record sul database Mongo.

![Untitled](<.gitbook/assets/Untitled (5).png>)

#### Sequence Diagram di un inserimento file:

![Untitled](<.gitbook/assets/Untitled (9) (1).png>)

### Il testing

La parte di testing è volta a certificare che tutte le funzioni e le api funzionino e la conseguente reazione al fallimento. Infatti con l'utilizzo della **libreria JUnit** è possibile simulare delle chiamate Mock, testando sia l'inserimento di parametri giusti e aspettati con conseguente risposta di successo e sia con input errati e inattesi, verificando in questo caso la restituzione di eccezioni.

![Untitled](<.gitbook/assets/Untitled (6) (1).png>)

#### Un esempio di test

Questa è la classe incaricata di testare la funzione di login di un utente

#### Chiamata corretta:

1. Si effettua il test di una chiamata corretta alla Api di autenticazione
2. Si inseriscono i parametri di email e password corretti
3. Si verifica che la risposta restituisca 200OK

#### Chiamata errata:

1. Si effettua il test di una chiamata errata alla Api di autenticazione
2. Si inseriscono i parametri di email e password errati
3. Si verifica che la risposta restituisca 403 Forbidden

![Untitled](<.gitbook/assets/Untitled (7) (1).png>)

## Le API

La documentazione ha l'obiettivo di mostrare le chiamate API di cui si compone questo applicativo RESTful progettato per restituire dati (JSON) che verranno inviati dai controller direttamente tramite la risposta HTTP. Il base url è lo stesso per tutte le chiamate.

#### La lista degli end-point e i parametri:

* Login
  * /login/authenticate (POST: Autenticazione di un utente, **email + password**)
* Register
  * /register/register (POST: Registrazione di un nuovo utente, **name + email + password**)
* File
  * /file/getFiles (GET: get di tutti i document in una collection, **collection**)
  * /file/getUrlFiles (GET: get dell'url di mega del file)
  * /file/fileUpload (POST: Upload del pdf su Mega, **file + title + description + id + categoria**)
  * /file/delete (DELETE: elimina il document dalla collection, **id + collection**)
* User
  * /user/getUserByID (GET: Ritorna il document dell'user cercato in base all'id, **id**)
* Chat
  * /chat/createChat (POST: Creazione di una nuova chat singola, **user1 + user2**)
  * /chat/getAllChat (GET: Restituisce tutte le chat in cui è presente l'user, **user1**)
  * /chat/sendMessage (POST: Invio messaggio nella chat, **user1 + user2 + sender + messaggio**)
  * /chat/sendGroupMessage (POST: Invio messaggio nella chat di gruppo, **id + user + messaggio**)
  * /chat/createGroupChat (POST: Creazione di una chat di gruppo, **name + id**)
  * /chat/getGroupChat (GET: Restituisce le chat di gruppo in cui è presente l’user, **id**)
* Request
  * /request/insertRichiesta (POST: Crea una nuova richiesta, **idRichiedente + title + testo**)
  * /request/completeRequest (POST: Richiesta aperta diventa completata, **bool + id + collection**)
* Review
  * /recensione/insertRecensione (POST: Inserisce una nuova recensione, **idRecensore + idUserRecensito + idNotaRecensita + title + testo**)
* Score
  * /score/updateScore (POST: Modifico lo score complessivo della nota o dell'utente, **score + id + collection**)
  * /score/insertIdVotati (POST: Inserisco l'id della nota o dell'user che ho votato alla lista degli id di chi ho votato nel mio document (**id\_votato + id**)

## Testing

Il testing sfrutta la piattaforma JUnit, I Test d’integrazione per l’applicazione sono contenuti all’interno del package: src/test/java/com/sharingnotes.

#### I metodi principali della classe Assertions sono:

* assertTrue() verifica che la condizione passata nelle parentesi tonde sia vera;&#x20;
* assertFalse() verifica che la condizione passata nelle parentesi tonde sia falsa;&#x20;
* assertNull() verifica che la condizione passata nelle parentesi tonde ritorni null;&#x20;
* assertNotNull() verifica che la condizione passata nelle parentesi tonde non ritorni null;&#x20;
* assertThat() verifica che il valore atteso sia uguale al valore effettivo;&#x20;
* assertContains() verifica che un array contiene un valore;&#x20;
* assertNotContains() verifica che un array non contiene un valore;

![](<.gitbook/assets/Untitled (8) (1).png>)
