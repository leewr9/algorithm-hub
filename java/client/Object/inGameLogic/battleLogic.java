package inGameLogic;

import java.util.Random;

import anyDTO.playerDTO;
import monsterDTO.superMonsterDTO;

public class battleLogic {
	private startLogic start = null;// ��ü ���� �Ŀ� �Է�
	private superMonsterDTO monster = null;// ��ü ���� �Ŀ� �Է�

	private playerDTO player = null;// �Է¹޴� ��ŸƮ���� �ҷ�����

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
		}//���͸� ��ġ���� ��� �����۰� ����ġ �Է� �� ����
		return null;
	}// �̱�ų� ���ų� ���� ��� �������ִ� �޼ҵ�

	public boolean battle() {// ��ư �������� ����
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

	public boolean counter() {// 2ȸ ���ݸ��� ���� ����
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
		int[] sucess = { 1, 1, 1, 1, 0, 0, 1, 1, 1, 1 };// 1�� �ɸ� ��� ���ݼ���, 0�� �ɸ���� ���� ����
		return sucess[r.nextInt(sucess.length)];
	}

	public String root() {//������ 3�� �߿��� �� �� ���� ���
		Random r = new Random();
		return this.monster.getList()[r.nextInt(3)];
	}

	
}
