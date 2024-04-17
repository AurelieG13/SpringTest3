package com.aguichardon.springtest3.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "commande")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String beneficiaryName;
    private String beneficiaryFirstname;
    private double amount;
    private String paymentType;

}
