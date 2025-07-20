package com.walls.energetic_plus.event;

import com.walls.energetic_plus.globalVariable.GlobalVariable;
import com.walls.energetic_plus.item.ModItems;
import com.walls.energetic_plus.library.InventoryChecker;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.walls.energetic_plus.EnergeticPlus.MOD_ID;

public class PlayerLeftTheGame {

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void register(){
        // 注册一个事件，当玩家离开游戏时触发
        ServerPlayerEvents.LEAVE.register((playerEntity) -> {
            if(InventoryChecker.hasItem(playerEntity, ModItems.ENERGETIC_PLUS)){
                ItemStack targetItemStackResult = InventoryChecker.targetItemStackResult;
                GlobalVariable.energeticPlusCoolDownTime = playerEntity.getItemCooldownManager().getCooldownProgress(targetItemStackResult, 0);
                LOGGER.info("玩家离开游戏，能量充能时间：{}", GlobalVariable.energeticPlusCoolDownTime);

            }
        });
    }
}
