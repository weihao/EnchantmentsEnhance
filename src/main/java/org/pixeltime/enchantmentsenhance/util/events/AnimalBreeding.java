package org.pixeltime.enchantmentsenhance.util.events;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class AnimalBreeding {

    public static ItemStack goldenApple, goldenCarrot, wheat, carrot, seeds,
            dandelions;
    public static EntityType horse, sheep, cow, mooshroomCow, pig, chicken,
            rabbit;
    public static ArrayList<Material> breeadableFood =
            new ArrayList<Material>();
    public static ArrayList<EntityType> breeadableAnimals =
            new ArrayList<EntityType>();


    /**
     * Initialization Constructor.
     */
    public AnimalBreeding() {
        this.addBreeadableAnimals();
        this.addBreeadableFood();
    }


    /**
     * Defines breedable food.
     */
    public void addBreeadableFood() {
        goldenApple = new ItemStack(Material.GOLDEN_APPLE);
        goldenCarrot = new ItemStack(Material.GOLDEN_CARROT);
        wheat = new ItemStack(Material.WHEAT);
        carrot = new ItemStack(Material.CARROT);
        seeds = new ItemStack(Material.SEEDS);
        dandelions = new ItemStack(Material.YELLOW_FLOWER);

        breeadableFood.add(goldenApple.getType());
        breeadableFood.add(goldenCarrot.getType());
        breeadableFood.add(wheat.getType());
        breeadableFood.add(carrot.getType());
        breeadableFood.add(seeds.getType());
        breeadableFood.add(dandelions.getType());
    }


    /**
     * Defines breedable animals.
     */
    public void addBreeadableAnimals() {
        horse = EntityType.HORSE;
        sheep = EntityType.SHEEP;
        cow = EntityType.COW;
        mooshroomCow = EntityType.MUSHROOM_COW;
        pig = EntityType.PIG;
        chicken = EntityType.CHICKEN;
        rabbit = EntityType.RABBIT;

        breeadableAnimals.add(horse);
        breeadableAnimals.add(sheep);
        breeadableAnimals.add(cow);
        breeadableAnimals.add(mooshroomCow);
        breeadableAnimals.add(pig);
        breeadableAnimals.add(chicken);
        breeadableAnimals.add(rabbit);
    }

}
