package com.lucas.challenge.builders.shared.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.lucas.challenge.builders.shared.messages.MessagesApiProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResponseDataDTO<T> extends ResponseDTO {

    @JsonInclude()
    private T data;

    @JsonIgnore
    public  static<T> ResponseDataDTO<T> ok(T data) {
        var response = new ResponseDataDTO<T>();
        response.setData(data);
        response.setMessagesApiProperties(MessagesApiProperties.S001);
        return response;
    }

}