/*
 *     Copyright (C) 2017-Present 25 (https://github.com/25)
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

package org.pixeltime.enchantmentsenhance.compatibility

import com.lgou2w.ldk.asm.ASMClassGenerator
import com.lgou2w.ldk.bukkit.version.MinecraftBukkitVersion
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.Label
import org.objectweb.asm.Opcodes

@Deprecated("INTERNAL ONLY")
object EnchantmentGlowImplGenerator : ASMClassGenerator {

    private fun compatibilityName(className: String) = "org/pixeltime/enchantmentsenhance/compatibility/$className"
    private fun compatibilityDescriptor(className: String) = "L${compatibilityName(className)};"

    private const val E_ID = 255 // Before version 1.13
    private const val E_NAME = "eeglow"

    override fun generate(): Map<String, ByteArray> {
        val classes = LinkedHashMap<String, ByteArray>(1)
        val enchantmentGlowImpl = ClassWriter(0x00).apply {
            visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, compatibilityName("EnchantmentGlowImpl"), null, "org/bukkit/enchantments/EnchantmentWrapper", null)
            visitSource("EnchantmentGlowImpl.java", null)
            visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null).apply {
                visitCode()
                val l0 = Label()
                visitLabel(l0)
                visitLineNumber(11, l0)
                visitVarInsn(Opcodes.ALOAD, 0)
                if (MinecraftBukkitVersion.isV113OrLater) {
                    // Since Minecraft 1.13
                    visitLdcInsn(E_NAME)
                    visitMethodInsn(Opcodes.INVOKESPECIAL, "org/bukkit/enchantments/EnchantmentWrapper", "<init>", "(Ljava/lang/String;)V", false)
                } else {
                    // Before version 1.13
                    visitIntInsn(Opcodes.SIPUSH, E_ID)
                    visitMethodInsn(Opcodes.INVOKESPECIAL, "org/bukkit/enchantments/EnchantmentWrapper", "<init>", "(I)V", false)
                }
                val l1 = Label()
                visitLabel(l1)
                visitLineNumber(12, l1)
                visitInsn(Opcodes.RETURN)
                val l2 = Label()
                visitLabel(l2)
                visitLocalVariable("this", compatibilityDescriptor("EnchantmentGlowImpl"), null, l0, l2, 0)
                visitMaxs(2, 1)
                visitEnd()
            }
            visitMethod(Opcodes.ACC_PUBLIC, "canEnchantItem", "(Lorg/bukkit/inventory/ItemStack;)Z", null, null).apply {
                visitCode()
                val l0 = Label()
                visitLabel(l0)
                visitLineNumber(16, l0)
                visitInsn(Opcodes.ICONST_1)
                visitInsn(Opcodes.IRETURN)
                val l1 = Label()
                visitLabel(l1)
                visitLocalVariable("this", compatibilityDescriptor("EnchantmentGlowImpl"), null, l0, l1, 0)
                visitLocalVariable("item", "Lorg/bukkit/inventory/ItemStack;", null, l0, l1, 1)
                visitMaxs(1, 2)
                visitEnd()
            }
            visitMethod(Opcodes.ACC_PUBLIC, "getItemTarget", "()Lorg/bukkit/enchantments/EnchantmentTarget;", null, null).apply {
                visitCode()
                val l0 = Label()
                visitLabel(l0)
                visitLineNumber(21, l0)
                visitFieldInsn(Opcodes.GETSTATIC, "org/bukkit/enchantments/EnchantmentTarget", "ALL", "Lorg/bukkit/enchantments/EnchantmentTarget;")
                visitInsn(Opcodes.ARETURN)
                val l1 = Label()
                visitLabel(l1)
                visitLocalVariable("this", compatibilityDescriptor("EnchantmentGlowImpl"), null, l0, l1, 0)
                visitMaxs(1, 1)
                visitEnd()
            }
            visitMethod(Opcodes.ACC_PUBLIC, "getMaxLevel", "()I", null, null).apply {
                visitCode()
                val l0 = Label()
                visitLabel(l0)
                visitLineNumber(26, l0)
                visitInsn(Opcodes.ICONST_1)
                visitInsn(Opcodes.IRETURN)
                val l1 = Label()
                visitLabel(l1)
                visitLocalVariable("this", compatibilityDescriptor("EnchantmentGlowImpl"), null, l0, l1, 0)
                visitMaxs(1, 1)
                visitEnd()
            }
            visitMethod(Opcodes.ACC_PUBLIC, "getStartLevel", "()I", null, null).apply {
                visitCode()
                val l0 = Label()
                visitLabel(l0)
                visitLineNumber(31, l0)
                visitInsn(Opcodes.ICONST_1)
                visitInsn(Opcodes.IRETURN)
                val l1 = Label()
                visitLabel(l1)
                visitLocalVariable("this", compatibilityDescriptor("EnchantmentGlowImpl"), null, l0, l1, 0)
                visitMaxs(1, 1)
                visitEnd()
            }
            visitMethod(Opcodes.ACC_PUBLIC, "conflictsWith", "(Lorg/bukkit/enchantments/Enchantment;)Z", null, null).apply {
                visitCode()
                val l0 = Label()
                visitLabel(l0)
                visitLineNumber(36, l0)
                visitInsn(Opcodes.ICONST_0)
                visitInsn(Opcodes.IRETURN)
                val l1 = Label()
                visitLabel(l1)
                visitLocalVariable("this", compatibilityDescriptor("EnchantmentGlowImpl"), null, l0, l1, 0)
                visitLocalVariable("other", "Lorg/bukkit/enchantments/Enchantment;", null, l0, l1, 1)
                visitMaxs(1, 2)
                visitEnd()
            }
            visitMethod(Opcodes.ACC_PUBLIC, "getName", "()Ljava/lang/String;", null, null).apply {
                visitCode()
                val l0 = Label()
                visitLabel(l0)
                visitLineNumber(41, l0)
                visitLdcInsn(E_NAME)
                visitInsn(Opcodes.ARETURN)
                val l1 = Label()
                visitLabel(l1)
                visitLocalVariable("this", compatibilityDescriptor("EnchantmentGlowImpl"), null, l0, l1, 0)
                visitMaxs(1, 1)
                visitEnd()
            }
        }.toByteArray()
        classes["org.pixeltime.enchantmentsenhance.compatibility.EnchantmentGlowImpl"] = enchantmentGlowImpl
        return classes
    }
}
