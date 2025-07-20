package com.walls.energetic_plus.globalVariable;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;

public class StateSaverAndLoader extends PersistentState {
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putFloat("energeticPlusCoolDownTime", GlobalVariable.energeticPlusCoolDownTime);
        return nbt;
    }
}
