package com.example.zhafran.restAPI.model.request.contactReq;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagingResponsse {
    private Integer currentPage;
    private Integer size;
    private Integer totalPages;
}
