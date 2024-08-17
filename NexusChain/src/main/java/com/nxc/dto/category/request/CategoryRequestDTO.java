package com.nxc.dto.category.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO {
    private Long id;
    private String name;
    private String description;
}
