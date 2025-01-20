package com.example.zhafran.restAPI.model.response.addressRes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressRes {
    private String id;
    private String country;
            private String city;
                    private String province;
                            private String street;
                                    private String postalCode;
}
