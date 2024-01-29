package com.aguichardon.springtest3.controller;

import com.aguichardon.springtest3.model.Sport;
import com.aguichardon.springtest3.service.SportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sports")
public class SportController {

    private final SportService sportService;

    public SportController(SportService sportService) {
        this.sportService = sportService;
    }

    @GetMapping("/all")
    public List<Sport> getAllSports() {
        return sportService.getAllSports();
    }

    @GetMapping("/{id}")
    public Sport getSportById(@PathVariable Long id) {
        return sportService.getSportById(id);
    }

    @PostMapping("/create")
    public Sport createSport(@RequestBody Sport sport) {
        return sportService.createSport(sport);
    }

    @PostMapping("/save")
    public Sport saveSport(@RequestBody Sport sport) {
        return sportService.createSport(sport);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        sportService.deleteSport(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sport> updateSport(@PathVariable Long id, @RequestBody Sport updatedSport) {
        sportService.updateSport(id, updatedSport);
        return ResponseEntity.ok(updatedSport);
    }
}
