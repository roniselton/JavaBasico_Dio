package com.example.jpaspringboot.event.listener;

import com.example.jpaspringboot.event.ResourceCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class ResourceCreatedListener implements ApplicationListener<ResourceCreatedEvent> {
    @Override
    public void onApplicationEvent(ResourceCreatedEvent event) {
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path(event.getPath()).path("/{id}")
                .buildAndExpand( event.getId()).toUri();
        event.getResponse().setHeader("location", uri.toASCIIString());
    }
}
