package com.walls.energetic_plus;

import com.walls.energetic_plus.dataGen.ModModelsProvider;
import com.walls.energetic_plus.dataGen.ModZHCNLanProvider;
import com.walls.energetic_plus.dataGen.ModENUSLanProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EnergeticPlusDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelsProvider::new);
		pack.addProvider(ModZHCNLanProvider::new);
		pack.addProvider(ModENUSLanProvider::new);

	}
}
