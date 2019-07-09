package org.pixeltime.enchantmentsenhance

import junit.framework.Assert
import junit.framework.TestCase
import org.junit.Test
import org.pixeltime.enchantmentsenhance.version.Version
import java.util.*


class VersionTest : TestCase() {
    @Test
    fun testVersion() {
        var a = Version("1.1")
        var b = Version("1.1.1")
        a.compareTo(b) // return -1 (a<b)
        assertFalse(a.equals(b))    // return false
        assertTrue(a < b)

        a = Version("2.0")
        b = Version("1.9.9")
        a.compareTo(b) // return 1 (a>b)
        assertFalse(a.equals(b))    // return false
        assertTrue(a >= b)

        a = Version("1.0")
        b = Version("1")
        a.compareTo(b) // return 0 (a=b)
        assertTrue(a.equals(b))    // return true
        assertTrue(a == b)

        a = Version("4.4.2")
        b = Version("4.5")
        a.compareTo(b) // return 0 (a<b)
        assertFalse(a.equals(b))    // return true
        assertTrue(a < b)


        a = Version("4.9")
        b = Version("4.9")
        a.compareTo(b) // return 0 (a<b)
        assertTrue(a.equals(b))    // return true
        assertFalse(a < b)
        assertTrue(a >= b)


        val versions = ArrayList<Version>()
        versions.add(Version("2"))
        versions.add(Version("1.0.5"))
        versions.add(Version("1.01.0"))
        versions.add(Version("1.00.1"))
        Assert.assertEquals(Collections.min(versions).version, "1.00.1") // return min version
        Assert.assertEquals(Collections.max(versions).version, "2")// return max version

    }
}