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
public class AdministradorTest {
    private URI path;
    private MockHttpServletRequest request;
    private ResultMatcher expectedResult;

    @Autowired
    private MockMvc mock;

    private String jwtToken;

    @Before
    public void setUp() throws Exception{

        String json = "{\"login\": \"camilabbreda\",  \"senha\": \"102030\"}";
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

        expectedResult = MockMvcResultMatchers.status().isOk();

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

    //-----------------------------------------------------------------------------------------------------------
    @Test
    public void testCadastrarEndereco() throws Exception{

        String jsonCadastro = "{\"cep\": \"89825000\", \"logradouro\": \"teste99\",\"numero\": \"260\",\"bairro\": \"teste99\",\"localidade\": \"teste99\",\"estado\": \"teste99\",\"latitude\": \"teste99\",\"longitude\": \"teste99\"}";

        path = new URI("/endereco");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(path)
                .content(jsonCadastro)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isCreated();

        mock.perform(request).andExpect(expectedResult);

    }

    @Test
    public void testAtualizarEndereco() throws Exception {

        String jsonAtualiza = "{\"id\": \"1\",\"cep\": \"89825000\", \"logradouro\": \"teste99\",\"numero\": \"260\",\"bairro\": \"teste99\",\"localidade\": \"teste99\",\"estado\": \"teste99\",\"latitude\": \"teste99\",\"longitude\": \"teste99\"}";

        path = new URI("/endereco/update");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(path)
                .content(jsonAtualiza)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);
    }

    @Test
    public void testRemoverEndereco() throws Exception {

        path = new URI("/endereco/");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(path)
                .param("id","2")
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);
    }

    @Test
    public void testListarEndereco() throws Exception{

        path = new URI("/endereco/todosEnderecos");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(path)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);

    }

    //-------------------------------------------------------------------------------------------------------
    @Test
    public void testCadastrarFarmacia() throws Exception{

        String jsonCadastro = "{\"razaoSocial\": \"89825000\", \"cnpj\": \"teste99\",\"nomeFantasia\": \"260\",\"email\": \"teste99\",\"telefone\": \"teste99\",\"celular\": \"teste99\",\"enderecoId\": \"1\"}";

        path = new URI("/farmacia");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(path)
                .content(jsonCadastro)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isCreated();

        mock.perform(request).andExpect(expectedResult);

    }

    @Test
    public void testAtualizarFarmacia() throws Exception {

        String jsonAtualiza = "{\"id\": \"12\",\"razaoSocial\": \"89825000\", \"cnpj\": \"teste99\",\"nomeFantasia\": \"260\",\"email\": \"teste99\",\"telefone\": \"teste99\",\"celular\": \"teste99\",\"enderecoId\": \"8\"}";

        path = new URI("/farmacia/update");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(path)
                .content(jsonAtualiza)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);
    }

    @Test
    public void testRemoverFarmacia() throws Exception {

        path = new URI("/farmacia/");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(path)
                .param("id","12")
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);
    }

    @Test
    public void testListarFarmacia() throws Exception{

        path = new URI("/farmacia/todasFarmacias");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(path)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);

    }

    //------------------------------------------------------------------------------------------
    @Test
    public void testCadastrarMedicamento() throws Exception{

        String jsonCadastro = "{\"nome\": \"zazul\", \"laboratorio\": \"teste99\",\"dosagem\": \"260\",\"descricao\": \"teste99\",\"preco\": \"12,00\",\"tipo\": \"controlado\"}";

        path = new URI("/medicamento");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(path)
                .content(jsonCadastro)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isCreated();

        mock.perform(request).andExpect(expectedResult);

    }

    @Test
    public void testAtualizarMedicamento() throws Exception {

        String jsonAtualiza = "{\"id\": \"16\", \"nome\": \"zazul\", \"laboratorio\": \"teste99\",\"dosagem\": \"260\",\"descricao\": \"teste99\",\"preco\": \"12,00\",\"tipo\": \"controlado\"}";

        path = new URI("/medicamento/update");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(path)
                .content(jsonAtualiza)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);
    }

    @Test
    public void testRemoverMedicamento() throws Exception {

        path = new URI("/medicamento/");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(path)
                .param("id","16")
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);
    }

    @Test
    public void testListarMedicamento() throws Exception{

        path = new URI("/medicamento/todosMedicamentos");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(path)
                .header("Content-Type", "application/json")
                .header("Authorization", jwtToken);

        expectedResult = MockMvcResultMatchers.status().isOk();

        mock.perform(request).andExpect(expectedResult);

    }
}
