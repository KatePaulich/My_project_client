package sample.admin;

/**
 *  User class for storing user information
 */
public class User {
    /**
     * id of a user
     */
    private int id;
    /**
     * role of a user
     */
    private String role;
    /**
     * nameuser of a user
     */
    private String nameuser;
    /**
     * surnameuser of a user
     */
    private String surnameuser;
    /**
     * patronymicuser of a user
     */
    private String patronymicuser;

    /**
     * This a constructor to initialize user object
     * @param id an initial user id
     * @param role an initial user role
     * @param nameuser an initial user nameuser
     * @param surnameuser an initial user surnameuser
     * @param patronymicuser an initial user patronymicuser
     */
    public User(int id, String role, String nameuser, String surnameuser,
                String patronymicuser){
        this.id = id;
        this.role = role;
        this.nameuser = nameuser;
        this.surnameuser = surnameuser;
        this.patronymicuser = patronymicuser;
    }

    /**
     * Get user id
     * @return user id
     */
    public int getId() { return id; }

    /**
     * To set an id of a user
     * @param id a user id
     */
    public void setId(int id) { this.id = id; }

    /**
     * Get user role
     * @return user role
     */
    public String getRole() { return role; }

    /**
     * Get user nameuser
     * @return user nameuser
     */
    public String getNameuser() { return nameuser; }

    /**
     * Get user surnameuser
     * @return user surnameuser
     */
    public String getSurnameuser() { return surnameuser; }

    /**
     * Get user patronymicuser
     * @return user patronymicuser
     */
    public String getPatronymicuser() { return patronymicuser; }
}
