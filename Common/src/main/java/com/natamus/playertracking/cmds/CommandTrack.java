package com.natamus.playertracking.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.natamus.collective.functions.PlayerFunctions;
import com.natamus.collective.functions.MessageFunctions;
import com.natamus.playertracking.util.Tracking;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class CommandTrack {
	   public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
			dispatcher.register(Commands.literal("track")
					.requires((iCommandSender) -> iCommandSender.getEntity() instanceof Player)
					.then(Commands.literal("help")
					.executes((command) -> {
						Player player = command.getSource().getPlayerOrException();
						
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.trackinghelppage1", true, ChatFormatting.DARK_GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.introductiontrackingread", ChatFormatting.GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.informationbuildingtracker", ChatFormatting.GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.explanationtrackread", ChatFormatting.GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.explanationtrackplayername", ChatFormatting.GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.selectpagetrack", ChatFormatting.GRAY);
						return 1;
					}))
					.then(Commands.literal("help")
					.then(Commands.argument("page", IntegerArgumentType.integer(1, 5))
					.executes((command) -> {
						Player player = command.getSource().getPlayerOrException();
						
						int page = IntegerArgumentType.getInteger(command, "page");
						
						if (page == 2) {
							MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.trackinghelppage2", true, ChatFormatting.DARK_GRAY);
							MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.trackingfeatureencourage", ChatFormatting.GRAY);
							return 1;
						}
						else if (page == 3) {
							MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.trackinghelppage3", true, ChatFormatting.DARK_GRAY);
							MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.startbuildingsolid", ChatFormatting.GRAY);
							MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.seelayouttracker", ChatFormatting.DARK_GRAY);
							return 1;
						}
						else if (page == 4) {
							MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.trackinghelppage4", true, ChatFormatting.DARK_GRAY);
							MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.oncemadeown", ChatFormatting.GRAY);
							MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.proceedpage5", ChatFormatting.DARK_GRAY);
							return 1;
						}
						else if (page == 5) {
							MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.trackinghelppage5", true, ChatFormatting.DARK_GRAY);
							MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.ifluckyenough", ChatFormatting.GRAY);
							return 1;
						}
						
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.trackinghelppage1", true, ChatFormatting.DARK_GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.introductiontrackingread", ChatFormatting.GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.informationbuildingtracker", ChatFormatting.GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.explanationtrackread", ChatFormatting.GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.explanationtrackplayername", ChatFormatting.GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.selectpagetrack", ChatFormatting.GRAY);
						return 1;
					})))
					.then(Commands.literal("help")
					.then(Commands.argument("page", IntegerArgumentType.integer(3, 3))
					.then(Commands.literal("layout")
					.executes((command) -> {
						Player player = command.getSource().getPlayerOrException();
						
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.trackinghelppage35", true, ChatFormatting.DARK_GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.picturebelowsee", ChatFormatting.GRAY);
						MessageFunctions.sendMessage(player, "         G", ChatFormatting.GRAY);
						MessageFunctions.sendMessage(player, "         |", ChatFormatting.GRAY);
						MessageFunctions.sendMessage(player, "    G--D--G ", ChatFormatting.GRAY);
						MessageFunctions.sendMessage(player, "         |", ChatFormatting.GRAY);
						MessageFunctions.sendMessage(player, "         G", ChatFormatting.GRAY);
						MessageFunctions.sendMessage(player, "", ChatFormatting.GRAY);   				
						return 1;
					}))))
					.then(Commands.literal("all")
					.executes((command) -> {
						Player player = command.getSource().getPlayerOrException();
						
						Level world = player.level();
						BlockPos bpos = player.blockPosition().below().immutable();
						Block block = world.getBlockState(bpos).getBlock();
						if(block.equals(Blocks.DIAMOND_BLOCK)) {
							Tracking tracker = new Tracking();
							tracker.setLoc(bpos.getX(), bpos.getY(), bpos.getZ());
							tracker.Track(player, null);
							return 1;
						}
						if(block.equals(Blocks.OBSIDIAN)) {
							MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.cannottracktype", ChatFormatting.GRAY);
							return 1;
						}

						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.needsolidtracker", ChatFormatting.GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.trackhelpmore", ChatFormatting.GRAY);
						return 1;
					}))
					.then(Commands.argument("playerName", StringArgumentType.string())
					.executes((command) -> {
						Player player = command.getSource().getPlayerOrException();
						String playername = StringArgumentType.getString(command, "playerName");
						
						Player other = PlayerFunctions.matchPlayer(player, playername);
						if(other != null) {
							Tracking tracker = new Tracking();
							BlockPos bpos = player.blockPosition().below().immutable();
							tracker.setLoc(bpos.getX(), bpos.getY(), bpos.getZ());
							tracker.Track(player, other);
							return 1;
						}
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.findplayer", ChatFormatting.GRAY, playername);
						return 1;
					}))
					.executes((command) -> {
						Player player = command.getSource().getPlayerOrException();
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.usetrackplayername", ChatFormatting.GRAY);
						MessageFunctions.sendTranslatableMessage(player, "collective.playertracking.message.trackhelpinformation", ChatFormatting.GRAY);
						return 1;
					})
				);
	   }
}