package JavaClasses;


/**
 *
 * @author z.zafar
 */
public class User {

    private String nom;
    private String prenom;
    private int age;
    private String login;
    private String mdp;

    public User(String nom, String prenom, int age, String login, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.login = login;
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

}
