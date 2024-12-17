package com.r4l.tan_tooltips.proxy;

import com.r4l.tan_tooltips.ui.TANTooltipsGUI;

import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
	public void subscribeHandler() {
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new TANTooltipsGUI());
	}
}
