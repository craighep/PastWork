package entity;

import controller.UserController;
import javax.persistence.*;

/**
 *
 * @author James Bowcott
 */
@Entity
@Table(name="USERS")
@NamedQuery(name = "User.findByUserID", query="SELECT u FROM User u WHERE u.userID = :userID")
public class User extends AbstractEntity {
    
    /* Relationships */
                
    /* Fields */
    
    @Column(unique=true)
    @Basic(optional=false)
    private String userID;

    private String name;
    
    private String avatarURL = "images/monster.png";
        
    private Integer money = 1000;
    
    private String passwordHash;
    
    private Boolean isAdmin;
    
    /* Constructors */

    public User() {
	super();
	this.isAdmin = false;
    }
    
    public User(String userID, String name) {
	this();
	this.userID = userID;
	this.name = name;
    }
    
    public User(String userID, String name, String password) {
	this(userID, name);
	this.passwordHash = UserController.hashPassword(password);
    }
    
    public User(String userID, String name, String password, String avatarURL) {
	this(userID, name, password);
	this.avatarURL = avatarURL;
    }
    
    /* Accessors */

    public String getUserID() {
	return userID;
    }

    public void setUserID(String userID) {
	this.userID = userID;
    }
    
    public String getAvatarURL(){
        return avatarURL;
    }
    
    public void setAvatarURL(String avatarURL){
        this.avatarURL = avatarURL;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Integer getMoney() {
	return money;
    }

    public void setMoney(Integer money) {
	this.money = money;
    }
    
        public Boolean getIsAdmin() {
	return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
	this.isAdmin = isAdmin;
    }
    
    public String getPasswordHash() {
	return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
	this.passwordHash = passwordHash;
    }
        
    /* Methods */
    
    public void setPassword(String password) {
	this.passwordHash = UserController.hashPassword(password);
    }
    
}