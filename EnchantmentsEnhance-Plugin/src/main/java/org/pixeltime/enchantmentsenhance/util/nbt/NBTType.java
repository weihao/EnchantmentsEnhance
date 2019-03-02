/*
 *     Copyright (C) 2017-Present 25
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

package org.pixeltime.enchantmentsenhance.util.nbt;

public enum NBTType {
    NBTTagEnd(0),
    NBTTagByte(1),
    NBTTagShort(2),
    NBTTagInt(3),
    NBTTagLong(4),
    NBTTagFloat(5),
    NBTTagDouble(6),
    NBTTagByteArray(7),
    NBTTagIntArray(11),
    NBTTagString(8),
    NBTTagList(9),
    NBTTagCompound(10);

    private final int id;

    NBTType(int i) {
        id = i;
    }

    public static NBTType valueOf(int id) {
        for (NBTType t : values())
            if (t.getId() == id)
                return t;
        return NBTType.NBTTagEnd;
    }

    public int getId() {
        return id;
    }

}
