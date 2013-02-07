package entity;

/**
 *
 * @author James Bowcott
 */
public class RemoteMonster extends Monster {
    
    /* Fields */
    public String
	    monsterID, userID;
    public Double
	    baseStrength, currentStrength,
	    baseDefence, currentDefence,
	    baseHealth, currentHealth;
    public Integer
	    birthDate, lifespan,
	    breedOffer, saleOffer;
    
    public Server server;

    /* Accessors */
    
    public Server getServer() {
	return server;
    }
    
    public void setServer(Server server) {
	this.server = server;
    }
    
    @Override
    public String getUserID() {
	return this.userID;
    }

    @Override
    public String getMonsterID() {
	return monsterID;
    }

    public void setMonsterID(String monsterID) {
	this.monsterID = monsterID;
    }

    @Override
    public Double getBaseStrength() {
	return baseStrength;
    }

    @Override
    public Double getCurrentStrength() {
	return currentStrength;
    }

    @Override
    public Double getBaseDefence() {
	return baseDefence;
    }

    @Override
    public Double getCurrentDefence() {
	return currentDefence;
    }

    @Override
    public Double getBaseHealth() {
	return baseHealth;
    }

    @Override
    public Double getCurrentHealth() {
	return currentHealth;
    }

    @Override
    public Integer getBirthDate() {
	return birthDate;
    }

    @Override
    public Integer getLifespan() {
	return lifespan;
    }

    @Override
    public Integer getBreedOffer() {
	return breedOffer;
    }

    @Override
    public Integer getSaleOffer() {
	return saleOffer;
    }

    @Override
    public void setBaseStrength(Double baseStrength) {
	this.baseStrength = baseStrength;
    }

    @Override
    public void setCurrentStrength(Double currentStrength) {
	this.currentStrength = currentStrength;
    }

    @Override
    public void setBaseDefence(Double baseDefence) {
	this.baseDefence = baseDefence;
    }

    @Override
    public void setCurrentDefence(Double currentDefence) {
	this.currentDefence = currentDefence;
    }

    @Override
    public void setBaseHealth(Double baseHealth) {
	this.baseHealth = baseHealth;
    }

    @Override
    public void setCurrentHealth(Double currentHealth) {
	this.currentHealth = currentHealth;
    }


}
