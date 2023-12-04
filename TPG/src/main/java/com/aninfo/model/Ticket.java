package com.aninfo.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private String startDate;
    private String endDate;
    private Long timeWork;
    @ManyToMany
    @JoinTable(
        name = "ticket_tarea",
        joinColumns = @JoinColumn(name = "ticket_id"),
        inverseJoinColumns = @JoinColumn(name = "tarea_id"))
    private List<Tarea> tareas;
    
    public Ticket(){
    }

    public Ticket(Integer severity, String client, String description, String priority) {
        this.state = "Abierto";
        this.severity = "S" + severity;
        this.client = client;
        this.description = description;
        this.priority = priority;
        String date = "" + LocalDateTime.now().getDayOfMonth();
       
        if(date.length() == 2){
            this.startDate = ""+ LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonthValue() 
                + "-" + LocalDateTime.now().getDayOfMonth();
        } else {    
            this.startDate = ""+ LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonthValue() 
                        + "-0" + LocalDateTime.now().getDayOfMonth();
        }
    }

    public Long getId() {return id;}
    public String getState(){return state;}
    public String getSeverity() {return severity;}
    public String getClient() {return client;}
    public String getDescription() {return description;}
    public String getPriority() {return priority;}
    public String getStartDate() {return startDate;}
    public String getEndDate() {return endDate;}
    public Long getTimeWork() {return timeWork;}

    public void setSeverity(String severity) {this.severity = severity;}
    public void setClient(String client) {this.client = client;}
    public void setDescription(String description) {this.description = description;}
    public void setPriority(String priority) {this.priority = priority;}
    public void setEndDate(String hour){this.endDate = hour;}
    public void setTimeWork(Long timeWork){this.timeWork = timeWork;}
    public void updateState(String state){this.state = state;}
    public void updateDescription(String description){this.description = description;}
    public void updatePriority(String priority ){this.priority = priority;}
    public void updateSeverity(Integer severity ){this.severity = "S" + severity;}

}
