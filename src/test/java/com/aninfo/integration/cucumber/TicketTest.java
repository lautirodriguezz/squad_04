package com.aninfo.integration.cucumber;

import com.aninfo.Soporte;
import com.aninfo.model.Ticket;
import com.aninfo.service.TicketService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ContextConfiguration(classes = Soporte.class)
@CucumberContextConfiguration
@WebAppConfiguration
public class TicketTest {

    @Autowired
    TicketService ticketService;

    Ticket ticket;

    Ticket createTicket(){
        Integer severity = 1;
        String client = "test";
        String description = "testDescr";
        String priority = "1";
        return ticketService.createTicket( severity, client, description, priority);
    }

    @Given("Creo un ticket")
    public void crearTicket(){
        ticket = createTicket();
    }

    @Then("^El sistema debe registrar el ticket correctamente con el estado ([A-Za-z]+)$")
    public void registrarConEstado(String estado){
        assertEquals(estado,ticketService.findById(ticket.getId()).get().getState());
    }
}
