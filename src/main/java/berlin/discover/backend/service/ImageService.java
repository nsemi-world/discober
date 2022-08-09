package berlin.discover.backend.service;

import berlin.discover.backend.model.Image;

import java.util.List;

public interface ImageService {
    boolean addImage(String title, String subtitle, String description, String dataUrl);
    List<Image> getImagesFromUser(Long userId);
}
