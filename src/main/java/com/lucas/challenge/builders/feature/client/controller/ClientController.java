package com.lucas.challenge.builders.feature.client.controller;

import com.lucas.challenge.builders.feature.client.dto.ClientDTO;
import com.lucas.challenge.builders.feature.client.dto.CreateClientDTO;
import com.lucas.challenge.builders.feature.client.dto.ResponseClientDTO;
import com.lucas.challenge.builders.feature.client.dto.UpdateClientDTO;
import com.lucas.challenge.builders.feature.client.mapper.ClientMapper;
import com.lucas.challenge.builders.feature.client.service.ClientService;
import com.lucas.challenge.builders.shared.dto.ResponseDataDTO;
import com.lucas.challenge.builders.shared.dto.ResponseErrorDTO;
import com.lucas.challenge.builders.shared.messages.MessagesApiProperties;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/client")
@Tag(name = "Client", description = "Operations on the client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @Operation(
            summary = "♦ Create client",
            description = "## Update client",
            responses = {
                    @ApiResponse(responseCode = "200", description = "### Success: \n- S001=Successful request"),
                    @ApiResponse(responseCode = "404", description = "### Error: \n- CLE001=Client not found!", content = @Content(schema = @Schema(implementation = ResponseErrorDTO.class)))
            }
    )
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseClientDTO> createClient(@Valid @RequestBody CreateClientDTO createUserDTO) {
        ResponseClientDTO responseDTO = new ResponseClientDTO();
        responseDTO.setMessagesApiProperties(MessagesApiProperties.S001);
        responseDTO.setData(clientMapper.toDto(clientService.save(clientMapper.toDomain(createUserDTO))));
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(
            summary = "♦ Update client",
            description = "## Update client",
            responses = {
                    @ApiResponse(responseCode = "200", description = "### Success: \n- S002=Resource updated successfully"),
                    @ApiResponse(responseCode = "404", description = "### Error: \n- CLE001=Client not found!", content = @Content(schema = @Schema(implementation = ResponseErrorDTO.class)))
            }
    )
    @PatchMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseClientDTO> updateUser(@Valid @RequestBody UpdateClientDTO updateUserDTO) {
        ResponseClientDTO responseDTO = new ResponseClientDTO();
        responseDTO.setMessagesApiProperties(MessagesApiProperties.S002);
        responseDTO.setData(clientMapper.toDto(clientService.update(clientMapper.toDomain(updateUserDTO))));
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(
            summary = "♦ Get all clients",
            description = "## Get all clients",
            responses = {
                    @ApiResponse(responseCode = "200", description = "### Success: \n- S001=Successful request")
            }
    )
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDataDTO<Page<ClientDTO>>> getEvDrivers(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "0", required = false) int pageSize
    ) {
        var data = clientMapper.toDto(clientService.getAll(pageNumber, pageSize).getContent());
        return ResponseEntity.ok(ResponseDataDTO.ok(new PageImpl<>(data)));
    }

    @Operation(
            summary = "♦ Search clients",
            description = "## Search clients",
            responses = {
                    @ApiResponse(responseCode = "200", description = "### Success: \n- S001=Successful request")
            }
    )
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDataDTO<Page<ClientDTO>>> search(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "0", required = false) int pageSize,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String cellphone
    ) {
        var data = clientMapper.toDto(clientService.searchByParams(id, name, email, cellphone, pageNumber, pageSize).getContent());
        return ResponseEntity.ok(ResponseDataDTO.ok(new PageImpl<>(data)));
    }

}
