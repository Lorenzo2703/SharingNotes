package com.sharingnotes.security;

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

public class AuthenticationRestControllerTest extends AbstractRestControllerTest {

   @Test
   public void successfulAuthenticationWithUser() throws Exception {
      getMockMvc().perform(post("/login/authenticate")
         .contentType(MediaType.APPLICATION_JSON_VALUE)
              .param("email","test@gmail.com")
              .param("password","marco"))
         .andExpect(status().isOk());
   }


   @Test
   public void unsuccessfulAuthenticationWithWrongPassword() throws Exception {
      getMockMvc().perform(post("/login/authenticate")
                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                      .param("email","test@gmail.com")
                      .param("password","ciccio"))
              .andExpect(status().isForbidden());
   }

}
