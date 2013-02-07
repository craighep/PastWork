package entity;

import java.util.Date;
import javax.persistence.*;

/**
 * @author James Bowcott
 */
@MappedSuperclass
public abstract class Monster extends AbstractEntity {
    public abstract String getMonsterID();
    public abstract String getUserID();
    public abstract Double getBaseStrength();
    public abstract Double getCurrentStrength();
    public abstract Double getBaseDefence();
    public abstract Double getCurrentDefence();
    public abstract Double getBaseHealth();
    public abstract Double getCurrentHealth();
    public abstract Integer getBirthDate();
    public abstract Integer getLifespan();
    public abstract Integer getBreedOffer();
    public abstract Integer getSaleOffer();
    
    public abstract void setBaseStrength(Double baseStrength);
    public abstract void setCurrentStrength(Double currentStrength);
    public abstract void setBaseDefence(Double baseDefence);
    public abstract void setCurrentDefence(Double currentDefence);
    public abstract void setBaseHealth(Double baseHealth);
    public abstract void setCurrentHealth(Double currentHealth);

    
    public Double getAge() {	
	Double d = (getCurrentTime() - getBirthDate()) / (double) getLifespan();
	return d;
    }
    
    public Integer getCurrentTime() {
	return new Long(new Date().getTime()/1000).intValue();
    }
    
    public Date getBirthday() {
	Date d = new Date();
	d.setTime(getBirthDate().longValue()*1000);
	return d;
    }
    
    public boolean isDead() {
	return (this.getAge() >= 1.0 || this.getCurrentHealth() <= 0.0);
    }
}
