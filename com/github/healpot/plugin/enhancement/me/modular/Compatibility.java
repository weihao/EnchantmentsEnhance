package com.github.healpot.plugin.enhancement.me.modular;

import org.bukkit.Bukkit;

import com.github.healpot.plugin.enhancement.me.effect.Glow;
import com.github.healpot.plugin.enhancement.me.effect.Glow_1_10_R1;
import com.github.healpot.plugin.enhancement.me.effect.Glow_1_11_R1;
import com.github.healpot.plugin.enhancement.me.effect.Glow_1_12_R1;
import com.github.healpot.plugin.enhancement.me.effect.Glow_1_7_R4;
import com.github.healpot.plugin.enhancement.me.effect.Glow_1_8_R3;
import com.github.healpot.plugin.enhancement.me.effect.Glow_1_9_R2;
import com.github.healpot.plugin.enhancement.me.effect.PlaySound;
import com.github.healpot.plugin.enhancement.me.effect.PlaySound_1_10_R1;
import com.github.healpot.plugin.enhancement.me.effect.PlaySound_1_11_R1;
import com.github.healpot.plugin.enhancement.me.effect.PlaySound_1_12_R1;
import com.github.healpot.plugin.enhancement.me.effect.PlaySound_1_7_R4;
import com.github.healpot.plugin.enhancement.me.effect.PlaySound_1_8_R3;
import com.github.healpot.plugin.enhancement.me.effect.PlaySound_1_9_R2;

public class Compatibility {
	public Glow glow;
	public PlaySound playsound;

	public boolean setupGlow() {

		String version;

		try {

			version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

		} catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
			return false;
		}

		Bukkit.getServer().getLogger().info("Your server is running version " + version);

		if (version.equals("v1_7_R4")) {
			glow = new Glow_1_7_R4();
		} else if (version.equals("v1_8_R3")) {
			glow = new Glow_1_8_R3();
		} else if (version.equals("v1_9_R2")) {
			glow = new Glow_1_9_R2();
		} else if (version.equals("v1_10_R1")) {
			glow = new Glow_1_10_R1();
		} else if (version.equals("v1_11_R1")) {
			glow = new Glow_1_11_R1();
		} else if (version.equals("v1_12_R1")) {
			glow = new Glow_1_12_R1();
		}

		return glow != null;
	}

	public boolean setupSound() {

		String version;

		try {

			version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

		} catch (ArrayIndexOutOfBoundsException whatVersionAreYouUsingException) {
			return false;
		}
		if (version.equals("v1_7_R4")) {
			playsound = new PlaySound_1_7_R4();
		} else if (version.equals("v1_8_R3")) {
			playsound = new PlaySound_1_8_R3();
		} else if (version.equals("v1_9_R2")) {
			playsound = new PlaySound_1_9_R2();
		} else if (version.equals("v1_10_R1")) {
			playsound = new PlaySound_1_10_R1();
		} else if (version.equals("v1_11_R1")) {
			playsound = new PlaySound_1_11_R1();
		} else if (version.equals("v1_12_R1")) {
			playsound = new PlaySound_1_12_R1();
		}

		return playsound != null;
	}
}
