package com.aninfo.repository;

import com.aninfo.model.Ticket;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TicketFinishedRepository extends CrudRepository<Ticket, Long> {

    @Override
    List<Ticket> findAll();

}