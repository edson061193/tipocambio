package com.edsuarez.tipocambio.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "T_TYPECHANGE")
public class TypeChange implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String money;
    private String symbology;
    private Double value;
    private String userUpdate;
    private LocalDateTime lastUpdate;
}
