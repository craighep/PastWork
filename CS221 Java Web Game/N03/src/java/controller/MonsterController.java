package controller;

import entity.LocalMonster;
import entity.Monster;
import java.util.Random;

public class MonsterController {
            
    /* Methods */
    
    public static Monster fight(Monster monster1, Monster monster2){
        Random randomDmg = new Random(); //random float number added to damage
        float dmg;
	Double m1Health = monster1.getCurrentHealth();
	Double m2Health = monster2.getCurrentHealth();
        
        while (m1Health > 0.0 || m2Health > 0.0) {
            /*Monster1 makes first hit*/
	    
            m2Health -= ((monster1.getCurrentStrength() / monster2.getCurrentDefence()) / 10) * randomDmg.nextFloat();
            
            /*Cheking if after hit monster2 has died, and if its dead, break loop*/
            if(m2Health <= 0.0){
                break;
            }
	    
	    m1Health -= ((monster2.getCurrentStrength() / monster1.getCurrentDefence()) / 10) * randomDmg.nextFloat();
        }
	
	monster1.setCurrentHealth(m1Health);
	monster2.setCurrentHealth(m2Health);

        return m1Health <= 0.0 ? monster1 : monster2;
    }
    
    public static LocalMonster breed(Monster m1, Monster m2) {
        LocalMonster newMonster = new LocalMonster();
	newMonster.setMonsterID(m1.getMonsterID() + " - " + m2.getMonsterID());
        newMonster.setBaseStrength(Math.abs(m1.getBaseStrength() - m2.getBaseStrength()));
        newMonster.setBaseDefence(Math.abs(m1.getBaseDefence() - m2.getBaseDefence()));
        newMonster.setBaseHealth(Math.abs(m1.getBaseHealth() - m2.getBaseHealth()));
	newMonster.setUserID(m1.getUserID());
        return newMonster;
    }
    
}
