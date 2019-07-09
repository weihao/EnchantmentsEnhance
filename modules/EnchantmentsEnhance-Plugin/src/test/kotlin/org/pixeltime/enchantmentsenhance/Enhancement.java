package org.pixeltime.enchantmentsenhance;

public class Enhancement {
    public static void main(String[] args) {
        println("levels:", 0);


        for (int level = 1; level <= 20; level++) {
            double baseChance = 1 - (Math.pow(level - 1, 2) * 0.0025);

            double chanceIncreasePerFailstack = baseChance * 0.025;

            int maximumFailstackApplied = level * 10;

            double downgradeChanceIfFail = 0.25 + level * 0.025;
            double destroyChanceIfFail = 0.025 + level * 0.025;

            int costToForceEnchant = (int) (1 / Math.pow(baseChance, 3));


            println(String.format("%d:", level), 1);
            println(String.format("baseChance: %f", baseChance), 2);
            println(String.format("chanceIncreasePerFailstack: %f", chanceIncreasePerFailstack), 2);
            println(String.format("maximumFailstackApplied: %d", maximumFailstackApplied), 2);
            println(String.format("failstackGainedPerFail: 1"), 2);
            println(String.format("costToForceEnchant: %d", costToForceEnchant), 2);
            println(String.format("downgradeChanceIfFail: %f", downgradeChanceIfFail), 2);
            println(String.format("destroyChanceIfFail: %f", destroyChanceIfFail), 2);
            println(String.format("requireConcentratedStones: %s", (level >= 15) ? "true" : "false"), 2);
            println(String.format("broadcastEnhance: %s", (level >= 18) ? "true" : "false"), 2);
            println(String.format(" fireworkIfSuccessful: %s", (level >= 7) ? "true" : "false"), 2);
            println(String.format("fireworkRounds: 1"), 2);
            println(String.format("prefix: \"\""), 2);
            println(String.format("suffix: \"&8+%d\"", level), 2);
            println(String.format("enchantments: []"), 2);
            println(String.format("lore: []"), 2);

        }
    }

    public static void println(String s, int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        sb.append(s);
        System.out.println(sb.toString());
    }
}
