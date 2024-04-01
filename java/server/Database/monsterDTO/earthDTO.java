package monsterDTO;

import java.io.Serializable;
import java.util.Random;

public class earthDTO extends superMonsterDTO implements Serializable {

	@Override
	public String attack() {
		Random r = new Random();
		String[] atk = { "ÇÒÄû±â", "¸öÅë¹ÚÄ¡±â" };

		return atk[r.nextInt(atk.length)];
	}

}
