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

public class ReviewRestControllerTest extends AbstractRestControllerTest{

    @Test
    public  void successInsertReview() throws Exception{
        getMockMvc().perform(post("/recensione/insertRecensione")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("idRecensore","5prova")
                        .param("idUserRecensito","8b156556-0b43-437b-910b-d14b0253b1ff")
                        .param("idNotaRecensita","d4d84d8a-d2bd-4927-ac70-63a12e49617a")
                        .param("title","TestBE")
                        .param("testo","TestBE"))
                .andExpect(status().isOk()).andReturn();
    }

    /*
    se si facesse anche un controllo backend
    @Test
    public  void unsuccessInsertReview() throws Exception{
        getMockMvc().perform(post("/recensione/insertRecensione")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("idRecensore","5prova")
                        .param("idUserRecensito","fakeid")
                        .param("idNotaRecensita","fakeid")
                        .param("title","TestBEError")
                        .param("testo","TestBEError"))
                .andExpect(status().isBadRequest());
    }*/
}
