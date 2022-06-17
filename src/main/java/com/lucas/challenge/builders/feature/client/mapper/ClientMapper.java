package com.lucas.challenge.builders.feature.client.mapper;

import com.lucas.challenge.builders.feature.client.domain.Client;
import com.lucas.challenge.builders.feature.client.dto.ClientDTO;
import com.lucas.challenge.builders.feature.client.dto.CreateClientDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ClientMapper {

    private final ModelMapper modelMapper;

    public Client toDomain(CreateClientDTO dto){
        return modelMapper.map(dto, Client.class);
    }

    public ClientDTO toDto(Client domain){
        var map = modelMapper.map(domain, ClientDTO.class);
        map.setAge(Period.between(domain.getBirthDate(), LocalDate.now()).getYears());
        return map;
    }

    public List<ClientDTO> toDto(List<Client> domain){
        return domain
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void merge(Client source, Client destination) {
         modelMapper.map(source, destination);
    }
}
