package berlin.discover.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Image {


    @Id
    @SequenceGenerator(name = "image_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String subtitle;

    private String description;

    @Column(name="data_url")
    private byte[] dataUrl;

    @NotBlank
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotBlank
    @Column(name="updated_at")
    private LocalDateTime updateAt;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(byte[] dataUrl) {
        this.dataUrl = dataUrl;
    }
}
