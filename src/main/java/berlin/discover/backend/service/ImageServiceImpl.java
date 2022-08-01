package berlin.discover.backend.service;

import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public boolean addImage(String title, String subtitle, String description, String dataUrl) {
        throw new RuntimeException("Not Yet Implemented");
    }
}
