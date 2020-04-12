package ie.ait.ria.springrestbootstrap.service;

import ie.ait.ria.springrestbootstrap.domain.Demo;
import ie.ait.ria.springrestbootstrap.repository.DemoRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

  private final DemoRepository repository;

  DemoService(DemoRepository repository) { this.repository = repository; }

  public List<Demo> getDemoList() { return repository.findAll(); }

  public Demo findDemo(Long id) {
    return repository.findById(id).orElseThrow(NoSuchElementException::new);
  }

  public Demo addDemo(Demo demo) {
    if (repository.findAllByTitle(demo.getTitle()).isEmpty())
      return repository.save(demo);
    else
      throw new IllegalStateException("This demo title already exists");
  }

}
