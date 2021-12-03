package com.sharingnotes.rest;

import com.sharingnotes.util.AbstractRestControllerTest;
import kong.unirest.Body;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRestControllerTest extends AbstractRestControllerTest{

    @Test
    public  void getUserById() throws Exception{
        getMockMvc().perform(get("/user/getUserByID")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("_id","8b156556-0b43-437b-910b-d14b0253b1ff"))
                .andExpect(status().isOk()).andReturn();
    }

    /*
    Se dovesse ritornare un errore
    @Test
    public  void getUserByIdDontExist() throws Exception{
        getMockMvc().perform(get("/user/getUserByID")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .param("_id",""))
                .andExpect(status());
    }
    */


}
