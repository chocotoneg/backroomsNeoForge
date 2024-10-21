package net.mc3699.backrooms.items;

import net.mc3699.backrooms.BackroomsMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.EventBus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BackroomsMod.MODID);



    public static void register(IEventBus modEventBus)
    {
        ITEMS.register(modEventBus);
    }
}
