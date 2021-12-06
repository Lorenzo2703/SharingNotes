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

public class RequestRestControllerTest extends AbstractRestControllerTest{

    @Test
    public  void successInsertIdVotati() throws Exception{
        getMockMvc().perform(post("/request/insertRichiesta")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("idRichiedente","IdTest")
                        .param("title","TestBE")
                        .param("testo","TestBE"))
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    public  void unsuccessCompleteRequestErrorId() throws Exception{
        getMockMvc().perform(post("/request/completeRequest")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("bool","true")
                        .param("id","IdFake")
                        .param("collection","richieste"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public  void unsuccessCompleteRequestErrorCollection() throws Exception{
        getMockMvc().perform(post("/request/completeRequest")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("bool","true")
                        .param("id","4048e8c8-eb85-4394-9798-d2ef4294dbc1")
                        .param("collection","coll"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public  void successCompleteRequest() throws Exception{
        getMockMvc().perform(post("/request/completeRequest")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("bool","true")
                        .param("id","4048e8c8-eb85-4394-9798-d2ef4294dbc1")
                        .param("collection","richieste"))
                .andExpect(status().isOk()).andReturn();
    }

}
