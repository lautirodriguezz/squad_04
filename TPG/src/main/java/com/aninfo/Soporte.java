package com.aninfo;

import com.aninfo.model.Ticket;
import com.aninfo.service.TicketService;
import com.aninfo.exceptions.InvalidPriorityException;
import com.aninfo.exceptions.InvalidSeverityException;
import com.aninfo.exceptions.InvalidStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@SpringBootApplication
@EnableSwagger2
public class Soporte {

	@Autowired
	private TicketService ticketService;

	public static void main(String[] args) {
		SpringApplication.run(Soporte.class, args);
	}

	@PostMapping("/tickets")
	@ResponseStatus(HttpStatus.CREATED)
	public Ticket createTicket(@RequestParam Integer severity,@RequestParam String client,@RequestParam String description,@RequestParam Integer priority) {

		if((severity > 4) || (severity < 1)){
            throw new InvalidSeverityException("Severidad incorrecta, debe ser menor a 4.");
        }
		return ticketService.createTicket(severity, client, description, this.parsePriority(priority));
	}

	@GetMapping("/tickets")
	public Collection<Ticket> getTickets() {
		return ticketService.getTickets();
	}

	@DeleteMapping("/tickets/{id}")
	public void deleteTicketById(@PathVariable Long id) {
		ticketService.deleteTicketByID(id);
	}

	@GetMapping("/tickets/{id}")
	public ResponseEntity<Ticket> getTicket(@PathVariable Long id) {
		Optional<Ticket> ticket = ticketService.findById(id);
		return ResponseEntity.of(ticket);
	}

	@PutMapping("/tickets/updateState/{id}")
	public ResponseEntity<Ticket> updateState(@PathVariable Long id, @RequestParam int state) {
		Optional<Ticket> ticketOptional = ticketService.modifyState(id, this.parseState(state));
		if (ticketOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Ticket ticket = ticketOptional.get();
		return ResponseEntity.ok(ticket);
	}

	@PutMapping("/tickets/updateDescription/{id}")
	public ResponseEntity<Ticket> updateDescription(@PathVariable Long id, @RequestParam String description) {

		Optional<Ticket> ticketOptional = ticketService.modifyDescription(id, description);
		if (ticketOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Ticket ticket = ticketOptional.get();
		return ResponseEntity.ok(ticket);
	}

	@PutMapping("/tickets/updatePriority/{id}")
	public ResponseEntity<Ticket> updatePriority(@PathVariable Long id, Integer priority) {
		Optional<Ticket> ticketOptional = ticketService.modifyPriority(id, parsePriority(priority));
		if (ticketOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Ticket ticket = ticketOptional.get();
		return ResponseEntity.ok(ticket);
	}

	@PutMapping("/tickets/updateSeverity/{id}")
	public ResponseEntity<Ticket> updateSeverity(@PathVariable Long id, Integer severity) {

		if((severity > 4) || (severity < 1)){
            throw new InvalidSeverityException("Severidad incorrecta, debe ser menor a 4.");
        }
		Optional<Ticket> ticketOptional = ticketService.modifySeverity(id, severity);
		if (ticketOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Ticket ticket = ticketOptional.get();
		return ResponseEntity.ok(ticket);
	}


	private String parseState(int newState) {
		switch (newState) {
			case 1:
				return "En progreso";
			case 2:
				return "Bloqueada";
			case 3:
				return "Resuelto";
			default:
				throw new InvalidStateException("Estado invalido");
		}
	}
	private String parsePriority(int newPriority) {
		switch (newPriority) {
			case 1:
				return "Alta";
			case 2:
				return "Media";
			case 3:
				return "Baja";
			default:
				throw new InvalidPriorityException("Prioridad invalida. 1-Alta | 2-Media | 3-Baja");
		}
	}


	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(PathSelectors.any())
			.build();
	}
}
