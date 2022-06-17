package com.lucas.challenge.builders.feature.client.service;

import com.lucas.challenge.builders.config.exception.NotFoundException;
import com.lucas.challenge.builders.feature.client.domain.Client;
import com.lucas.challenge.builders.feature.client.mapper.ClientMapper;
import com.lucas.challenge.builders.feature.client.repository.ClientRepository;
import com.lucas.challenge.builders.feature.client.repository.ClientSpecification;
import com.lucas.challenge.builders.shared.messages.MessagesApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Slf4j
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Client update(Client client) {
        Client clientDB = findById(client.getId());
        clientMapper.merge(client, clientDB);
        return clientRepository.save(clientDB);
    }

    public Page<Client> getAll(int pageNumber, int pageSize) {
        Pageable pageable = getPageable(pageNumber, pageSize);

        return clientRepository.findAll(pageable);
    }

    public Page<Client> searchByParams(Long id, String name, String email, String phone, int pageNumber, int pageSize) {
        Pageable pageable = getPageable(pageNumber, pageSize);

        return clientRepository.findAll(where(ClientSpecification.name(name))
                .or(ClientSpecification.id(id))
                .or(ClientSpecification.email(email))
                .or(ClientSpecification.cellphone(phone)), pageable);
    }

    private Pageable getPageable(int pageNumber, int pageSize) {
        return pageNumber == 0 && pageSize == 0 ?
                Pageable.unpaged() :
                PageRequest.of(pageNumber, pageSize);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessagesApiProperties.CLE001));
    }


}
