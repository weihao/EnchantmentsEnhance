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

package org.pixeltime.enchantmentsenhance.util.events

import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.inventory.ItemStack
import org.pixeltime.enchantmentsenhance.util.XMaterial
import java.util.*

object AnimalBreeding {

    lateinit var goldenApple : ItemStack
    lateinit var goldenCarrot : ItemStack
    lateinit var wheat : ItemStack
    lateinit var carrot : ItemStack
    lateinit var seeds : ItemStack
    lateinit var dandelions : ItemStack
    lateinit var horse : EntityType
    lateinit var sheep : EntityType
    lateinit var cow : EntityType
    lateinit var mooshroomCow : EntityType
    lateinit var pig : EntityType
    lateinit var chicken : EntityType
    lateinit var rabbit : EntityType
    var breeadableFood = ArrayList<Material>()
    var breeadableAnimals = ArrayList<EntityType>()

    /**
     * Initialization Constructor.
     */
    fun setUp() {
        addBreeadableAnimals()
        addBreeadableFood()
    }

    /**
     * Defines breedable food.
     */
    fun addBreeadableFood() {
        goldenApple = ItemStack(Material.GOLDEN_APPLE)
        goldenCarrot = ItemStack(Material.GOLDEN_CARROT)
        wheat = ItemStack(Material.WHEAT)
        carrot = ItemStack(Material.CARROT)
        seeds = ItemStack(XMaterial.WHEAT_SEEDS.parseMaterial())
        dandelions = ItemStack(XMaterial.DANDELION.parseMaterial())

        breeadableFood.add(goldenApple.type)
        breeadableFood.add(goldenCarrot.type)
        breeadableFood.add(wheat.type)
        breeadableFood.add(carrot.type)
        breeadableFood.add(seeds.type)
        breeadableFood.add(dandelions.type)
    }

    /**
     * Defines breedable animals.
     */
    fun addBreeadableAnimals() {
        horse = EntityType.HORSE
        sheep = EntityType.SHEEP
        cow = EntityType.COW
        mooshroomCow = EntityType.MUSHROOM_COW
        pig = EntityType.PIG
        chicken = EntityType.CHICKEN
        rabbit = EntityType.RABBIT

        breeadableAnimals.add(horse)
        breeadableAnimals.add(sheep)
        breeadableAnimals.add(cow)
        breeadableAnimals.add(mooshroomCow)
        breeadableAnimals.add(pig)
        breeadableAnimals.add(chicken)
        breeadableAnimals.add(rabbit)
    }
}
