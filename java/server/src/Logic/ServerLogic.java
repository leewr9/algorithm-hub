package Logic;

import java.util.Vector;

import INF.allINFImpl;
import anyDTO.logDTO;

public class ServerLogic {
	private allINFImpl inf = new allINFImpl();
	private static ServerLogic server = null;

	private ServerLogic() {

	}

	public static ServerLogic getInstance() {
		if (server == null)
			server = new ServerLogic();
		return server;
	}
	
	public Vector<logDTO> findLog(String id){
		Vector<logDTO> player = inf.playerLog(id);
		return player;
	}
	
	public void logUpdate(logDTO l) {
		inf.logUpdate(l);
	}

}
