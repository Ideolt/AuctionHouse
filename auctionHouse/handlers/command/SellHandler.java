package auctionHouse.handlers.command;

import auctionHouse.handlers.AuctionHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class SellHandler 
{
	public static String cmdDesc = "Use /ah sell <price> <time> [buyout Price] <-[optional] while holding ";
	public static String cmdDesc2 = "the item you want to auction in your hand to register a new auction";
	
	public static void handleSell(ICommandSender var1, String[] var2)
	{
		String name = var1.getCommandSenderName();
		
		if(!(var1 instanceof EntityPlayer))
			{
				var1.sendChatToPlayer("You can't use this command from the console!");
				return;
			}
					
		if(var2.length < 3)
			{
				var1.sendChatToPlayer("Too few arguments in your command");
				var1.sendChatToPlayer(cmdDesc);
				var1.sendChatToPlayer(cmdDesc2);

				return;
			}
		
		
		
		EntityPlayer pl = (EntityPlayer)var1;
		
		long price = 0;
		long time = 0;
		long buyoutPrice = 0;
		
		if(AuctionHandler.isNumber(var2[1]))//Checking for bad input [or dumbasses...]
			price = Long.parseLong(var2[1]);
		else
		{
			var1.sendChatToPlayer("bad command imput");
			var1.sendChatToPlayer(cmdDesc);
			var1.sendChatToPlayer(cmdDesc2);
			return;
		}
		
		if(AuctionHandler.isNumber(var2[2]))
			time = Long.parseLong(var2[2]);
		else
		{
			var1.sendChatToPlayer("bad command imput");
			var1.sendChatToPlayer(cmdDesc);
			var1.sendChatToPlayer(cmdDesc2);
			return;
		}
		
		
		
		if(var2.length >= 4 && AuctionHandler.isNumber(var2[3]))
			buyoutPrice = Long.parseLong(var2[3]);
			
		AuctionHandler.registerAuction(pl.inventory.getCurrentItem(), name, time, price, buyoutPrice);
		
		int stackSize = pl.inventory.getCurrentItem().stackSize;
		int itemId = pl.inventory.getCurrentItem().itemID;
		
		while(stackSize > 0)
		{
			pl.inventory.consumeInventoryItem(itemId);
			stackSize--;
		}
			
		var1.sendChatToPlayer("[AH] Auction registered successfully");
	}

}
