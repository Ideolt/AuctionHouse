package auctionHouse.handlers.command;

import auctionHouse.basic.AuctionWorker;
import auctionHouse.handlers.IdHandler;
import auctionHouse.handlers.NoteHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class NotesHandler
{

	public static String cmdDesc = "Use /ah notes if you have any pending notes ";
	public static String cmdDesc2 =	"(auctions that are finished) to make them finish";
	
	public static void handlerNotes(ICommandSender var1, String[] var2) 
	{
		
		String name = var1.getCommandSenderName();
		
		if(!(var1 instanceof EntityPlayer))
		{
			var1.sendChatToPlayer("You can't use this command from the console");
			return;
		}
		
		if(NoteHandler.getNotes(IdHandler.returnId(name)) == null)
		{
			var1.sendChatToPlayer("You don't have any finished auctions");
			return;
		}
		else
		{
			int[] auctions = NoteHandler.getNotes(IdHandler.returnId(name));
			
			int x = 0;
			while(x < auctions.length)
			{
				AuctionWorker.runWorker(auctions[x]);
				x++;
			}
			NoteHandler.removeNotes(IdHandler.returnId(name));
		}
		
		var1.sendChatToPlayer("Auction execution done. Notes removed");
		return;
	}

}
