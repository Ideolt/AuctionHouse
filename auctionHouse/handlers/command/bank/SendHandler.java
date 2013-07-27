package auctionHouse.handlers.command.bank;

import auctionHouse.handlers.AuctionHandler;
import auctionHouse.handlers.EcoHandler;
import auctionHouse.handlers.IdHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class SendHandler {

	public static String cmdDesc = "Use /bank send <playerName> <amount> to send money ";
	public static String cmdDesc2 = "from your balance to another player";
	
	public static void handleSend(ICommandSender var1, String[] var2) {
		
		String SenderName = var1.getCommandSenderName();
		
		long amount = 0;
		
		if(AuctionHandler.isNumber(var2[2]))//Checking for bad input [or dumbasses...]
			amount = Long.parseLong(var2[2]);
		else
		{
			var1.sendChatToPlayer("bad command imput");
			var1.sendChatToPlayer(cmdDesc);
			var1.sendChatToPlayer(cmdDesc2);
			return;
		}
		
		String ReceiverName = var2[1];
		
		if(!(var1 instanceof EntityPlayer))
		{
			EcoHandler.setBalance(IdHandler.returnId(ReceiverName), EcoHandler.getBalance(IdHandler.returnId(ReceiverName)) + amount);
			EcoHandler.saveBalance(IdHandler.returnId(ReceiverName));
			return;
		}
		
		if(var2.length < 2)
		{
			var1.sendChatToPlayer("To few arguments in your command");
			var1.sendChatToPlayer(cmdDesc);
			var1.sendChatToPlayer(cmdDesc2);
			return;
		}
		
		if(EcoHandler.getBalance(IdHandler.returnId(SenderName)) < amount)
		{
			var1.sendChatToPlayer("Not enoth money in your acount to process command");
			return;
		}
		
		if(IdHandler.getId(ReceiverName) == 0)
		{
			var1.sendChatToPlayer("Unknown player you want to send money to");
			return;
		}
		
		EcoHandler.setBalance(IdHandler.returnId(SenderName), EcoHandler.getBalance(IdHandler.returnId(SenderName)) - amount);
		EcoHandler.setBalance(IdHandler.returnId(ReceiverName), EcoHandler.getBalance(IdHandler.returnId(ReceiverName)) + amount);
		EcoHandler.saveBalance(IdHandler.returnId(SenderName));
		EcoHandler.saveBalance(IdHandler.returnId(ReceiverName));
		
		var1.sendChatToPlayer("Transaction Complete!");
		
		System.out.println("[AH Bank] Player "+SenderName+" has send player "+ReceiverName+"  "+amount+" Credits");
		return;
		
	}

}
