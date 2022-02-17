package com.edsuarez.tipocambio.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeResponse {
    private Long id;
    private String money;
    private String symbology;
    private Double value;
    private String userUpdate;
}
