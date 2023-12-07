package com.example.jpaspringboot.event;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ResourceCreatedEvent extends ApplicationEvent {
    private Long id;
    private String path;
    private HttpServletResponse response;
    public ResourceCreatedEvent(Object source, String path, Long id , HttpServletResponse response) {
        super(source);
        this.id = id;
        this.path = path;
        this.response = response;
    }
}
