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

public class ChatRestControllerTest extends AbstractRestControllerTest {

    @Test
    public  void chatCreateChat() throws Exception{
        getMockMvc().perform(post("/chat/createChat")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id_user1","test@gmail.com")
                        .param("id_user2","ciccio"))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public  void getAllChat() throws Exception{
        getMockMvc().perform(get("/chat/getAllChat")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id_user1","test@gmail.com"))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public  void sendNewMessage() throws Exception{
        getMockMvc().perform(post("/chat/sendMessage")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id_user1","test@gmail.com")
                        .param("id_user2", "ciccio")
                        .param("sender","1")
                        .param("messaggio","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public  void sendNewMessageWithOutReciver() throws Exception{
        getMockMvc().perform(post("/chat/sendMessage")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id_user1","test@gmail.com")
                        .param("id_user2", "")
                        .param("sender","0")
                        .param("messaggio","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public  void sendNewMessageWithOutSender() throws Exception{
        getMockMvc().perform(post("/chat/sendMessage")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id_user1","")
                        .param("id_user2", "test@gmail.com")
                        .param("sender","1")
                        .param("messaggio","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public  void createNewGroupChat() throws Exception{
        getMockMvc().perform(post("/chat/createGroupChat")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("name","Nome del Gruppo della Chat")
                        .param("id", "test@gmail.com"))
                .andExpect(status().isOk()).andReturn();
    }


    @Test
    public  void returnAllGroupChatForOneUser() throws Exception{
        getMockMvc().perform(get("/chat/getGroupChat")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id", "1d2e3894-0fd6-4eb6-bb6d-22ba4090e4a6"))
                .andExpect(status().isOk()).andReturn();
    }


    //Errore id in nessun gruppo
    @Test
    public  void returnAllGroupChatButUserDonTHave() throws Exception{
        getMockMvc().perform(get("/chat/getGroupChat")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id", "test@gmail.com"))
                .andExpect(status().isBadRequest());
    }


    //Assenza id
    @Test
    public  void returnAllGroupChatWithOutId() throws Exception{
        getMockMvc().perform(get("/chat/getGroupChat")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id", ""))
                .andExpect(status().isBadRequest());
    }


    @Test
    public  void sendMessageInGroupChat() throws Exception{
        getMockMvc().perform(post("/chat/sendGroupMessage")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id", "test@gmail.com")
                        .param("id_user","ciccio")
                        .param("messaggio", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public  void sendMessageInGroupChatWithOutIdUser() throws Exception{
        getMockMvc().perform(post("/chat/sendGroupMessage")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id", "test@gmail.com")
                        .param("id_user","")
                        .param("messaggio", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."))
                .andExpect(status().isForbidden());
    }
}
