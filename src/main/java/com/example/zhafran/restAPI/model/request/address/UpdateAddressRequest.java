package com.example.zhafran.restAPI.model.request.address;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAddressRequest {
    @JsonIgnore
    @NotBlank
    private String contactId;
    @JsonIgnore
    @NotBlank
    private String addressId;

    private String street;

    private String city;

    private String province;

    private String country;

    private String postalCode;

}
