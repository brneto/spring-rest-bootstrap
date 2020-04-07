package ie.ait.ria.springrestbootstrap.service;

import ie.ait.ria.springrestbootstrap.domain.Demo;
import ie.ait.ria.springrestbootstrap.repository.DemoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

  private final DemoRepository repository;

  DemoService(DemoRepository repository) { this.repository = repository; }

  public List<Demo> getDemoList() { return repository.findAll(); }

  public DemoService addDemo(Demo demo) {
    if (repository.findAllByTitle(demo.getTitle()).isEmpty())
      repository.save(demo);
    else
      throw new IllegalStateException("You cannot create more than one demo with the same title");

    return this;
  }

}
