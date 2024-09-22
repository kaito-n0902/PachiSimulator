package model;

import java.util.Random;

public class PachiLogicRezero {
int ball = 0; //獲得出玉 
Random rand = new Random();
	
	public void execute(Pachi pachi) {
		int money = pachi.getMoney();
		int firstBonusCnt = 0; //初当たりの回数
		int usedMoney = 1000; //使ったお金
		int playableCnt = money * 17 / 1000; //プレイ可能な回転数（1kあたりのボーダー＊金額）（金額＊ボーダー/1000）
			
		int bonus = rand.nextInt(319); //当たりの数字を生成
		    
		//選択した金額がなくなるまでランダム生成した数字を当たりの数字と比較し続ける
		for(int i = 0; i < playableCnt; i++) {
		    int play = rand.nextInt(319);
		    
		  //iがボーダーを超えるごとにusedMoneyに+1000
		    if(i % 17 == 0) {
		    	usedMoney += 1000;
		    	if(usedMoney > money) {
		    		usedMoney -= 1000;
		    	}
		    }
		    
		  //当たったら55%で確変突入、確変が終わったら残金残して終了
		    if(bonus == play) {
		    	firstBonusCnt++;
		    	int rush = rand.nextInt(100);
		    	if(rush < 55) {
		    		bonusTime(pachi);
		    		pachi.setBall(ball);
		    		break;
		    	}
		    }
		}
		
		//初当たりを何回引けたか
		pachi.setFirstBonusCnt(firstBonusCnt);
		
		//何円使ったか
		pachi.setUsedMoney(usedMoney);
	}
	
	//確変時の動作
	public void bonusTime(Pachi pachi) {
		int contiRate = 77; //継続率
		int petitBonusCnt = 0;
		int mediumBonusCnt = 0;
		int bigBonusCnt = 0;
		int maxBonusCnt = 0;
		ball += 3000;
		
		while(true) {
			//特図2の振り分け
			int whichBonus = rand.nextInt(100);	
			if(whichBonus < 14) {
				ball += 0;
				petitBonusCnt++;
			}else if(whichBonus < 20) {
				ball += 300;
				mediumBonusCnt++;
			}else if(whichBonus < 75) {
				ball += 1500;
				bigBonusCnt++;
			}else if(whichBonus < 100) {
				ball += 3000;
				maxBonusCnt++;
			}
			
			//継続率以上の数字が出たら確変終了
			int fin = rand.nextInt(100);
			if(contiRate < fin) {
				//それぞれの当たりを何回ずつ引いたかカウント
				pachi.setPetitBonus(petitBonusCnt);
				pachi.setMediumBonus(mediumBonusCnt);
				pachi.setBigBonus(bigBonusCnt);
				pachi.setMaxBonus(maxBonusCnt);
				break;
			}
		}
	}
}
