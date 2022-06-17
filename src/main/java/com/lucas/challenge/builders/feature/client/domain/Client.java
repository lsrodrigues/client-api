package com.lucas.challenge.builders.feature.client.domain;

import com.lucas.challenge.builders.shared.base.EntityBase;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_client")
@EqualsAndHashCode(callSuper = true)
@SequenceGenerator(name = "seq_client", sequenceName = "seq_client", allocationSize = 1)
public class Client extends EntityBase<Long> {

    @Id
    @Column(name = "cod_client")
    @GeneratedValue(generator = "seq_client", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "e_mail")
    private String email;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "birth_date")
    private LocalDate birthDate;

}
