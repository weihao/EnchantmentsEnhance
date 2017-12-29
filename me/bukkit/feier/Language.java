package me.bukkit.feier;

public class Language {
	public void addCnDefault(SettingsManager s) {
		s.lang.addDefault("checkingVersion", "你正在使用的插件版本是v%version%.");
		s.lang.addDefault("successRate", "你手中的物品的成功率为%chance%%.");
		s.lang.addDefault("onEnable", "强化插件已开启!");
		s.lang.addDefault("onDisable", "强化插件已禁用!");
		s.lang.addDefault("consoleCommand", "服务器不可以使用这个指令哟!");
		s.lang.addDefault("enhanceSuccess", "强化成功!");
		s.lang.addDefault("enhanceFailed", "强化失败!你失去了当前强化等级!");
		s.lang.addDefault("itemInvalid", "你手里拿着的武器不可以强化!");

		s.lang.options().copyDefaults(true);
		s.saveLang();
	}
}
