---
cover: >-
  https://images.unsplash.com/photo-1532012197267-da84d127e765?crop=entropy&cs=srgb&fm=jpg&ixid=MnwxOTcwMjR8MHwxfHNlYXJjaHw1fHxib29rfGVufDB8fHx8MTY0MDM2Njg5OQ&ixlib=rb-1.2.1&q=85
coverY: 0
---

# Sharing Notes

### Tecnologie usate:

* [Angular](https://angular.io)
* [Spring Boot](https://spring.io)
* [Flask](https://flask.palletsprojects.com/en/2.0.x/)
* [MongoDB](https://www.mongodb.com)
* [MEGA](https://mega.nz)

### Scopo del progetto

Creare una piattaforma online di condivisione appunti universitari in formato PDF, dando la possibilità di registrarsi/autenticarsi al servizio in modo da poter creare una community in grado di richiedere e scaricare file, votare documenti e altri utenti e scambiare messaggi per rendere la comunicazione rapida ed efficace.

#### Prerequisiti

*   Node

    ```
    install (https://nodejs.org/it/download/)
    ```
*   Angular

    ```
    npm install -g @angular/cli
    ```
*   Java

    ```
    https://javadl.oracle.com/webapps/download
    ```
*   Python

    ```
    https://www.python.org/ftp/python/3.10.0
    ```
*   Flask

    ```
    pip install Flask
    ```
*   Flask cors

    ```
    pip install Flask-cors
    ```
*   Mega.py

    ```
    pip install Mega.py
    ```

#### Installazione:

1. Clona la repository

```
git clone https://github.com/Lorenzo2703/SharingNotes.git
```

### Configurazione:

1. Dalla cartella "python" esegui il server flask
2. Dalla cartella "backend" esegui la springboot application
3. Dalla cartella "frontend" esegui l'applicazione Angular

### Funzionalità:

1. Registrare/autenticare al servizio
2. Selezionare la nota d'interesse per leggerne la descrizione
3. Scaricare la nota
4. Recensire la nota e l'autore
5. Caricare una nuova nota
6. Filtrare e ricercare i documenti
7. Sistema di chat tra utenti e a gruppi di utenti
8. Sistema di richiesta appunti
9. Categorizzare i file per materia
10. Classificare in base alle votazioni

## Fase di Progettazione

**Oggetti di Dominio e Classi:**

* User
* Recensione
* Richiesta
* Nota
* Chat
* Chat di gruppo

**Casi d'uso:**

* Registrazione
* Login
* Visualizzare le note
  * Scaricare il documento
  * Recensire il documento
  * Aggiungere una valutazione della nota
* Inserire un nuovo documento
* Categorizzare le note
* Creare una richiesta
* Eliminare una nota caricata
* Eliminare una recensione
* Segnare una richiesta come completata
* Creare una chat singola
* Eliminare una chat singola
* Creare una chat di gruppo
* Votare un utente
* Visualizzare una classifica dei migliori utenti e delle migliori note

#### Librerie

* [Bootstrap](https://getbootstrap.com)
* [Angular Material](https://material.angular.io)
* [Mega.py](https://pypi.org/project/mega.py/)
* [Unirest](http://kong.github.io/unirest-java/)
