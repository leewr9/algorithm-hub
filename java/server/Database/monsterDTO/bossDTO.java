package monsterDTO;

import java.io.Serializable;
import java.util.Random;

public class bossDTO extends superMonsterDTO implements Serializable {

	@Override
	public String attack() {
		Random r = new Random();
		String[] atk = { "���Ǽ���", "�벿������", "����������", "����������" };

		return atk[r.nextInt(atk.length)];
	}

}
