package auctionHouse.handlers.command;

import auctionHouse.handlers.command.bank.BalanceHandler;
import auctionHouse.handlers.command.bank.DepositHandler;
import auctionHouse.handlers.command.bank.SendHandler;
import auctionHouse.handlers.command.bank.WithdrawHandler;
import net.minecraft.command.ICommandSender;

public class HelpHandler 
{

	public static void handleHelp(ICommandSender var1) 
	{
		var1.sendChatToPlayer("- - - - - - - - - AuctionHouse - - - - - - - - -");
		var1.sendChatToPlayer("Full list of commands:");
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer(SellHandler.cmdDesc);
		var1.sendChatToPlayer(SellHandler.cmdDesc2);
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer(BidHandler.cmdDesc);
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer(BuyoutHandler.cmdDesc);
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer(CancelHandler.cmdDesc);
		var1.sendChatToPlayer(CancelHandler.cmdDesc2);
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer(MeHandler.cmdDesc);
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer(ListHandler.cmdDesc);
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer(ShowHandler.cmdDesc);
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer(NotesHandler.cmdDesc);
		var1.sendChatToPlayer(NotesHandler.cmdDesc2);
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer("- - - - - - - - - AHBank - - - - - - - - -");
		var1.sendChatToPlayer("AH Economy commands: ");
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer(BalanceHandler.cmdDesc);
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer(SendHandler.cmdDesc);
		var1.sendChatToPlayer(SendHandler.cmdDesc2);
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer(DepositHandler.cmdDesc);
		var1.sendChatToPlayer(DepositHandler.cmdDesc2);
		var1.sendChatToPlayer(DepositHandler.cmdDesc3);
		var1.sendChatToPlayer(DepositHandler.cmdDesc4);
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer(WithdrawHandler.cmdDesc);
		var1.sendChatToPlayer(WithdrawHandler.cmdDesc2);
		var1.sendChatToPlayer(WithdrawHandler.cmdDesc3);		
		var1.sendChatToPlayer(" ");
		var1.sendChatToPlayer("[AuctionHouse] Have Fun !");
		
		return;
	}
	
	

}
