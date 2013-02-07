package entity;

import java.math.BigInteger;
import java.util.Date;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 *
 * @author James Bowcott
 */
@Entity
@NamedQueries({
    @NamedQuery(name="LocalMonster.findByMonsterID", query = "SELECT m FROM LocalMonster m WHERE m.monsterID = :monsterID"),
    @NamedQuery(name="LocalMonster.findByUserID", query = "SELECT m FROM LocalMonster m WHERE m.userID = :userID")
})
public class LocalMonster extends Monster {
    
    private String monsterID;
    private String userID;
    
    private Double baseStrength;
    private Double baseDefence;
    private Double baseHealth;
    private Double currentHealth;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date birthDate;
    private Integer lifespan;
    
    private Integer breedOffer;
    private Integer saleOffer;
    
    private Boolean isMale;

    public LocalMonster() {
	super();
	Random rand = new Random();
	
	this.monsterID = new BigInteger(60, rand).toString(16);
	
	this.baseDefence = rand.nextDouble();
	this.baseStrength = rand.nextDouble();
	this.baseHealth = rand.nextDouble();
	this.lifespan = 10 + rand.nextInt(1000);
	this.birthDate = new Date();
	this.breedOffer = 0;
	this.saleOffer = 0;
	this.currentHealth = 1.0;
	this.isMale = rand.nextBoolean();
    }
    
    public LocalMonster(String userID) {
	this();
	this.userID = userID;
    }
    
    public void setUserID(String userID) {
	this.userID = userID;
    }

    @Override
    public String getMonsterID() {
	return this.monsterID;
    }
    
    public void setMonsterID(String monsterID) {
	this.monsterID = monsterID;
    }


    @Override
    public String getUserID() {
	return userID;
    }

    @Override
    public Double getBaseStrength() {
	return baseStrength;
    }

    @Override
    public Double getCurrentStrength() {
	return calcStat(getBaseStrength(), getAge());
    }

    @Override
    public Double getBaseDefence() {
	return this.baseDefence;
    }

    @Override
    public Double getCurrentDefence() {
	return calcStat(getBaseDefence(), getAge());
    }

    @Override
    public Double getBaseHealth() {
	return this.baseHealth;
    }

    @Override
    public Double getCurrentHealth() {
	return this.currentHealth;
    }

    @Override
    public Integer getBirthDate() {
	return new Long(this.birthDate.getTime()/1000).intValue();
    }

    @Override
    public Integer getLifespan() {
	return this.lifespan;
    }

    @Override
    public Integer getBreedOffer() {
	return this.breedOffer;
    }

    @Override
    public Integer getSaleOffer() {
	return this.saleOffer;
    }

    @Override
    public void setBaseStrength(Double baseStrength) {
	this.baseStrength = baseStrength;
    }

    @Override
    public void setCurrentStrength(Double currentStrength) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setBaseDefence(Double baseDefence) {
	this.baseDefence = baseDefence;
    }

    @Override
    public void setCurrentDefence(Double currentDefence) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setBaseHealth(Double baseHealth) {
	this.baseHealth = baseHealth;
    }

    @Override
    public void setCurrentHealth(Double currentHealth) {
	this.currentHealth = currentHealth;
    }
    
    public void setSaleOffer(Integer saleOffer) {
	this.saleOffer = saleOffer;
    }
    
    public void setBreedOffer(Integer breedOffer) {
	this.breedOffer = breedOffer;
    }
    
    public boolean getIsMale() {
	return this.isMale;
    }
    
    public static Double calcStat(Double stat, Double age) {
	Double d = stat - Math.pow((age - 0.5),2) * (2 / (1 / stat));
	if (d < 0.0) {
	    return 0.0;
	} else if (d > 1.0) {
	    return 1.0;
	} else { 
	    return d;
	}
    }

    public void setBirthDate(Integer birthDate) {
			Date d = new Date();
	d.setTime(getBirthDate().longValue()*1000);

	this.birthDate = d;
    }

    public void setLifespan(Integer lifespan) {

	this.lifespan = lifespan;
    }

}
