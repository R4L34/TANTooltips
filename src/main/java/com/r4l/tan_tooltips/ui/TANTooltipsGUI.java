package com.r4l.tan_tooltips.ui;

import org.lwjgl.opengl.GL11;

import com.r4l.tan_tooltips.config.ConfigHandler;
import com.r4l.tan_tooltips.reference.RegexHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TANTooltipsGUI {
	@SubscribeEvent
	public void ShowTooltip(RenderTooltipEvent.PostText event) {

		if (ConfigHandler.tan_tooltips == false) { return; }
		
		
		//Get Item
		ItemStack stack = event.getStack(); if (stack == null) { return; }
		//Texture Handler
		Minecraft mc = Minecraft.getMinecraft();
		mc.getTextureManager().bindTexture(new ResourceLocation("tan_tooltips", "textures/gui/overlay/thirst_overlay.png"));// Change later
		GuiScreen gui = mc.currentScreen;
		
		//Get Item Names
		String unlocalisedName = stack.getItem().getRegistryName().toString(); 
		int metadata = stack.getMetadata();
		String[] unlocalisedName_meta = {unlocalisedName, unlocalisedName + ":0", unlocalisedName + ":" + Integer.toString(metadata)}; //0,1 compare to if metadata == 0, 2 compare to if metadata != 0
		//Thirst & Hydration
		int calculatedThirst = 0;
		int calculatedHydration = 0;
		//Matching Item
		boolean loop = true;
		for (int i = 0; i < ConfigHandler.items_tan_tooltips_registry_name.length && loop == true; i++) {
			String configItem = ConfigHandler.items_tan_tooltips_registry_name[i];
			for (int t = 0; t <= 20; t++) {
				if(RegexHandler.preg_match(configItem, "\\|T" + t + "\\b")) {configItem = RegexHandler.preg_replace(configItem, "\\|T" + t + "\\b", ""); calculatedThirst = t;}
			}
			for (int h = 0; h <= 20; h++) {
				if(RegexHandler.preg_match(configItem, "\\|H" + h + "\\b")) {configItem = RegexHandler.preg_replace(configItem, "\\|H" + h + "\\b", ""); calculatedHydration = h;}
			}
			
			
			if((configItem.equals(unlocalisedName_meta[0]) || configItem.equals(unlocalisedName_meta[1])) && metadata == 0) {
				loop = false;
			} else if (configItem.equals(unlocalisedName_meta[2]) && metadata != 0) {
				loop = false;
			} else if (RegexHandler.preg_match(configItem, ":\\*") && unlocalisedName.equals(RegexHandler.preg_replace(configItem, ":\\*", ""))) {
				loop = false;
			}else {
				calculatedThirst = 0;
				calculatedHydration = 0;
				loop = true;
			}
		}
		
		
		if (calculatedThirst == 0 && calculatedHydration == 0) { return; }	
		
		//mc.player.sendChatMessage(Integer.toString(calculatedThirst));
		//mc.player.sendChatMessage(Integer.toString(calculatedHydration));
		
		//calculating
				ScaledResolution res = new ScaledResolution(mc);
				float hydrationRemaining = 2f * ((int) calculatedHydration % 2);
				int lengthThirst = ((calculatedThirst + 1) >> 1) << 3;
				int lengthHydration = 2 + ((int) ((calculatedHydration + 1.99) / 2.0)) * 6;
				int length = Math.max(lengthThirst, lengthHydration);
				GL11.glColor4f(1, 1, 1, 1);
				int baseX = event.getX();
				int baseY = event.getY() + event.getHeight();
				if ((baseY + 29) >= res.getScaledHeight())
					baseY = event.getY() - 33;
				
				//drawing
				gui.drawTexturedModalRect(baseX - 2, baseY + 3, 0, 42, length + 4, 26);

				gui.drawTexturedModalRect(baseX + length + 2, baseY + 3, 88, 42, 3, 26);
				
				for (int i = 0; i * 2 < calculatedThirst; i++) {
					if (calculatedThirst - i * 2 == 1) {
						gui.drawTexturedModalRect(baseX + 2 + i * 8, baseY + 7, 46, 0, 7, 10);
						break;
					}
					gui.drawTexturedModalRect(baseX + 2 + i * 8, baseY + 7, 37, 0, 7, 10);

				}
				
				for (int i = 0; i * 2 < calculatedHydration; i++) {
					if (calculatedHydration - i * 2 < 2) {
						gui.drawTexturedModalRect(baseX + 2 + i * 6, baseY + 17, ((int) (hydrationRemaining - 1)) * 7, 27, 6, 7);
						break;
					}
					gui.drawTexturedModalRect(baseX + 2 + i * 6, baseY + 17, 21, 27, 6, 7);

				}
		
		
	}
	
}
