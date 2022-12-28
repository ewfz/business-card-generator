package ewfz.project.entities;
import java.util.Date;

public class User {
    private String Nom;
    private String NomArabe;
    private String Prenom;
    private String PrenomArabe;
    private String CIN;
    private String Profession;
    private Date DateDeNaissance;
    private String TypeCarte;
    private String adresse;

    public User(){}

    public User(String nom, String nomArabe, String prenom, String prenomArabe, String CIN, String profession, Date dateDeNaissance, String typeCarte, String adresse) {
        Nom = nom;
        NomArabe = nomArabe;
        Prenom = prenom;
        PrenomArabe = prenomArabe;
        this.CIN = CIN;
        Profession = profession;
        DateDeNaissance = dateDeNaissance;
        TypeCarte = typeCarte;
        adresse = adresse;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getNomArabe() {
        return NomArabe;
    }

    public void setNomArabe(String nomArabe) {
        NomArabe = nomArabe;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getPrenomArabe() {
        return PrenomArabe;
    }

    public void setPrenomArabe(String prenomArabe) {
        PrenomArabe = prenomArabe;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getProfession() {
        return Profession;
    }

    public void setProfession(String profession) {
        Profession = profession;
    }

    public Date getDateDeNaissance() {
        return DateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        DateDeNaissance = dateDeNaissance;
    }

    public String getTypeCarte() {
        return TypeCarte;
    }

    public void setTypeCarte(String typeCarte) {
        TypeCarte = typeCarte;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "User{" +
                "Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", CIN='" + CIN + '\'' +
                ", DateDeNaissance=" + DateDeNaissance +
                '}';
    }
}
