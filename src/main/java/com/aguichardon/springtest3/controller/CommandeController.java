package com.aguichardon.springtest3.controller;

import com.aguichardon.springtest3.model.Commande;
import com.aguichardon.springtest3.model.Sport;
import com.aguichardon.springtest3.service.CommandeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commande")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping("/all")
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public Commande getCommandeById(@PathVariable Long id) {
        return commandeService.getCommandeById(id);
    }

    @PostMapping("/create")
    public Commande creerCommande(@RequestBody Commande commande) {
        return commandeService.createCommande(commande);
    }


    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
    }
}
