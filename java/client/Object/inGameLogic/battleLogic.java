package inGameLogic;

import java.util.Random;

import anyDTO.playerDTO;
import monsterDTO.superMonsterDTO;

public class battleLogic {
	private startLogic start = null;// 객체 생성 후에 입력
	private superMonsterDTO monster = null;// 객체 생성 후에 입력

	private playerDTO player = null;// 입력받는 스타트에서 불러오기

	private int damage = 0;
	private int counterDamage = 0;
	
	public battleLogic(startLogic s, superMonsterDTO m) {
		this.start = s;
		this.monster = m;
		this.player = this.start.getPlayer();
	}
	
	public int monsterHp() {
		return this.monster.getHp();
	}
	
	public int playerHp() {
		return this.player.getHp();
	}
	

	public String finish() {
		if (this.monster.getHp() == 0) {
			return root();
		}//몬스터를 해치웠을 경우 아이템과 경험치 입력 후 리턴
		return null;
	}// 이기거나 지거나 끝날 경우 리턴해주는 메소드

	public boolean battle() {// 버튼 형식으로 구분
		if (percent() == 1) {
			this.damage = this.player.getAttack() - this.monster.getDefence();
			if(damage < 0) {
				this.player.setHp(this.player.getHp() + damage);
				if (this.player.getHp() < 0) {
					this.player.setHp(0);
				}
				return false;
			}else {
				this.monster.setHp(this.monster.getHp() - damage);
			}
			if (this.monster.getHp() < 0) {
				this.monster.setHp(0);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean counter() {// 2회 공격마다 상대방 공격
		if (percent() == 1) {
			this.counterDamage = this.monster.getAttack() - this.player.getDefence();
			if(counterDamage < 0) {
				this.monster.setHp(this.monster.getHp() + counterDamage);
				if (this.monster.getHp() < 0) {
					this.monster.setHp(0);
				}
				return false;
			}else {
				this.player.setHp(this.player.getHp() - this.counterDamage);
			}
			if (this.player.getHp() < 0) {
				this.player.setHp(0);
			}
			return true;
		} else {
			return false;
		}
	}

	public int percent() {
		Random r = new Random();
		int[] sucess = { 1, 1, 1, 1, 0, 0, 1, 1, 1, 1 };// 1이 걸릴 경우 공격성공, 0이 걸릴경우 공격 실패
		return sucess[r.nextInt(sucess.length)];
	}

	public String root() {//아이템 3개 중에서 한 개 랜덤 드랍
		Random r = new Random();
		return this.monster.getList()[r.nextInt(3)];
	}

	
}
