package com.aninfo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String state;
    private String severity;
    private String client;
    private String description;
    private String priority;    
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    public Ticket(){
    }

    public Ticket(Integer severity, String client, String description, String priority) {
        this.state = "Abierto";
        this.severity = "S" + severity;
        this.client = client;
        this.description = description;
        this.priority = priority;
        this.startDate = LocalDateTime.now();
    }

    public Long getId() {return id;}
    public String getState(){return state;}
    public String getSeverity() {return severity;}
    public String getClient() {return client;}
    public String getDescription() {return description;}
    public String getPriority() {return priority;}
    public LocalDateTime getStartDate() {return startDate;}
    public LocalDateTime getEndDate() {return endDate;}

    public void setSeverity(String severity) {this.severity = severity;}
    public void setClient(String client) {this.client = client;}
    public void setDescription(String description) {this.description = description;}
    public void setPriority(String priority) {this.priority = priority;}
    public void updateState(String state){this.state = state;}
    public void setEndDate(LocalDateTime hour){this.endDate = hour;}

}
