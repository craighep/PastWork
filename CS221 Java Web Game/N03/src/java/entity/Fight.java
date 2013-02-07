package entity;

import javax.persistence.*;

/**
 *
 * @author James Bowcott
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Fight.findAllFightsForUserID",
    query = "SELECT r FROM Fight r WHERE r.localUserID = :userID"),
    @NamedQuery(name = "Fight.findFightsForUserID",
    query = "SELECT r FROM Fight r WHERE r.localUserID = :userID AND r.status = :status"),
    @NamedQuery(name = "Fight.findCompletedFightsForUserID",
    query = "SELECT r FROM Fight r WHERE r.localUserID = :userID AND r.status > 2"),
    @NamedQuery(name = "Fight.findByFightID",
    query="SELECT r FROM Fight r WHERE r.fightID = :fightID"),
    @NamedQuery(name = "Fight.findByLocalMonsterID",
    query="SELECT r FROM Fight r WHERE r.localMonsterID = :monsterID")
})
public class Fight extends AbstractEntity {
        
    public static enum FightStatus {
	RECEIVED, SENT, REJECTED, WON, LOST
    }
    
    /* Relationships */
    
    private String localMonsterID;
    private String localUserID;
    private String remoteMonsterID;
    private String remoteUserID;
    private Integer remoteServerNumber;
    
    /* Fields */
    
    private String fightID;
    
    private Integer status;
    
    /* Constructors */
    
    public Fight() {
	super();
	this.fightID = AbstractEntity.generateUID();
    }

    public Fight(
	    LocalMonster localMonster,
	    Server remoteServer,
	    String remoteMonsterID,
	    String remoteUserID,
	    FightStatus status) {
	this();
	this.localMonsterID = localMonster.getMonsterID();
	this.localUserID = localMonster.getUserID();
	this.remoteMonsterID = remoteMonsterID;
	this.remoteServerNumber = remoteServer.getServerNumber();
	this.remoteUserID = remoteUserID;
	this.status = status.ordinal();
    }


    
    /* Accessors */

    public String getLocalMonsterID() {
	return localMonsterID;
    }

    public void setLocalMonsterID(String localMonsterID) {
	this.localMonsterID = localMonsterID;
    }   

    public String getRemoteMonsterID() {
	return remoteMonsterID;
    }

    public void setRemoteMonsterID(String remoteMonsterID) {
	this.remoteMonsterID = remoteMonsterID;
    }

    public String getFightID() {
	return fightID;
    }

    public void setFightID(String fightID) {
	this.fightID = fightID;
    }

    public Integer getStatus() {
	return status;
    }

    public void setStatus(Integer status) {
	this.status = status;
    }
    
    public void setStatus(FightStatus status) {
	this.status = status.ordinal();
    }

    public Integer getRemoteServerNumber() {
	return remoteServerNumber;
    }

    public void setRemoteServerNumber(Integer remoteServerNumber) {
	this.remoteServerNumber = remoteServerNumber;
    }

    public String getRemoteUserID() {
	return remoteUserID;
    }

    public void setRemoteUserID(String remoteUserID) {
	this.remoteUserID = remoteUserID;
    }
    
    public boolean getWasWon() {
	return this.status == FightStatus.WON.ordinal();
    }
    
    public boolean getIsComplete() {
	return (this.status == FightStatus.WON.ordinal() ||
		this.status == FightStatus.LOST.ordinal());
    }

    public String getLocalUserID() {
	return localUserID;
    }

    public void setLocalUserID(String localUserID) {
	this.localUserID = localUserID;
    }
    
    
    
}
