package auctionHouse.handlers.command;

import java.util.Iterator;
import java.util.Set;

import auctionHouse.handlers.AuctionHandler;
import net.minecraft.command.ICommandSender;

public class ShowHandler {
	
	public static String cmdDesc = "Use /ah show <item name> to see auctions for items whith that name";

	public static void handleShow(ICommandSender var1, String[] var2) {
						
		if(var2.length < 2)
		{
			var1.sendChatToPlayer("Too few arguments in your command");
			var1.sendChatToPlayer(cmdDesc);
			return;
		}
		
		//array whith ids matching the search
		int[] goodIds = new int[10]; 
		
		int numberOfPages = 0;
		
		Set AuctionIdSet = AuctionHandler.Auctions.keySet();
		Iterator AuctionIt = AuctionIdSet.iterator();
		
		int x = 0; //temp caunter
		int[] Ids = new int[AuctionHandler.Auctions.size()];
		
		if(AuctionIt.hasNext())
		{
			Ids[x] = (Integer) AuctionIt.next();
			x++;
		}
		
		int auctionsToDisplay = 0;
				
		int auctionId = 0;
		
		x = 0; //the counter/id checker

		//padaryt 1 page max
		while(x < Ids.length)
		{
			if(AuctionHandler.getAuction(Ids[x]).itemName.contains(var2[1]) && goodIds.length < 20)
				goodIds[goodIds.length] = x;
			
			x++;
		}
				
		auctionsToDisplay = goodIds.length - (numberOfPages-1)*10;	
		
		//auction info
		String name = "";
		int amount = 0;
		long price = 0;
		long timeLeft = 0;
		long buyout = 0;
		
		var1.sendChatToPlayer("- - - - - - - - - AuctionHouse - - - - - - - - -");
		var1.sendChatToPlayer("ID | Item Name | Amount | Price | Time Left | BuyOut Price");
		var1.sendChatToPlayer(" ");
		
		auctionId = 0;
		
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
				name = AuctionHandler.getAuction(goodIds[auctionId]).itemName;
				amount = AuctionHandler.getAuction(goodIds[auctionId]).itemAmount;
				price = AuctionHandler.getAuction(goodIds[auctionId]).priceNow;
				timeLeft = AuctionHandler.getAuction(goodIds[auctionId]).timeLeft;
				buyout = AuctionHandler.getAuction(goodIds[auctionId]).buyOutPrice;
			
				if(buyout == 0)
					var1.sendChatToPlayer(Ids[auctionId]+" | "+name+" | "+amount+" | "+price+" | "+timeLeft+" |  - ");
						else
							var1.sendChatToPlayer(Ids[auctionId]+" | "+name+" | "+amount+" | "+price+" | "+timeLeft+" | "+buyout);
			
				auctionsToDisplay--;
				auctionId++;

			}
		}
		
		var1.sendChatToPlayer("- - - - - - - - - Page[1/1] - - - - - - - - -");
	}

}
