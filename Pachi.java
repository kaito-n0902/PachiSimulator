package model;

import java.io.Serializable;

public class Pachi implements Serializable{
	private String model;
	private int money;
	private int ball;
	private int petitBonus;
	private int mediumBonus;
	private int bigBonus;
	private int maxBonus;
	private int firstBonusCnt;
	private int usedMoney;
	
	public Pachi() {}
	public Pachi(String model, int money) {
		this.model = model;
		this.money = money;
	}
	
	public String getModel() {
		return this.model;
	}
	public int getMoney() {
		return this.money;
	}
	public int getBall() {
		return this.ball;
	}
	public void setBall(int ball) {
		this.ball = ball;
	}
	public int getPetitBonus() {
		return this.petitBonus;
	}
	public void setPetitBonus(int petitBonus) {
		this.petitBonus = petitBonus;
	}
	public int getMediumBonus() {
		return this.mediumBonus;
	}
	public void setMediumBonus(int mediumBonus) {
		this.mediumBonus = mediumBonus;
	}
	public int getBigBonus() {
		return this.bigBonus;
	}
	public void setBigBonus(int bigBonus) {
		this.bigBonus = bigBonus;
	}
	public int getMaxBonus() {
		return this.maxBonus;
	}
	public void setMaxBonus(int maxBonus) {
		this.maxBonus = maxBonus;
	}
	public int getFirstBonusCnt() {
		return this.firstBonusCnt;
	}
	public void setFirstBonusCnt(int firstBonusCnt) {
		this.firstBonusCnt = firstBonusCnt;
	}
	public int getUsedMoney() {
		return this.usedMoney;
	}
	public void setUsedMoney(int usedMoney) {
		this.usedMoney = usedMoney;
	}
}
