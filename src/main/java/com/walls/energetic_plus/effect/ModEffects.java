package com.walls.energetic_plus.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import static com.walls.energetic_plus.EnergeticPlus.MOD_ID;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> WET = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MOD_ID, "wet"), new WetEffect());
    public static final RegistryEntry<StatusEffect> ENERGY = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MOD_ID, "energy"), new EnergyEffect());
    public static final RegistryEntry<StatusEffect> BLOOD_POWER_REPAYMENT = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MOD_ID, "blood_power_repayment"), new BloodPowerRepayment());
    public static final RegistryEntry<StatusEffect> BLOOD_POWER_ATTACKER = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(MOD_ID, "blood_power_attacker"), new BloodPowerAttacker());

    public static void registerEffects() {
    }
}
