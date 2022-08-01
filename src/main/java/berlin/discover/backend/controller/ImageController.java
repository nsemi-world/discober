package berlin.discover.backend.controller;

import berlin.discover.backend.model.Image;
import berlin.discover.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/images")
public class ImageController {

    @Autowired private ImageService imageService;

    /**
     * Get images from the database. Given the number of images, only a page will be retrieved. Clients must provide two
     * parameters representing the pagination specification, the number of page and the number of images in that page.
     * @param page  The page to be retrieved
     * @param limit The number of elements to be retrieved in the page
     *
     * @return  A list with at most <code>limit</code> images.
     */
    @GetMapping
    public ResponseEntity<List<Image>> getImages(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit) {
        List<Image> images = new ArrayList<>();
        return ResponseEntity.ok(images);
    }



    @PostMapping
    public ResponseEntity<Void> addImage(Map<String, String> body) {
        String title = body.get("title");
        String subtitle = body.get("subtitle");
        String description = body.get("description");
        String dataUrl = body.get("dataUrl");
        boolean result = imageService.addImage(title, subtitle, description, dataUrl);
        return (result)
                ? ResponseEntity.created(URI.create("api/v1/images")).build()
                : ResponseEntity.badRequest().build();
    }
}
