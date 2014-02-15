package com.team6.app;

import org.springframework.data.annotation.Id;

public class Character {

	private String characterName;
	private String character;
	private int level;
	private int levelThreshold;
	private int experience;
	private int health;
	private int str;
	private int def;
	private double crit_chance;
	private boolean charged;
	private static final int levelMax = 100;
	public Character (String character, String characterName)
	{
		//Initialize column fields with parameter values
		this.characterName = characterName;
		this.character = character;
		this.level = 1;
		this.experience = 0;
		this.levelThreshold = 5;
		this.health = 150;
		this.str = 1;
		this.def = 1;
		this.crit_chance = (5/100) + (this.level/(Character.levelMax*20));
		this.charged = true;
	}
	
	public String getCharacterName() {
        return this.characterName;
    }
	
	public String getCharacter() {
		return this.character.toLowerCase();
	}
	
    public int getLevel() {
        return this.level;
    }
    
    public int getLevelThreshold() {
    	return this.levelThreshold;
    }
    
    public int getExperience() {
    	return this.experience;
    }
    
    public void setExperience(int experience)
    {
    	this.experience = experience;
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
    
    public void checkLevel()
    {
    	if(this.experience >= this.levelThreshold)
    	{
    		this.experience = 0;
    		this.level = this.level + 1;
    		this.levelThreshold = calculateThreshold();
    		this.health += 20;
    		this.str += 5;
    		this.def += 3;
    	}
    }
    
    public int calculateThreshold()
    {
    	return this.level * 5;
    }
    
    public void setname(String name)
    {
    	this.characterName = name;
    }
    
    public void incrementHealth(int amt) {
    	this.health += amt;
    }
    
    public void incrementStr(int amt) {
    	this.str += amt;
    }
    
    public void incrementDef(int amt) {
    	this.def += amt;
    }
    
    public void incrementCrit_Chance(int amt) {
    	this.crit_chance += amt;
    }

	public boolean isCharged() {
		return charged;
	}

	public void setCharged(boolean isCharged) {
		this.charged = isCharged;
	}
}
