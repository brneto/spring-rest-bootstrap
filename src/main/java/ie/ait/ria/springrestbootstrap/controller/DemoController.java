package ie.ait.ria.springrestbootstrap.controller;

import ie.ait.ria.springrestbootstrap.domain.Demo;
import ie.ait.ria.springrestbootstrap.service.DemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Tag(name = "Demo")
@RestController
@RequestMapping("/api")
public class DemoController {

  private final DemoService service;

  DemoController(DemoService service) { this.service = service; }

  @Operation(tags = "Demo", summary = "Find Demos", description = "Rest call to return a list of Demos")
  @ApiResponses(value = @ApiResponse(
          responseCode = "200",
          description = "successful operation",
          content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)))
  @GetMapping("/demos")
  public List<Demo> getAllDemos() { return service.getDemoList(); }

  @GetMapping("/demos/{id}")
  public Demo getDemo(@PathVariable Long id) {
    try {
      return service.findDemo(id);
    } catch (NoSuchElementException ex) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provide correct Demo Id", ex);
    }
  }

  @Operation(tags = "Demo", summary = "Add a new demo")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Contact created"),
      @ApiResponse(responseCode = "400", description = "Invalid input"),
      @ApiResponse(responseCode = "409", description = "Contact already exists") })
  @PostMapping("/demos")
  public Demo addDemo(@Valid @RequestBody Demo demo) { return service.addDemo(demo); }

  @GetMapping("/demos/form")
  public String getFormDemo(@Valid Demo demo) {
    return demo.toString();
  }

  @PostMapping("/demos/form")
  public String postFormDemo(@Valid Demo demo) {
    return demo.toString();
  }

}
