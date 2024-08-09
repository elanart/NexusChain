package com.nxc.dto.supplier.response;

import com.nxc.dto.user.response.UserDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDetailsResponse {
    private UserDetailResponse user;
    private String paymentTerms;
}