package com.sharingnotes;

import com.sharingnotes.Cloud.CloudApi;
import com.sharingnotes.Model.Notes;
import com.sharingnotes.Model.User;
import com.sharingnotes.MongoDb.MongoDb;
import kong.unirest.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RestController
@SpringBootApplication
public class MainTest {
    public static void main(String[] args) {
        SpringApplication.run(MainTest.class, args);
        init();
        //CloudApi.uploadFile(new File("C:\\Users\\gialo\\Desktop\\SharingNotes\\Backend\\certificato.pdf"));
        JSONObject json = new JSONObject(CloudApi.getAll()); // Convert text to object
        System.out.println(json.toString(5));

    }

    @GetMapping("/getAllFile")
    public String getAll(){
        return (CloudApi.getAll().toString());
    }


    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {

        CloudApi.uploadFile(convertFile(multipartFile));
        return "success";
    }


    public static void init(){
        //MongoDb mongo=new MongoDb();

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

    public static File convertFile(MultipartFile multipartFile){
        String fileName = multipartFile.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        File file = null;
        try {
            file = File.createTempFile(fileName.substring(0,fileName.lastIndexOf("."))
                    .replaceAll("[^a-zA-Z0-9-_\\.]", "_"), prefix);
            multipartFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

