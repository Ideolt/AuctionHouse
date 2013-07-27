package auctionHouse.handlers.command.bank;

import auctionHouse.handlers.EcoHandler;
import auctionHouse.handlers.IdHandler;
import net.minecraft.command.ICommandSender;

public class BalanceHandler {

	public static String cmdDesc = "Use /bank balance to see the remainder of money in your balance";
	
	public static void handleBalance(ICommandSender var1, String[] var2) {
		
		if(var1.getCommandSenderName().equalsIgnoreCase("Rcon"))
		{	
			var1.sendChatToPlayer("You can't use this command from the console...");
			return;
		}
		
		var1.sendChatToPlayer("- - - - - - - - - AHBank - - - - - - - - -");
		var1.sendChatToPlayer("Your remaining money: "+EcoHandler.getBalance(IdHandler.returnId(var1.getCommandSenderName()))+" Credits");
		var1.sendChatToPlayer("- - - - - - - - - AHBank - - - - - - - - -");
		return;
		
	}

}
