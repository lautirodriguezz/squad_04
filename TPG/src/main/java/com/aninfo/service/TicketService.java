package com.aninfo.service;

import com.aninfo.model.Ticket;
import com.aninfo.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import javax.transaction.Transactional;
// import java.util.ArrayList;
import java.util.Collection;
// import java.util.List;
// import java.util.Optional;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public Collection<Ticket> getTickets(){
        return ticketRepository.findAll();
    }

    public void deleteTicketByID(Long id){
        ticketRepository.deleteById(id);
    }
}
