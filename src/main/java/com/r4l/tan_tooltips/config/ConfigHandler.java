package com.r4l.tan_tooltips.config;

import java.io.File;

import com.r4l.tan_tooltips.main.Main;
import com.r4l.tan_tooltips.reference.DrinkData;
import com.r4l.tan_tooltips.reference.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler {
	public static Configuration config;
	
	//TAN Tooltips
	public static boolean tan_tooltips = true;
	public static String[] items_tan_tooltips_registry_name;
	
	public static void init(File file) {
		config = new Configuration(file);
		
		String category = "TAN Tooltips";
		config.addCustomCategoryComment(category, "TAN Item Tooltips");
		
		items_tan_tooltips_registry_name = config.getStringList("TAN Tooltips Items", category, DrinkData.tan_names, "Which items will get a TAN Tooltip.");
		tan_tooltips = config.getBoolean("TAN Tooltips", category, true, "Show TAN tooltips on TAN drinkables");
		
		config.save();
	}
	
	public static void registerConfig(FMLPreInitializationEvent event) {
		Main.config = new File(event.getModConfigurationDirectory() + "/" + Reference.MODID);
		Main.config.mkdirs();
		init(new File(Main.config.getPath(), Reference.MODID + ".cfg"));
	}
}
