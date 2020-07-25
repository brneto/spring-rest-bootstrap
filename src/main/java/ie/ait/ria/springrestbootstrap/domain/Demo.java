package ie.ait.ria.springrestbootstrap.domain;

import static javax.persistence.GenerationType.IDENTITY;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@ApiModel(description = "Class representing a demo in the application.")
@Entity
@Validated
public class Demo {

  @ApiModelProperty(
      notes = "Unique identifier of the demo.",
      example = "1",
      required = true,
      position = 0)
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @ApiModelProperty(
      notes = "Title of the demo.",
      example = "Jessica Abigail",
      required = true,
      position = 1)
  @NotBlank
  private String title;

  @ApiModelProperty(
      notes = "Description of the demo.",
      example = "bla bla bla",
      required = false,
      position = 2)
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
