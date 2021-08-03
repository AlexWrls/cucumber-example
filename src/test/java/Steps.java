import com.example.demo.entity.Client;
import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.То;
import cucumber.api.java.ru.Тогда;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.Result;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class Steps extends SpringCucumberIntegrationTest {
    private ResultActions resultActions;

    @Before
    public void seUp(){
        super.setUp();
    }
    @After
    public void tearDrown(){
        super.tearDown();
    }

    @Дано("^в таблице есть клиенты:$")
    public void в_таблице_есть_контакты(List<Client> contacts) throws NoSuchMethodException {
        doReturn(contacts).when(clientRepository)
                .findAll();
    }
    @Когда("^админ делает запрос GET (.+)$")
    public void клиент_выполнит_запрос_GET(String requestPath) throws Exception {
        resultActions = mockMvc.perform(get(requestPath));
    }

    @Тогда("^будет возвращён ответ со статусом (\\d+)$")
    public void будет_возвращён_ответ_со_статусом(int status) throws Exception {
        resultActions.andExpect(status().is(status));
    }
    @То("^тип содержимого будет (.+)$")
    public void тип_содержимого_будет(String contentType) throws Exception {
        resultActions.andExpect(content().contentType(contentType));
    }
    @То("^ответ будет содержать (\\d+) элемента$")
    public void ответ_будет_содержать_элемента(int count) throws Exception {
        resultActions.andExpect(jsonPath("$.length()").value(3));
    }

    @То("^так же ответ будет выведен в лог$")
    public void также_ответ_будет_выведен_в_лог() throws Exception {
        resultActions.andDo(print());
    }
}
