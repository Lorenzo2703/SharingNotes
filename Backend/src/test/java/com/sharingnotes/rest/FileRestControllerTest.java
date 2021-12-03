package com.sharingnotes.rest;

import com.sharingnotes.util.AbstractRestControllerTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FileRestControllerTest extends AbstractRestControllerTest{

    @Test
    public  void getFiles() throws Exception{
        getMockMvc().perform(get("/file/getFiles")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("collection","notes"))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public  void getUrlFiles() throws Exception{
        getMockMvc().perform(get("/file/getUrlFiles"))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public  void filUploadSuccessful() throws Exception{
        MultipartFile file = new MockMultipartFile("file", "order.pdf", "", "file contents".getBytes());
        getMockMvc().perform(MockMvcRequestBuilders.multipart("/file/fileUpload")
                        .file((MockMultipartFile) file)
                        .param("title", "CiaoSonoDiTest")
                        .param("description", "Sono il file che si caricata dal test del codice")
                        .param("userId", "1234")
                        .param("categoria","Matematica"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public  void deleteFile() throws Exception{
        getMockMvc().perform(delete("/file/delete")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("_id","0af9958c-be51-4bdb-bf48-d95291e419f3")
                        .param("collection", "notes"))
                .andExpect(status().isOk()).andReturn();
    }



    @Test
    public  void deleteFileNotExits() throws Exception{
        getMockMvc().perform(delete("/file/delete")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("_id","notes")
                        .param("collection", "notes"))
                .andExpect(status().isBadRequest());
    }

    //Test per i file se aggiungete i controlli
   /*@Test
   public  void filUploadWithFileNotPDF() throws Exception{
      MultipartFile file = new MockMultipartFile("file", "pippo.jpg", "", "file contents".getBytes());
      getMockMvc().perform(MockMvcRequestBuilders.multipart("/file/fileUpload")
                      .file((MockMultipartFile) file)
                      .param("title", "PippoPlutoPaperino")
                      .param("description", "Sono il file che si caricata dal test del codice")
                      .param("userId", "1234")
                      .param("categoria","Matematica"))
              .andExpect(status().isForbidden());
   }*/
}
