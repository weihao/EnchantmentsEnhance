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

package org.pixeltime.enchantmentsenhance.util.nbt;

public class NBTContainer extends NBTCompound {

    private Object nbt;

    public NBTContainer() {
        super(null, null);
        nbt = NBTReflectionUtil.getNewNBTTag();
    }

    protected NBTContainer(Object nbt) {
        super(null, null);
        this.nbt = nbt;
    }

    public NBTContainer(String nbtString) throws IllegalArgumentException {
        super(null, null);
        try {
            nbt = NBTReflectionUtil.parseNBT(nbtString);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Malformed Json: " + ex.getMessage());
        }
    }

    protected Object getCompound() {
        return nbt;
    }

    protected void setCompound(Object tag) {
        nbt = tag;
    }

}
