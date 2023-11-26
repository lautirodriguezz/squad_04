package com.aninfo.service;

import com.aninfo.exceptions.TicketNotFoundException;
import com.aninfo.exceptions.TicketSolvedException;
import com.aninfo.model.Ticket;
import com.aninfo.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Integer severity,String client,String description,String priority){

        Ticket ticket = new Ticket(severity, client, description, priority);
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

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    public Optional<Ticket> modifyState(Long id, String newState) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isEmpty()) {
            throw new TicketNotFoundException("No se encontró el ticket");
        }
        Ticket ticket = ticketOptional.get();
        if (ticket.getState() == "Resuelto") {
            throw new TicketSolvedException("El ticket ya esta resuelto");
        }
        if((newState == "Resuelto") && (ticket.getState() != "Resuelto")){
            ticket.setEndDate(LocalDateTime.now());
        }
        ticket.updateState(newState);
        ticketRepository.save(ticket);
        return ticketOptional;
        }
    
}
