package com.lucas.challenge.builders.feature.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class UpdateClientDTO extends CreateClientDTO implements Serializable {

    private Long id;

}
