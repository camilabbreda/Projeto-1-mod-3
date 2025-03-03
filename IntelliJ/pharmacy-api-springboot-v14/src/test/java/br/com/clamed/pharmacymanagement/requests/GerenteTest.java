package br.com.clamed.pharmacymanagement.requests;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestEntityManager
@Transactional
public class GerenteTest {
    private URI path;
    private MockHttpServletRequest request;
    private ResultMatcher expectedResult;

    @Autowired
    private MockMvc mock;

    private String jwtToken;

    @Before
    public void setUp() throws Exception{

        String json = "{\"login\": \"jacudomato2\",  \"senha\": \"123456789\"}";
        path = new URI("/auth");
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(path)
                .contentType(MediaType.APPLICATION_JSON).content(json);
        expectedResult = MockMvcResultMatchers.status().isOk();
        String response = mock.perform(request).andExpect(expectedResult).andReturn().getResponse()
                .getContentAsString();
        JSONObject data = new JSONObject(response);
        jwtToken = data.getString("Authorization");
    }

    @Test
    public void testCadastrar() throws Exception{

        String jsonCadastro = "{ \"login\": \"julianaAlmeida\", \"senha\": \"teste99\"}";

        path = new URI("/usuario/cadastro");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(path)
                .content(jsonCadastro)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isCreated();

        mock.perform(request).andExpect(expectedResult);

    }

    @Test
    public void testAtualizar() throws Exception {

        String jsonAtualiza = "{\"id\": \"3\",  \"login\": \"usuarioatualizar6\", \"senha\": \"usuarioatualizar6\"}";

        path = new URI("/usuario/cadastro");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(path)
                .content(jsonAtualiza)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);
    }

    @Test
    public void testRemover() throws Exception {

        path = new URI("/usuario/");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(path)
                .param("id","5")
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isForbidden();

        mock.perform(request).andExpect(expectedResult);
    }

    @Test
    public void testListar() throws Exception{

        path = new URI("/usuario/username");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(path)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);

    }
}
