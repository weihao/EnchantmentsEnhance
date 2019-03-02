/*
 *     Copyright (C) 2017-Present HealPot
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.util.events;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import com.lgou2w.ldk.bukkit.compatibility.XMaterial;

import java.util.ArrayList;

public class AnimalBreeding {

    public static ItemStack goldenApple, goldenCarrot, wheat, carrot, seeds,
            dandelions, seagrass, haybale, bamboo, sweetberries;
    public static EntityType horse, sheep, cow, mooshroomCow, pig, chicken,
            rabbit, llama, turtle, panda, fox;
    public static ArrayList<Material> breeadableFood =
            new ArrayList<Material>();
    public static ArrayList<EntityType> breeadableAnimals =
            new ArrayList<EntityType>();


    /**
     * Initialization Constructor.
     */
    public static void setUp() {
        addBreeadableAnimals();
        addBreeadableFood();
    }


    /**
     * Defines breedable food.
     */
    public static void addBreeadableFood() {
        goldenApple = new ItemStack(Material.GOLDEN_APPLE);
        goldenCarrot = new ItemStack(Material.GOLDEN_CARROT);
        wheat = new ItemStack(Material.WHEAT);
        carrot = new ItemStack(Material.CARROT);
        seeds = new ItemStack(XMaterial.WHEAT_SEEDS.toBukkit());
        dandelions = new ItemStack(XMaterial.DANDELION.toBukkit());

        breeadableFood.add(goldenApple.getType());
        breeadableFood.add(goldenCarrot.getType());
        breeadableFood.add(wheat.getType());
        breeadableFood.add(carrot.getType());
        breeadableFood.add(seeds.getType());
        breeadableFood.add(dandelions.getType());

        // 1.13
        try {
            haybale = new ItemStack(Material.HAY_BLOCK);
            seagrass = new ItemStack(Material.SEAGRASS);
            breeadableFood.add(haybale.getType());
            breeadableFood.add(seagrass.getType());
        } catch (NoSuchFieldError ex) {
            //
        }
    }


    /**
     * Defines breedable animals.
     */
    public static void addBreeadableAnimals() {
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

        // 1.13
        try {
            llama = EntityType.LLAMA;
            turtle = EntityType.TURTLE;

            breeadableAnimals.add(llama);
            breeadableAnimals.add(turtle);
        } catch (NoSuchFieldError ex) {
            //
        }
    }

}
