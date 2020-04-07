package ie.ait.ria.springrestbootstrap;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import ie.ait.ria.springrestbootstrap.domain.Demo;
import ie.ait.ria.springrestbootstrap.repository.DemoRepository;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.BasicJsonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureJsonTesters
@ActiveProfiles("test")
class SpringRestBootstrapApplicationTests {

  private final URI baseUri = URI.create("/api/demos/");
  private final Long demoIdInTest = 1L;
  private final URI demoUriInTest = baseUri.resolve(demoIdInTest.toString());
  private final Demo demo = new Demo()
      .setTitle("Call of the wild")
      .setDescription(
          "Here is the ultimate dog story, one filled with "
              + "emotion, adventure, and excitement. During the Gold Rush, "
              + "Buck is snatched away from his peaceful home and brought to the "
              + "harsh and bitter Yukon to become a sled dog."
      );

  // https://rtmccormick.com/2017/07/30/solved-testing-patch-spring-boot-testresttemplate
  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private BasicJsonTester json;

  @Test @Order(1)
  void shouldContextLoads(@Autowired DemoRepository demoRepository) {
    then(demoRepository).isNotNull();
  }

  @Test @Order(2)
  void shouldHaveNoDemoWithGetAll() {
    // given
    RequestEntity<Void> request = RequestEntity
        .get(baseUri)
        .accept(APPLICATION_JSON)
        .build();

    // when
    ResponseEntity<Collection<Demo>> response =
        restTemplate.exchange(request, new ParameterizedTypeReference<Collection<Demo>>() {});
    HttpStatus responseStatus = response.getStatusCode();
    Collection<Demo> responseContent = requireNonNull(response.getBody());

    // then
    then(responseStatus).isEqualTo(OK);
    then(responseContent).isEmpty();
  }

}
