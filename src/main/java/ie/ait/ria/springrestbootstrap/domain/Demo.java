package ie.ait.ria.springrestbootstrap.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@Schema(description = "Class representing a demo in the application.")
@Entity
@Validated
public class Demo {

  @Schema(
      description = "Unique identifier of the demo.",
      example = "1",
      required = true)
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Schema(
      description = "Title of the demo.",
      example = "Jessica Abigail",
      required = true)
  @NotBlank
  private String title;

  @Schema(
      description = "Description of the demo.",
      example = "bla bla bla")
  @Lob
  private String description;

  public long getId() { return id; }
  public String getTitle() { return title; }
  public String getDescription() { return description; }

  public Demo setId(long id) {
    this.id = id;
    return this;
  }

  public Demo setTitle(String title) {
    this.title = title;
    return this;
  }

  public Demo setDescription(String description) {
    this.description = description;
    return this;
  }

  @Override
  public String toString() {
    var template = "[id: %d, title: %s, description: %s]";
    return String.format(template, id, title, description);
  }
}
