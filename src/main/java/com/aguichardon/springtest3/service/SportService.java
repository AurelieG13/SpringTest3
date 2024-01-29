package com.aguichardon.springtest3.service;

import com.aguichardon.springtest3.model.Sport;
import com.aguichardon.springtest3.repository.SportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService {
    private SportRepository sportRepository;

    public Sport createSport(Sport sport) {
        return sportRepository.save(sport);
    }

    public List<Sport> getAllSports() {
        return sportRepository.findAll();
    }

    public Sport getSportById(Long id) {
        return sportRepository.findById(id).orElse(null);
    }

    public void deleteSport(Long id) {
        sportRepository.deleteById(id);
    }

    public void updateSport(Long id, Sport updatedSport) {
        // Vérifier si le produit existe
        Sport existingSport = getSportById(id);

        if (existingSport != null) {
            // Mettre à jour les propriétés du produit
            if (updatedSport.getName() !=null) {
                existingSport.setName(updatedSport.getName());
            }
            if (updatedSport.getNbSeat() != 0) {
                existingSport.setNbSeat(updatedSport.getNbSeat());
            }
            if (updatedSport.getPrice() != 0) {
                existingSport.setPrice(updatedSport.getPrice());
            }

            // Ajouter d'autres propriétés à mettre à jour...

            // Enregistrer les modifications dans la base de données
            createSport(existingSport);
        }
        // Gérer le cas où le produit n'existe pas, si nécessaire
    }
}
