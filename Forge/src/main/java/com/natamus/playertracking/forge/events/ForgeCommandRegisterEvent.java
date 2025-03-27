package com.natamus.playertracking.forge.events;

import com.natamus.playertracking.cmds.CommandTrack;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeCommandRegisterEvent {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent e) {
    	CommandTrack.register(e.getDispatcher());
    }
}
