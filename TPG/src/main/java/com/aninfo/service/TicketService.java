package com.aninfo.service;

import com.aninfo.exceptions.TicketNotFoundException;
import com.aninfo.exceptions.TicketSolvedException;
import com.aninfo.model.Ticket;
import com.aninfo.repository.TicketRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
            ticket.setEndDate(""+ LocalDateTime.now().getDayOfMonth() + "-" + LocalDateTime.now().getMonthValue() 
            + "-" + LocalDateTime.now().getYear());
            ticket.setTimeWork(this.setDifferenceBetweenDates(ticket.getStartDate()));
        }
        ticket.updateState(newState);
        ticketRepository.save(ticket);
        return ticketOptional;
        }
    
    public Optional<Ticket> modifyDescription(Long id, String description){

        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isEmpty()) {
            throw new TicketNotFoundException("No se encontró el ticket");
        }
        Ticket ticket = ticketOptional.get();
        if (ticket.getState() == "Resuelto") {
            throw new TicketSolvedException("El ticket ya esta resuelto");
        }
        ticket.updateDescription(description);
        ticketRepository.save(ticket);
        return ticketOptional;
    }

    public Optional<Ticket> modifyPriority(Long id, String priority){
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isEmpty()) {
            throw new TicketNotFoundException("No se encontró el ticket");
        }
        Ticket ticket = ticketOptional.get();
        if (ticket.getState() == "Resuelto") {
            throw new TicketSolvedException("El ticket ya esta resuelto");
        }
        ticket.updatePriority(priority);
        ticketRepository.save(ticket);
        return ticketOptional;
    }

    public Optional<Ticket> modifySeverity(Long id, Integer severity){
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        if (ticketOptional.isEmpty()) {
            throw new TicketNotFoundException("No se encontró el ticket");
        }
        Ticket ticket = ticketOptional.get();
        if (ticket.getState() == "Resuelto") {
            throw new TicketSolvedException("El ticket ya esta resuelto");
        }
        ticket.updateSeverity(severity);
        ticketRepository.save(ticket);
        return ticketOptional;
    }

    private Long setDifferenceBetweenDates(String startDate){

        String date = "" + LocalDateTime.now().getDayOfMonth();
        String endDate = "";

        if(date.length() == 2){
            // // Strings que representan las fechas
            endDate = ""+ LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonthValue() 
            + "-" + LocalDateTime.now().getDayOfMonth();
        } else {
            endDate = ""+ LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonthValue() 
            + "-0" + LocalDateTime.now().getDayOfMonth();   
        }
        // Define el formato de las fechas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Convierte las cadenas a objetos LocalDate
        LocalDate fecha2 = LocalDate.parse(endDate, formatter);
        LocalDate fecha1 = LocalDate.parse(startDate, formatter);

        // Calcula la diferencia en días
        long difference = ChronoUnit.DAYS.between(fecha1, fecha2) + 1;

        return difference;
    }
}
