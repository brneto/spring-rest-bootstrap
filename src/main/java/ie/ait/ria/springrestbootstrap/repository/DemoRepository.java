package ie.ait.ria.springrestbootstrap.repository;

import ie.ait.ria.springrestbootstrap.domain.Demo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Long> {

  List<Demo> findAllByTitle(String title);

}
