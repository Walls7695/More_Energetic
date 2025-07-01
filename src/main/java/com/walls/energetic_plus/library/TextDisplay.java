package com.walls.energetic_plus.library;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TextDisplay {
    /**
     * 在世界中显示临时浮动文字
     * @param world 要显示文字的世界
     * @param position 文字显示的位置
     * @param text 要显示的文本内容
     * @param duration 文字显示的持续时间（以游戏刻为单位，20刻=1秒）
     */
    public static void displayTemporaryText(World world, Vec3d position, String text, int duration) {
        // 确保在服务器端执行，避免客户端重复创建
        if (world.isClient()) return;

        ServerWorld serverWorld = (ServerWorld) world;

        // 创建隐形盔甲架
        ArmorStandEntity armorStand = new ArmorStandEntity(EntityType.ARMOR_STAND, serverWorld);

        // 设置盔甲架属性
        armorStand.setCustomName(Text.literal(text));
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);        // 隐形
        armorStand.setInvulnerable(true);     // 无敌
        armorStand.setNoGravity(true);        // 无重力
        armorStand.setSilent(true);           // 无声

        // 设置位置
        armorStand.setPosition(position);

        // 加入世界
        serverWorld.spawnEntity(armorStand);

        // 设置延迟移除任务
        MinecraftServer server = serverWorld.getServer();
        server.submit(() -> {
            if (!armorStand.isRemoved()) {
                armorStand.remove(Entity.RemovalReason.DISCARDED);
            }
        });
    }

    /**
     * 重载方法，使用x,y,z坐标指定位置
     */
    public static void displayTemporaryText(World world, double x, double y, double z, String text, int duration) {
        displayTemporaryText(world, new Vec3d(x, y, z), text, duration);
    }

    /**
     * 在实体上方显示临时文字
     * @param entity 要显示文字的目标实体
     * @param text 要显示的文本内容
     * @param duration 持续时间（游戏刻）
     * @param offsetY 文字相对于实体头顶的垂直偏移量
     */
    public static void displayTextAboveEntity(Entity entity, String text, int duration, float offsetY) {
        Vec3d position = entity.getPos().add(0, entity.getHeight() + offsetY, 0);
        displayTemporaryText(entity.getWorld(), position, text, duration);
    }

    /**
     * 在实体上方显示临时文字（默认偏移量）
     */
    public static void displayTextAboveEntity(Entity entity, String text, int duration) {
        displayTextAboveEntity(entity, text, duration, 0.5f);
    }
}
