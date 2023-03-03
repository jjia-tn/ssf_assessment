package ibf2022.batch2.ssf.ssf_assessment.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Shipping {

    @NotNull(message = "Please provide your name")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String name;

    @NotNull(message = "Please provide your address")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shipping [name=" + name + ", address=" + address + "]";
    }
    
}
