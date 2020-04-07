package ie.ait.ria.springrestbootstrap.controller;

import ie.ait.ria.springrestbootstrap.domain.Demo;
import ie.ait.ria.springrestbootstrap.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Demo")
@RestController
@RequestMapping("/api")
@Validated
public class DemoController {

  private final DemoService service;

  DemoController(DemoService service) { this.service = service; }

  @ApiOperation(tags = "Demo", value = "Find Demos", notes = "Rest call to return a list of Demos")
  @ApiResponses(value =
      @ApiResponse(code = 200, message = "successful operation", response = List.class))
  @GetMapping("/demos")
  public List<Demo> getAllDemos() { return service.getDemoList(); }

  @ApiOperation(tags = "Demo", value = "Add a new demo")
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Contact created"),
      @ApiResponse(code = 400, message = "Invalid input"),
      @ApiResponse(code = 409, message = "Contact already exists") })
  @PostMapping("/demos")
  public Demo addDemo(@Valid Optional<Demo> bodyContent) {
    Demo demo = bodyContent.orElseThrow(
        () -> new IllegalArgumentException("POST cannot be called without providing a payload"));

    service.addDemo(demo);
    return demo;
  }

}
