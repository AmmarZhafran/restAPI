package com.example.zhafran.restAPI.model.response.authentikasiResponse;

import com.example.zhafran.restAPI.model.request.contactReq.PagingResponsse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebResponse <T>{

    private T data;

    private String errors;

    private PagingResponsse paging;
}
