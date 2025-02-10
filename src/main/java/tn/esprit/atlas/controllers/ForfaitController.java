package tn.esprit.atlas.controllers;

import tn.esprit.atlas.entities.Forfait;
import tn.esprit.atlas.services.ForfaitService;

import java.util.List;

public class ForfaitController {

    private ForfaitService forfaitService = new ForfaitService();

    public void createForfait(Forfait forfait) {
        forfaitService.addForfait(forfait);
    }

    public void listForfaits() {
        List<Forfait> forfaits = ForfaitService.getAllForfaits();
        forfaits.forEach(forfait -> System.out.println(forfait.getName() + " - " + forfait.getDescription()));
    }

    public void modifyForfait(Forfait forfait) {
        forfaitService.updateForfait(forfait);
    }

    public void removeForfait(int packaged) {
        forfaitService.deleteForfait(packaged);
    }
}