package com.team6.app;

import org.springframework.data.annotation.Id;

public class Character {

	@Id //Fields of Character table/collection
	private String characterName;
	private String character;
	private int level;
	private int experience;
	private int health;
	private int str;
	private int def;
	private double crit_chance;
	private static final int levelMax = 100;
	public Character (String character, String characterName)
	{
		//Initialize column fields with parameter values
		this.characterName = characterName;
		this.character = character;
		this.level = 1;
		this.experience = 50;
		this.health = 150;
		this.str = 1;
		this.def = 1;
		this.crit_chance = (5/100) + (this.level/(Character.levelMax*20));
	}
	
	public String getCharacterName() {
        return this.characterName;
    }
	
	public String getCharacterType() {
		return this.character;
	}
	
    public int getLevel() {
        return this.level;
    }
    
    public int getExperience() {
    	return this.experience;
    }
    
    public int getHealth() {
    	return this.health;
    }
    
    public int getStr() {
    	return this.str;
    }
    
    public int getDef() {
    	return this.def;
    }
    
    public double getCritChance()
    {
    	return this.crit_chance;
    }
}
