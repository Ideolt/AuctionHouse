package auctionHouse.handlers.command;

import java.util.Iterator;
import java.util.Set;

import auctionHouse.handlers.AuctionHandler;
import net.minecraft.command.ICommandSender;

public class ListHandler {

	public static String cmdDesc = "Use /ah list [page number] to see all the auctions currently running";
	
	public static void handleList(ICommandSender var1, String[] var2) {
		

		int pageToDisplay = 0;
		
		if(var2.length >= 2 && AuctionHandler.isNumber(var2[1]))
			pageToDisplay = Integer.parseInt(var2[1]);
		else
			pageToDisplay = 1;
		
		int numberOfPages = 1;

		
		Set AuctionIdSet = AuctionHandler.Auctions.keySet();
		Iterator AuctionIt = AuctionIdSet.iterator();
		
		int x = 0; 
		int[] Ids = new int[AuctionHandler.Auctions.size()];
				
		while(AuctionIt.hasNext())
		{
			Ids[x] = (Integer) AuctionIt.next();
			x++;
		}
	
		int auctionsToDisplay = 0;
				
		int auctionId = 0;
		
		
		while (AuctionHandler.Auctions.size() - numberOfPages*10 > 10)
			numberOfPages++;

		if(pageToDisplay > numberOfPages || pageToDisplay < 1)
			pageToDisplay = 1;
		
		//Decides how many auctions there will be displayed
		
		if(pageToDisplay < numberOfPages)
			auctionsToDisplay = 10;
			else
			auctionsToDisplay = AuctionHandler.Auctions.size() - (numberOfPages-1)*10;	
		
		//auction info
		String name = "";
		int amount = 0;
		long price = 0;
		long timeLeft = 0;
		long buyout = 0;
		
		
		//preparing the info to display
		
		var1.sendChatToPlayer("- - - - - - - - - AuctionHouse - - - - - - - - -");
		var1.sendChatToPlayer("ID | Item Name | Amount | Price | Time Left | BuyOut Price");
		var1.sendChatToPlayer(" ");
		
		auctionId = (pageToDisplay-1)*10;
		
		if(auctionsToDisplay < 1)
			var1.sendChatToPlayer("No Auctions for now");
		else
		{
			while(auctionsToDisplay >= 1)
			{
				name = AuctionHandler.getAuction(Ids[auctionId]).itemName;
				amount = AuctionHandler.getAuction(Ids[auctionId]).itemAmount;
				price = AuctionHandler.getAuction(Ids[auctionId]).priceNow;
				timeLeft = AuctionHandler.getAuction(Ids[auctionId]).timeLeft;
				buyout = AuctionHandler.getAuction(Ids[auctionId]).buyOutPrice;
			
				if(buyout == 0 && timeLeft == 0)
				{
					var1.sendChatToPlayer(Ids[auctionId]+" | "+name+" | "+amount+" | "+price+" | Finished |  - ");
					var1.sendChatToPlayer(" ");
				}
				else if(timeLeft == 0)
				{
					var1.sendChatToPlayer(Ids[auctionId]+" | "+name+" | "+amount+" | "+price+" | Finished | "+buyout);
					var1.sendChatToPlayer(" ");
				}
				else if(buyout == 0)
				{
					var1.sendChatToPlayer(Ids[auctionId]+" | "+name+" | "+amount+" | "+price+" | "+timeLeft+" | - ");
					var1.sendChatToPlayer(" ");
				}
				else
				{
					var1.sendChatToPlayer(Ids[auctionId]+" | "+name+" | "+amount+" | "+price+" | "+timeLeft+" | "+buyout);
					var1.sendChatToPlayer(" ");
				}
				
			
				auctionsToDisplay--;
				auctionId++;

			}
		}
		
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer("- - - - - - - - - Page["+pageToDisplay+"/"+numberOfPages+"] - - - - - - - - -");
	}

}
