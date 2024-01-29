package com.aguichardon.springtest3.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sport")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int nbSeat;
    private double price;
}
