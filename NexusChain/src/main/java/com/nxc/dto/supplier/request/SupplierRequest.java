package com.nxc.dto.supplier.request;

import com.nxc.dto.user.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRequest {
    private UserRequest user;
    private String paymentTerms;
}
