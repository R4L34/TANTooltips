package com.r4l.tan_tooltips.main;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

import com.r4l.tan_tooltips.config.ConfigHandler;
import com.r4l.tan_tooltips.proxy.CommonProxy;
import com.r4l.tan_tooltips.reference.Reference;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_MINECRAFT_VERSION)
public class Main {

	public static File config;
	
	@Instance
	public static Main inctance;
	
	@SidedProxy(clientSide = Reference.CLIENT, serverSide = Reference.COMMON)
	public static CommonProxy proxy;

	
	//Event Handlers
	@EventHandler
	public static void preInit (FMLPreInitializationEvent event) {
		proxy.subscribeHandler();
		ConfigHandler.registerConfig(event);
	}
	
	@EventHandler
	public static void init (FMLInitializationEvent event) {}
	
	@EventHandler
	public static void postInit (FMLPostInitializationEvent event) {}
}
