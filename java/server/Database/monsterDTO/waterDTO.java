package monsterDTO;

import java.io.Serializable;
import java.util.Random;

public class waterDTO extends superMonsterDTO implements Serializable  {

	@Override
	public String attack() {
		Random r = new Random();
		String[] atk = {"물대포", "지느러미공격"};
		
 		return atk[r.nextInt(atk.length)];
	}

}
