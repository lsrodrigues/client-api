package com.lucas.challenge.builders.feature.client.dto;

import com.lucas.challenge.builders.shared.dto.ResponseDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponseClientDTO extends ResponseDTO {

    private ClientDTO data;

}
