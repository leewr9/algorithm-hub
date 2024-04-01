package monsterDTO;

import java.io.Serializable;
import java.util.Random;

public class skyDTO extends superMonsterDTO implements Serializable {

	@Override
	public String attack() {
		Random r = new Random();
		String[] atk = { "부리공격", "날개짓" };

		return atk[r.nextInt(atk.length)];
	}

}
