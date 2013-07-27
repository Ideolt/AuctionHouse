package auctionHouse.handlers.command;

import auctionHouse.handlers.AuctionHandler;
import auctionHouse.handlers.EcoHandler;
import auctionHouse.handlers.IdHandler;
import net.minecraft.command.ICommandSender;

public class BidHandler 
{
	public static String cmdDesc = "Use /ah bid <ID> <price> to bid for a specific auction";
	
	public static void handleBid(ICommandSender var1, String[] var2) 
	{
		
		String cmdDesc = "Use /ah bid <ID> <price> to bid for a specific auction";

		if(var2.length < 3)
		{
			var1.sendChatToPlayer("Too few arguments in your command");
			var1.sendChatToPlayer(cmdDesc);
			return;
		}
		
		int auctionId = 0;
		long bidPrice = 0;
		
		if(AuctionHandler.isNumber(var2[1]))
			auctionId = Integer.parseInt(var2[1]);
		else
		{
			var1.sendChatToPlayer("Bad command imput");
			var1.sendChatToPlayer(cmdDesc);
			return;
		}
		
		if(AuctionHandler.isNumber(var2[2]))
			bidPrice = Long.parseLong(var2[2]);
		else
		{
			var1.sendChatToPlayer("Bad command imput");
			var1.sendChatToPlayer(cmdDesc);
			return;
		}

		String name = var1.getCommandSenderName();
		
		if(AuctionHandler.getAuction(auctionId) == null)
		{
			var1.sendChatToPlayer("There are no auctions by that ID");
			return;
		}
		
		if(AuctionHandler.getAuction(auctionId).ownerName.equalsIgnoreCase(name))
		{
			var1.sendChatToPlayer("You can't bid on your own auctions...");
			return;
		}
		
		if(AuctionHandler.getAuction(auctionId).priceNow >= bidPrice)
		{
			var1.sendChatToPlayer("Your bid is too low, bid more to participate in the auction");
			return;
		}
		
		if(bidPrice > EcoHandler.getBalance(IdHandler.returnId(name)))
		{
			
			 var1.sendChatToPlayer("Not enough cash... Get more money, try again.");
			 return;
		}
		
		if(AuctionHandler.getAuction(auctionId).timeLeft == 0)
		{
			var1.sendChatToPlayer("The auction is over, no more bids allowed.");
			return;			
		}
		
		//returns the reservation of money if needed
		if(AuctionHandler.getAuction(auctionId).ownerName != AuctionHandler.getAuction(auctionId).winnerName)
		{
			long resedMoney = AuctionHandler.getAuction(auctionId).priceNow;
			int moneyOwner = IdHandler.returnId(AuctionHandler.getAuction(auctionId).winnerName);
			EcoHandler.setBalance(moneyOwner, EcoHandler.getBalance(moneyOwner)+resedMoney);
		}

		//reserves the new money amount
		EcoHandler.setBalance(IdHandler.returnId(name),EcoHandler.getBalance(IdHandler.returnId(name)) - bidPrice);
		
		AuctionHandler.getAuction(auctionId).setPrice(bidPrice);
		AuctionHandler.getAuction(auctionId).setWinner(name);
		
	}

}
