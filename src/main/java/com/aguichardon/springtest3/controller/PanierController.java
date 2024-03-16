package com.aguichardon.springtest3.controller;

import com.aguichardon.springtest3.model.Panier;
import com.aguichardon.springtest3.model.Sport;
import com.aguichardon.springtest3.service.PanierService;
import com.aguichardon.springtest3.service.SportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/panier")
public class PanierController {

    private final PanierService panierService;
    private final SportService sportService;

    public PanierController(PanierService panierService, SportService sportService) {
        this.panierService = panierService;
        this.sportService = sportService;
    }


    @GetMapping
    public List<Panier> getAllPanier() {
        return panierService.getAllPaniers();
    }

    @GetMapping("/{id}")
    public Panier getPanierById(@PathVariable Long id) {
        return panierService.getPanierById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePanier(@PathVariable Long id) {
        panierService.deletePanier(id);
    }

    @PostMapping("/create")
    public Panier createPanier() {
        Panier panier = new Panier();
        return panierService.savePanier(panier);
    }

    @PostMapping("/{panierId}/add-sport/{sportId}")
    public ResponseEntity<String> addSportToPanier(@PathVariable Long panierId, @PathVariable Long sportId) {
        Panier panier = panierService.getPanierById(panierId);
        if (panier == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Panier not found");
        }

        Sport sport = sportService.getSportById(sportId);
        if (sport == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sport not found");
        }

        panierService.savePanier(panier);

        return ResponseEntity.status(HttpStatus.OK).body("Product added to cart");
    }

    @PostMapping("/{panierId}/checkout")
    public ResponseEntity<String> checkoutPanier(@PathVariable Long panierId) {
        Panier panier = panierService.getPanierById(panierId);
        if (panier == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found");
        }

        // Effectuez les opérations nécessaires avant de sauvegarder le panier, par exemple, calcul du total, validation, etc.

        panierService.savePanier(panier);

        return ResponseEntity.status(HttpStatus.OK).body("Cart checked out successfully");
    }

}
