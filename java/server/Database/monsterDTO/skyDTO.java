package monsterDTO;

import java.io.Serializable;
import java.util.Random;

public class skyDTO extends superMonsterDTO implements Serializable {

	@Override
	public String attack() {
		Random r = new Random();
		String[] atk = { "�θ�����", "������" };

		return atk[r.nextInt(atk.length)];
	}

}
