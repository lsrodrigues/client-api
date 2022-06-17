package com.lucas.challenge.builders.feature.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDTO {

    private Long id;

    private String name;

    private String email;

    private String cellphone;

    private int age;

}
