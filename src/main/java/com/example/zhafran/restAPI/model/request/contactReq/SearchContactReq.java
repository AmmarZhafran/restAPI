package com.example.zhafran.restAPI.model.request.contactReq;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchContactReq {
    private String name;
    private String phone;
    private String email;
    @NotNull
    private Integer page;
    @NotNull
    private Integer size;
}
