package com.aguichardon.springtest3.service;

import com.aguichardon.springtest3.model.Panier;
import com.aguichardon.springtest3.repository.PanierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanierService {
    private PanierRepository panierRepository;

    public PanierService(PanierRepository panierRepository) {
        this.panierRepository = panierRepository;
    }

    public List<Panier> getAllPaniers() {
        return panierRepository.findAll();
    }

    public Panier getPanierById(Long id) {
        return panierRepository.findById(id).orElse(null);
    }

    public Panier savePanier(Panier panier) {
        return panierRepository.save(panier);
    }

    public void deletePanier(Long id)  {
        panierRepository.deleteById(id);
    }
}
