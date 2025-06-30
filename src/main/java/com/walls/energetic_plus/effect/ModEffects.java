package com.walls.energetic_plus.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import static com.walls.energetic_plus.EnergeticPlus.MOD_ID;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> WET = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MOD_ID, "wet"), new WetEffect());
    public static final RegistryEntry<StatusEffect> ENERGY = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MOD_ID, "energy"), new EnergyEffect());

    public static void registerEffects() {
    }
}
