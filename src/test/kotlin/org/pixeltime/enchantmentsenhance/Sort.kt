package org.pixeltime.enchantmentsenhance

import junit.framework.TestCase
import org.junit.Test
import java.io.File
import java.util.*

class Sort : TestCase() {
    @Test
    fun testSort() {
        try {
            val file = File("sort.txt")
            val sc = Scanner(file)
            val temp = ArrayList<String>()
            while (sc.hasNextLine()) {
                val curr = sc.nextLine()
                temp.add(curr)
            }

            for (String in temp.sortedWith(compareBy({ it.split("Main.getMain().server.pluginManager.registerEvents(")[1].split("(), Main.getMain())")[0] })))
                println(String)
            sc.close()
        }
        catch(ex:Exception)
        {
        }

    }
}