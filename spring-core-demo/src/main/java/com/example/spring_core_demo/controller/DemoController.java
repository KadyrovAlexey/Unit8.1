package com.example.spring_core_demo.controller;

import com.example.spring_core_demo.entity.Client;
import com.example.spring_core_demo.entity.Event;
import com.example.spring_core_demo.entity.EventType;
import com.example.spring_core_demo.repository.ClientRepository;
import com.example.spring_core_demo.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        log.info("Запрос на получение всех клиентов");
        return clientRepository.findAll();
    }

    @PostMapping("/clients")
    public Client createClient(@RequestBody Client client) {
        log.info("Создание клиента: {}", client.getName());
        return clientRepository.save(client);
    }

    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event) {
        log.info("Создание события: {}", event.getTitle());
        return eventRepository.save(event);
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        log.info("Запрос на получение всех событий");
        return eventRepository.findAll();
    }

    @GetMapping("/init")
    public String initData() {
        log.info("Инициализация тестовых данных");

        Client client1 = new Client("Иван Иванов", "Москва");
        Client client2 = new Client("Петр Петров", "Санкт-Петербург");
        Client client3 = new Client("Мария Сидорова", "Казань");
        clientRepository.save(client1);
        clientRepository.save(client2);
        clientRepository.save(client3);

        Event event1 = new Event("Java Conference 2024", EventType.CONFERENCE, LocalDateTime.now().plusDays(30));
        Event event2 = new Event("Spring Workshop", EventType.WORKSHOP, LocalDateTime.now().plusDays(15));
        Event event3 = new Event("Java Meetup", EventType.MEETUP, LocalDateTime.now().plusDays(7));
        Event event4 = new Event("Online Webinar", EventType.WEBINAR, LocalDateTime.now().plusDays(3));
        eventRepository.save(event1);
        eventRepository.save(event2);
        eventRepository.save(event3);
        eventRepository.save(event4);

        return "Данные инициализированы!";
    }
}