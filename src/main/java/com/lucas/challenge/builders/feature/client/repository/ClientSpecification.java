package com.lucas.challenge.builders.feature.client.repository;

import com.lucas.challenge.builders.feature.client.domain.Client;
import com.lucas.challenge.builders.feature.client.domain.Client_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ClientSpecification {

    public static Specification<Client> name(String name) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Client_.name), name);
    }

    public static Specification<Client> email(String email) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Client_.email), email);
    }

    public static Specification<Client> cellphone(String cellphone) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Client_.cellphone), cellphone);
    }

    public static Specification<Client> id(Long id) {
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(Client_.id), id);
    }

}
