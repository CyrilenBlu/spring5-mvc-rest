package blue.springframework.spring5mvcrest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VendorDTO {

    private Long id;
    private String name;

    @JsonProperty("vendor_url")
    private String vendorURL;
}
