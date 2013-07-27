package auctionHouse.handlers.command;

import java.util.Iterator;
import java.util.Set;


import auctionHouse.Config;
import auctionHouse.handlers.AuctionHandler;
import net.minecraft.command.ICommandSender;

public class MeHandler {
	
	public static String cmdDesc = "Use   /ah me   to see all the auctions you are participating in";
	
	public static void handleMe(ICommandSender var1, String[] var2) {
		
		String playerName = var1.getCommandSenderName();
		
		if(var2.length > 2)
		{
			var1.sendChatToPlayer("Too many arguments in your command");
			var1.sendChatToPlayer(cmdDesc);
			return;
		}
	
		int[] goodIds = new int[10];
		
		Set AuctionIdSet = AuctionHandler.Auctions.keySet();
		Iterator AuctionIt = AuctionIdSet.iterator();
		
		int x = 0; //temp caunter
		int[] Ids = new int[AuctionHandler.Auctions.size()];
		
		while(AuctionIt.hasNext())
		{
			Ids[x] = (Integer) AuctionIt.next();
			x++;
		}
		
		int auctionsToDisplay = 0;
		
		int auctionId = 0;

		x = 0; //the counter/id checker
		int y = 0; //it needs to rise normaly 0++ and so on [x will jump numbers]
		
		while(x < Ids.length)
		{
			if((AuctionHandler.getAuction(Ids[x]).ownerName.equalsIgnoreCase(playerName) || AuctionHandler.getAuction(Ids[x]).winnerName.equalsIgnoreCase(playerName) ) && y < 10)
			{
				goodIds[y] = Ids[x];
				y++;
			}
			
			x++;
		}
		
		//auction info
		
		String itemName = "";
		int amount = 0;
		long price = 0;
		long timeLeft = 0;
		long buyout = 0;
		String Role = "";//buying/selling
		
		auctionsToDisplay = y;
		
		var1.sendChatToPlayer("- - - - - - - - - AuctionHouse - - - - - - - - -");
		var1.sendChatToPlayer("ID | Item Name | Amount | Price | Time Left | BuyOut Price | Role");
		var1.sendChatToPlayer(" ");
		
		System.out.println("lenght: "+goodIds.length);
		
		if(auctionsToDisplay < 1)
		{	
			var1.sendChatToPlayer(" ");
			var1.sendChatToPlayer("No auctions faund");
			var1.sendChatToPlayer(" ");			
		}
		else
		{
			while(auctionsToDisplay >= 1)
			{
				itemName = AuctionHandler.getAuction(goodIds[auctionId]).itemName;
				amount = AuctionHandler.getAuction(goodIds[auctionId]).itemAmount;
				price = AuctionHandler.getAuction(goodIds[auctionId]).priceNow;
				timeLeft = AuctionHandler.getAuction(goodIds[auctionId]).timeLeft;
				buyout = AuctionHandler.getAuction(goodIds[auctionId]).buyOutPrice;
			
				if(AuctionHandler.getAuction(goodIds[auctionId]).ownerName.equalsIgnoreCase(playerName))
					Role = "Selling";
				else
					Role = "Buying";
			
				if(buyout == 0)
					var1.sendChatToPlayer(goodIds[auctionId]+" | "+itemName+" | "+amount+" | "+price+" | "+timeLeft+" |  -  | "+Role);
						else
							var1.sendChatToPlayer(goodIds[auctionId]+" | "+itemName+" | "+amount+" | "+price+" | "+timeLeft+" | "+buyout+" | "+Role );
			
				auctionsToDisplay--;
				auctionId++;
			
			}
		}
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer("- - - - - - - - - Showing["+auctionsToDisplay+"/1]- - - - - - - - - ");
		
		
	}

}
