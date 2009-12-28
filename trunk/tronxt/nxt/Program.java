package tronxt.nxt;

import tronxt.core.*;

public class Program {

	public static void main(String[] args) {
		BTControlledPlayer player = new BTControlledPlayer();
		
		TronBike bike = player.getBike();
		
		bike.addCrashHandler(new CrashHandler() {
			public void tronBikeCrashed(Player player) {
				player.die();
			}
		});
	}

}
