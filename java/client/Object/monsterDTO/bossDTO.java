package monsterDTO;

import java.io.Serializable;
import java.util.Random;

public class bossDTO extends superMonsterDTO implements Serializable {

	@Override
	public String attack() {
		Random r = new Random();
		String[] atk = { "¿ëÀÇ¼û°á", "¿ë²¿¸®°ø°İ", "¹ßÅéÇÒÄû±â", "¿©ÀÇÁÖÆø¹ß" };

		return atk[r.nextInt(atk.length)];
	}

}
