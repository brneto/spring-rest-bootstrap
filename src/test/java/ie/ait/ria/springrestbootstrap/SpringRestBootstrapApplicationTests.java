package ie.ait.ria.springrestbootstrap;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import ie.ait.ria.springrestbootstrap.domain.Demo;
import ie.ait.ria.springrestbootstrap.repository.DemoRepository;
import java.net.URI;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class SpringRestBootstrapApplicationTests {

  @Test
  void shouldContextLoads(@Autowired DemoRepository demoRepository) {
    then(demoRepository).isNotNull();
  }

  @Test
  void shouldHaveNoDemoWithGetAll(@Autowired WebTestClient webClient) {
    webClient
        .get()
        .uri("/api/demos/")
        .accept(APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Demo.class)
        .hasSize(0);
  }

}
