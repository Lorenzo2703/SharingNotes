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

public class ScoreRestControllerTest extends AbstractRestControllerTest{

    @Test
    public  void successUpdateScore() throws Exception{
        getMockMvc().perform(post("/score/updateScore")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("score","5")
                        .param("id","8b156556-0b43-437b-910b-d14b0253b1ff")
                        .param("collection","utenti"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public  void unsuccessUpdateScoreErrorId() throws Exception{
        getMockMvc().perform(post("/score/updateScore")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("score","5")
                        .param("id","8765")
                        .param("collection","utenti"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public  void unsuccessUpdateScoreErrorCollection() throws Exception{
        getMockMvc().perform(post("/score/updateScore")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("score","5")
                        .param("id","8b156556-0b43-437b-910b-d14b0253b1ff")
                        .param("collection","coll"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public  void successInsertIdVotati() throws Exception{
        getMockMvc().perform(post("/score/insertIdVotati")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id_votato","5prova")
                        .param("id","8b156556-0b43-437b-910b-d14b0253b1ff"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public  void unsuccessInsertIdVotati() throws Exception{
        getMockMvc().perform(post("/score/insertIdVotati")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("id_votato","5prova")
                        .param("id","fakeid"))
                .andExpect(status().isBadRequest());
    }

}
