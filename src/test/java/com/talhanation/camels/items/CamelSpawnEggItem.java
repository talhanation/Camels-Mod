package com.talhanation.camels.items;


import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;

import java.util.function.Supplier;

public class CamelSpawnEggItem  extends SpawnEggItem {
    private Supplier<EntityType<?>> entityType;

    public CamelSpawnEggItem(Supplier<EntityType<?>> entityType, int primaryColor, int secondaryColor, Properties properties){
        super(null, primaryColor, secondaryColor, properties);
        this.entityType = entityType;
    }

    @Override
    public EntityType<?> getType(CompoundNBT compound){
        if(compound != null && compound.contains("EntityTag", 10)) {
            CompoundNBT entityTag = compound.getCompound("EntityTag");

            if(entityTag.contains("id", 8)) {
                return EntityType.byKey(entityTag.getString("id")).orElse(this.entityType.get());
            }
        }
        return this.entityType.get();
    }
}