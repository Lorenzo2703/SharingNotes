package com.sharingnotes;


import com.sharingnotes.Model.Notes;
import com.sharingnotes.Model.User;
import com.sharingnotes.MongoDb.MongoDb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class MainTest {
    public static void main(String[] args) {
        SpringApplication.run(MainTest.class, args);
        init();
    }

    @RequestMapping("/hi")
    public String Ciao(){
        return "<h1>CIAO</h1>";
    }

    public static void init(){
        MongoDb mongo=new MongoDb();

        /* Creazione della collection notes e inserimento di un appunto test
           mongo.createCollection("notes");
           insertNote(mongo,new Notes(1,"test","description test","url:...","url:..."));*/

        /* test insert di un documento nella collection degli utenti con nome test e id 1
           insertUser(mongo, new User(1,"test","test@gmail.com","12345"));
         */
    }

    public static void insertUser(MongoDb mongo, User user){
        mongo.insertUser(user,"utenti");
    }

    public static void insertNote(MongoDb mongo, Notes notes){
        mongo.insertNotes(notes,"notes");
    }
}

