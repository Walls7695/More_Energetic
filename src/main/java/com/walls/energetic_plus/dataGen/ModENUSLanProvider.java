package com.walls.energetic_plus.dataGen;

import com.walls.energetic_plus.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static com.walls.energetic_plus.EnergeticPlus.MOD_ID;

public class ModENUSLanProvider extends FabricLanguageProvider {
    public ModENUSLanProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput,"en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup wrapperLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.ENERGETIC_PLUS,"Energetic+");
        translationBuilder.add(ModItems.ENERGY_CONVERTER_BLOOD_POWER_REPAYMENT_TYPE,"Energy converter(blood power repayment type)");

        translationBuilder.add("effect."+MOD_ID+".wet","Wet");
        translationBuilder.add("effect."+MOD_ID+".energy","Energy");

        translationBuilder.add("energyItem.tips.level_up", "Level Up！");
        translationBuilder.add("energyItem.tips.have_the_maximum", "Have The Maximum！");
        translationBuilder.add("energyItem.tips.already_added", "Already Added!");

        translationBuilder.add("itemGroup.energetic_plus_group", "Energetic+");
    }
}
