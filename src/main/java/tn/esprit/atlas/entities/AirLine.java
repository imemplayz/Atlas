package tn.esprit.atlas.entities;

public class AirLine {
    private int id;
    private String nom;
    private String pays;
    private String logo;



    public AirLine() {
    }


    public AirLine(int id, String nom, String pays, String logo) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
        this.logo = logo;

    }

    public AirLine( String nom, String pays, String logo) {
        this.nom = nom;
        this.pays = pays;
        this.logo = logo;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }



    public void afficherDetails() {
        System.out.println("Compagnie: " + nom + " | Pays: " + pays );
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AirLine compagnie = (AirLine) obj;
        return id == compagnie.id && nom.equals(compagnie.nom);
    }


    @Override
    public String toString() {
        return "CompagnieAerienne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", pays='" + pays + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}

