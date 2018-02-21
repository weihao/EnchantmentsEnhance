package org.pixeltime.healpot.enhancement.manager;

import java.util.HashMap;
import java.util.Set;
import org.bukkit.Bukkit;
import org.pixeltime.healpot.enhancement.Main;
import org.pixeltime.healpot.enhancement.util.ArrayBag;

public class DataManager {
    public int levels;
    public double[] baseChance;
    public double[] chanceIncreasePerFailstack;
    public double[] maximumFailstackApplied;
    public double[] failstackGainedPerFail;
    public double[] costToForceEnchant;
    public double[] fireworks;
    public double[] firewordRounds;
    public String[] name;
    
    public static final HashMap<String, ArrayBag> ImmutableMap =  new HashMap<String, ArrayBag>();
    public DataManager()
    {
        ArrayBag baseChance = new ArrayBag();
        ArrayBag chanceIncreasePerFailstack = new ArrayBag();
        ArrayBag maximumFailstackApplied = new ArrayBag();
        ArrayBag failstackGainedPerFail = new ArrayBag();
        ArrayBag costToForceEnchant = new ArrayBag();
        ArrayBag fireworks = new ArrayBag();
        ArrayBag firewordRounds = new ArrayBag();
        ArrayBag name = new ArrayBag();
        {
            ImmutableMap.put("baseChance", baseChance);
            ImmutableMap.put("chanceIncreasePerFailstack", chanceIncreasePerFailstack);
            ImmutableMap.put("maximumFailstackApplied", maximumFailstackApplied);
            ImmutableMap.put("failstackGainedPerFail", failstackGainedPerFail);
            ImmutableMap.put("costToForceEnchant", costToForceEnchant);
            ImmutableMap.put("fireworks", fireworks);
            ImmutableMap.put("firewordRounds", firewordRounds);
            ImmutableMap.put("name", name);
        }
         Set<String> temp = SettingsManager.config.getConfigurationSection("enhance").getKeys(false);
         levels = temp.size();
         
         Set<String> temp2 = SettingsManager.config.getConfigurationSection("enhance.0").getKeys(false);
         for (String key2: temp2)
         {
             for (int i = 1; i < levels; i ++)
             {
                 ImmutableMap.get("temp2").add(SettingsManager.config.get("enhance." + i + "." + key2));
             }
         }
         
    }
}
