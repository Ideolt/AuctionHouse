package auctionHouse.handlers.command;

import auctionHouse.handlers.AuctionHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CancelHandler 
{
	public static String cmdDesc = "Use /ah cancel <auctionId> to cancel an auction that belongs to you ";
	public static String cmdDesc2 ="WARNING! if some one has already bided on it, it will not be canceled!";
	
	public static void handleCancel(ICommandSender var1, String[] var2) 
	{

		String name = var1.getCommandSenderName();
		
		if(var2.length < 2)
		{
			var1.sendChatToPlayer("Too few arguments in your command");
			var1.sendChatToPlayer(cmdDesc);
			var1.sendChatToPlayer(cmdDesc2);

			return;
		}
		
		int auctionId = 0;
		
		if(AuctionHandler.isNumber(var2[1]))
			auctionId = Integer.parseInt(var2[1]);
		else
		{
			var1.sendChatToPlayer("Bad command input");
			var1.sendChatToPlayer(cmdDesc);
			var1.sendChatToPlayer(cmdDesc2);
			return;
		}
		
		if(!(var1 instanceof EntityPlayer))
		{
			AuctionHandler.getAuction(auctionId).setPrice(0);
			AuctionHandler.getAuction(auctionId).setWinner(AuctionHandler.getAuction(auctionId).ownerName);
			AuctionHandler.getAuction(auctionId).setTime(0);
		}
		
		if(AuctionHandler.getAuction(auctionId).ownerName != name)
		{
			var1.sendChatToPlayer("You can't cancel an auction that does not belong to you");
			return;
		}
		
		if(!AuctionHandler.getAuction(auctionId).winnerName.equalsIgnoreCase(name))
		{
			var1.sendChatToPlayer("Some one already bided on your auction, you can't cancel it now.");
			return;
		}
		
		AuctionHandler.getAuction(auctionId).setPrice(0);
		AuctionHandler.getAuction(auctionId).setTime(0);
		
	}

}
