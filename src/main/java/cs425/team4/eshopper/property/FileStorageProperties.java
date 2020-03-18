package cs425.team4.eshopper.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    private String uploadDirectory;// = "/api/v1/products/productimages/";

    public String getUploadDirectory() {
        return uploadDirectory;
    }

    public void setUploadDirectory(String uploadDirectory) {
        this.uploadDirectory = uploadDirectory;
    }

}
