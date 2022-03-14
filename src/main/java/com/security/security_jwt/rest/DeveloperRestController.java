package com.security.security_jwt.rest;

import com.security.security_jwt.model.Developer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/developers")
public class DeveloperRestController {

    private List<Developer> developers = Stream.of(
            new Developer(1L, "Ivan", "Ivanov"),
            new Developer(2L, "Petr", "Petrov"),
            new Developer(3L, "Fedor", "Fedorov")
    ).collect(Collectors.toList());


    @GetMapping
    public List<Developer> findAll() {
        return developers;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developer:read')")
    public Developer findById(@PathVariable Long id) {
        return developers.stream().filter(developer -> developer.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('developer:write')")
    public Developer addDeveloper(@RequestBody Developer developer){
        developers.add(developer);
        return developer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('developer:write')")
    public void remove(@PathVariable Long id){
        developers.removeIf(developer -> developer.getId().equals(id));
    }
}
