package com.aninfo.integration.cucumber;

import com.aninfo.Soporte;
import com.aninfo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = Soporte.class)
@WebAppConfiguration
public class TicketTest {

    @Autowired
    TicketService ticketService;

}
