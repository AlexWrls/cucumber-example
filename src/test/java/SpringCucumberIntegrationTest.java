import com.example.demo.DemoApplication;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.restdocs.ManualRestDocumentation;

@SpringBootTest
@ContextConfiguration(classes = DemoApplication.class)
@AutoConfigureRestDocs(outputDir = "target/gen-doc")
public class SpringCucumberIntegrationTest {
    @Autowired
    @MockBean
    ClientRepository clientRepository;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private ManualRestDocumentation restDocumentation;
    MockMvc mockMvc;

    public void setUp() {
        restDocumentation = new ManualRestDocumentation("target/generated-snippets");

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();

        restDocumentation.beforeTest(Steps.class, "setUp");
    }

    public void tearDown() {
        restDocumentation.afterTest();
    }

}
