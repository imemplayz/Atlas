package tn.esprit.atlas.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.sql.Connection;
import java.util.List;
import java.util.Scanner;
import tn.esprit.atlas.services.VolService;
import tn.esprit.atlas.services.AirLineService;
import tn.esprit.atlas.entities.Vol;
import tn.esprit.atlas.entities.AirLine;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

public class AtlasApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AtlasApplication.class.getResource("/tn/esprit/atlas/views/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("ATLAS");

        InputStream inputStream = getClass().getResourceAsStream("/tn/esprit/atlas/assets/ATLAS_LOGO.png");
        if (inputStream == null) {
            System.err.println("Icon file not found! Check the path.");
        } else {
            Image icon = new Image(inputStream);
            stage.getIcons().add(icon);
        }

        try {
            Connection cnx = DatabaseConnection.getInstance().getCnx();
            if (cnx != null) {
                System.out.println("Connexion à la base de données réussie !");
            } else {
                System.err.println("Échec de la connexion !");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VolService volService = new VolService();
        AirLineService airLineService = new AirLineService();

        try {
            while (true) {

                System.out.println("\n=== Choisissez une table ===");
                System.out.println("1. Gestion des Vols");
                System.out.println("2. Gestion des Compagnies Aériennes");
                System.out.println("3. Quitter");
                System.out.print("Choisissez une option : ");

                int choixTable = scanner.nextInt();
                scanner.nextLine();

                if (choixTable == 1) {
                    // Menu Vols
                    showVolMenu(scanner, volService);
                } else if (choixTable == 2) {
                    // Menu AirLines
                    showAirLineMenu(scanner, airLineService);
                } else if (choixTable == 3) {
                    System.out.println("Quitter...");
                    scanner.close();
                    return;
                } else {
                    System.out.println("Option invalide! Essayez à nouveau.");
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    // Menu de gestion des Vols
    private static void showVolMenu(Scanner scanner, VolService volService) {
        while (true) {
            // CLI Menu pour les Vols
            System.out.println("\n=== Gestion des Vols ===");
            System.out.println("1. Ajouter un vol");
            System.out.println("2. Modifier un vol");
            System.out.println("3. Supprimer un vol");
            System.out.println("4. Lister les vols");
            System.out.println("5. Retour");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    addVol(scanner, volService);
                    break;
                case 2:
                    updateVol(scanner, volService);
                    break;
                case 3:
                    deleteVol(scanner, volService);
                    break;
                case 4:
                    listVols(volService);
                    break;
                case 5:
                    return; // Retour au menu principal
                default:
                    System.out.println("Option invalide! Essayez à nouveau.");
            }
        }
    }


    private static void showAirLineMenu(Scanner scanner, AirLineService airLineService) {
        while (true) {
            // CLI Menu pour les Compagnies Aériennes
            System.out.println("\n=== Gestion des Compagnies Aériennes ===");
            System.out.println("1. Ajouter une compagnie aérienne");
            System.out.println("2. Modifier une compagnie aérienne");
            System.out.println("3. Supprimer une compagnie aérienne");
            System.out.println("4. Lister les compagnies aériennes");
            System.out.println("5. Retour");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1:
                    addAirLine(scanner, airLineService);
                    break;
                case 2:
                    updateAirLine(scanner, airLineService);
                    break;
                case 3:
                    deleteAirLine(scanner, airLineService);
                    break;
                case 4:
                    listAirLines(airLineService);
                    break;
                case 5:
                    return; // Retour au menu principal
                default:
                    System.out.println("Option invalide! Essayez à nouveau.");
            }
        }
    }


    private static void addVol(Scanner scanner, VolService volService) {
        System.out.print("Entrez le départ du vol : ");
        String depart = scanner.nextLine();

        System.out.print("Entrez la destination du vol : ");
        String destination = scanner.nextLine();

        System.out.print("Entrez la durée du vol : ");
        String duree = scanner.nextLine();

        System.out.print("Entrez le nombre de places disponibles : ");
        int places = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Vol vol = new Vol(depart, destination, duree, places);
        volService.ajouter(vol);
        System.out.println("Vol ajouté avec succès : " + vol);
    }

    private static void updateVol(Scanner scanner, VolService volService) {
        System.out.print("Entrez le départ du vol à modifier : ");
        String depart = scanner.nextLine();

        System.out.print("Entrez la destination du vol à modifier : ");
        String destination = scanner.nextLine();

        // Recherche du vol existant
        Vol volExist = null;
        List<Vol> vols = volService.getall();
        for (Vol vol : vols) {
            if (vol.getDeparture().equals(depart) && vol.getDestination().equals(destination)) {
                volExist = vol;
                break;
            }
        }

        if (volExist != null) {
            System.out.print("Entrez le nouveau départ du vol : ");
            String newDepart = scanner.nextLine();

            System.out.print("Entrez la nouvelle destination du vol : ");
            String newDestination = scanner.nextLine();

            System.out.print("Entrez la nouvelle durée du vol : ");
            String newDuree = scanner.nextLine();

            System.out.print("Entrez le nouveau nombre de places disponibles : ");
            int newPlaces = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne


            volExist.setDeparture(newDepart);
            volExist.setDestination(newDestination);
            volExist.setDuration(newDuree);
            volExist.setAvailableSeats(newPlaces);

            volService.modifier(volExist);
            System.out.println("Vol modifié avec succès : " + volExist);
        } else {
            System.out.println("Vol introuvable !");
        }
    }

    private static void deleteVol(Scanner scanner, VolService volService) {
        System.out.print("Entrez l'index du vol à supprimer : ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        // Récupération du vol à supprimer
        Vol volToDelete = volService.getall().get(index);
        volService.supprimer(volToDelete);
        System.out.println("Vol supprimé avec succès !");
    }

    private static void listVols(VolService volService) {
        System.out.println("\nListe des vols : ");
        for (Vol vol : volService.getall()) {
            System.out.println(vol);
        }
    }


    private static void addAirLine(Scanner scanner, AirLineService airLineService) {
        System.out.print("Entrez le nom de la compagnie aérienne : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez le pays de la compagnie aérienne : ");
        String pays = scanner.nextLine();

        System.out.print("Entrez le logo de la compagnie aérienne : ");
        String logo = scanner.nextLine();

        AirLine airLine = new AirLine(nom, pays, logo);
        airLineService.ajouter(airLine);
        System.out.println("Compagnie aérienne ajoutée avec succès : " + airLine);
    }

    private static void updateAirLine(Scanner scanner, AirLineService airLineService) {
        System.out.print("Entrez le nom de la compagnie aérienne à modifier : ");
        String nom = scanner.nextLine();

        // Recherche de la compagnie aérienne existante
        AirLine airLineExist = null;
        List<AirLine> airLines = airLineService.getall();
        for (AirLine airLine : airLines) {
            if (airLine.getNom().equals(nom)) {
                airLineExist = airLine;
                break;
            }
        }

        if (airLineExist != null) {
            System.out.print("Entrez le nouveau nom de la compagnie : ");
            String newNom = scanner.nextLine();

            System.out.print("Entrez le nouveau pays de la compagnie : ");
            String newPays = scanner.nextLine();

            System.out.print("Entrez le nouveau logo de la compagnie : ");
            String newLogo = scanner.nextLine();


            airLineExist.setNom(newNom);
            airLineExist.setPays(newPays);
            airLineExist.setLogo(newLogo);

            airLineService.modifier(airLineExist);
            System.out.println("Compagnie aérienne modifiée avec succès : " + airLineExist);
        } else {
            System.out.println("Compagnie aérienne introuvable !");
        }
    }

    private static void deleteAirLine(Scanner scanner, AirLineService airLineService) {
        System.out.print("Entrez le nom de la compagnie aérienne à supprimer : ");
        String nomAirLine = scanner.nextLine(); // Lire le nom de la compagnie aérienne


        AirLine airLineToDelete = null;
        for (AirLine airLine : airLineService.getall()) {
            if (airLine.getNom().equalsIgnoreCase(nomAirLine)) {
                airLineToDelete = airLine;
                break;
            }
        }

        if (airLineToDelete != null) {
            airLineService.supprimer(airLineToDelete);
            System.out.println("Compagnie aérienne " + nomAirLine + " supprimée avec succès !");
        } else {
            System.out.println("Erreur : Aucune compagnie aérienne trouvée avec ce nom.");
        }
    }


    private static void listAirLines(AirLineService airLineService) {
        System.out.println("\nListe des compagnies aériennes : ");
        for (AirLine airLine : airLineService.getall()) {
            System.out.println(airLine);
        }
    }
}
