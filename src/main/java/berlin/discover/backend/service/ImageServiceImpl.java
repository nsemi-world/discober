package berlin.discover.backend.service;

import berlin.discover.backend.model.Image;
import berlin.discover.backend.repo.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public boolean addImage(String title, String subtitle, String description, String dataUrl) {
        throw new RuntimeException("Not Yet Implemented");
    }

    @Override
    public List<Image> getImagesFromUser(Long userId) {
        return imageRepository.findAllByUserId(userId);
    }
}
