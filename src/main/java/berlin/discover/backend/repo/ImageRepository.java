package berlin.discover.backend.repo;

import berlin.discover.backend.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByUserId(Long userId);
}
