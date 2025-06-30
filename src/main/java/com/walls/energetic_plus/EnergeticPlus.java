package com.walls.energetic_plus;

import com.walls.energetic_plus.effect.ModEffects;
import com.walls.energetic_plus.item.ModItemGroup;
import com.walls.energetic_plus.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnergeticPlus implements ModInitializer {
	public static final String MOD_ID = "energetic-plus";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("Loading");

		ModItems.registerModItems();
		ModItemGroup.registerModItemsGroup();
		ModEffects.registerEffects();

		LOGGER.info("All Done");
	}
}