# Skyblock Remake

![Skyblock Remake Banner](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/banner.png?raw=true)

<p align="center">
  <img src="https://img.shields.io/github/license/Sweattypalms/skyblock-remake" alt="License">
  <img src="https://img.shields.io/github/languages/code-size/Sweattypalms/skyblock-remake" alt="Code Size">
  <img src="https://img.shields.io/badge/language-Java-green" alt="Language">
</p>
<p align="center">
  <a href="https://discord.gg/Ew4u4TRbQ6">
    <img src="https://img.shields.io/discord/962270718568046592?color=7289DA&label=Join%20our%20Discord&logo=discord&logoColor=white" alt="Join our Discord&style=for-the-badge">
  </a>
</p>


<b>Skyblock Remake </b> is an open-source fan project inspired by Hypixel's Skyblock. It aims not only to recreate the familiar experience but also to offer a platform for fellow developers to learn and grow.

🚨 **Disclaimer:** This project is a fan-made recreation of "Hypixel" Skyblock. It is not affiliated with or endorsed by Hypixel or its associated products. All rights and content belong to their respective owners.

## Why This Project Exists

I'm a high school kid, and I really enjoy programming, like you can make anything you want.
I got a taste of that when I started developing this plugin two years ago. It was pretty basic back then, 
but man, did it help me get good at programming, design patterns, efficient code, maths, everything. 
I feel like it would've helped me a lot if there were projects that I could've taken inspiration from, 
so I'm putting this out there so someone on the same path can benefit a lot from this. 
This is my way of paying it back, y'know. Enjoy! 

## Features

<details open>
<summary>Click to expand</summary>

### Player Features

-   **Stats System:** Complete stat system.
![Stats System](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/stats/stats_system.gif?raw=true)
-   **Slayers (W.I.P):** Start quests and defeat bosses.
*Start a Slayer Quest*
![Slayer Quest](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/slayer/slayer_start.gif?raw=true)
*Get the necessary xp to spawn the boss*
![Slayer Quest](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/slayer/slayer_xp.gif?raw=true)
*Defeat the boss*
![Slayer Boss](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/slayer/slayer_kill.gif?raw=true)
-   **Skills (W.I.P):** Progress and develop your player skills.
-   **Fan favourite items:** Most loved items from Hypixel Skyblock (e.g. Hyperion, Terminator, etc.)
![Hyperion & Terminator melting mobs](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/items/items_showcase.gif?raw=true)
-   **Regions:** Explore different areas and regions.
-   **Scoreboard:** See objectives, your balance and quests.
![Scoreboard](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/misc/scoreboard.png?raw=true)
-   **Mobs System:** Engage with various in-game creatures.
![Mobs System](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/mobs/mobs_showcase.png?raw=true)
-   **Customize Items:** Customize your items by applying enchants, reforges, and much more!
![Customize Items](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/items/customize_item_showcase.png?raw=true)
-   **Ender Dragon Fight:** Battle the mighty Ender Dragon! Altar system with Custom Dragon Pathfinding + Dragon egg animation.
![End World](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/end/end_world.png?raw=true)
![Altar System](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/end/altar.gif?raw=true)
![Spawn Animation](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/end/spawn_animation.gif?raw=true)
![Dragon Pathfinding](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/end/dragon_pathfinding.gif?raw=true)
![Dragon Ability](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/end/dragon_ability.gif?raw=true)
![Fight Dragon)](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/end/fight_dragon.gif?raw=true)
![Dragon Death](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/end/dragon_death_message.png?raw=true)
- **Dungeons (W.I.P):** Procedurally generated dungeons with bosses and loot. (Extremely W.I.P)
![Dungeons Generate Map](https://github.com/Sweattypalms/skyblock-remake/blob/master/README/assets/gameplay/dungeons/dungeon_generate_map.gif?raw=true)

### Developer Features
<details>
<summary>Features with Code examples</summary>


-   **Annotation-Based Command System:** Efficiently handle and manage in-game commands.
```java
@Command(name = "example", description = "Example command", op = true)  
public void exampleCommand(Player player, String[] args) {  
  player.sendMessage(ChatColor.RED + "This is an example command!");  
    player.sendMessage(ChatColor.YELLOW + Strings.join(args, " "));  
}  
	  
@TabCompleter(command = "example")  
public List<String> exampleTabCompleter(Player player, String[] args) {  
  return List.of("example", "example2");  
}
```
-   **Hologram System:** Create both static and dynamic holograms with ease.
```java
Hologram hologram = new Hologram(  
		"Example Text",  
		new Location(Bukkit.getWorld("world"), 0, 100,0)  
);	
```
-   **Event-Based System:** Harness the power of events for versatile gameplay elements.
```java
@EventHandler  
public void onXpGain(SkyblockXpEvent event){  
	String name = event.getSkyblockPlayer().getPlayer().getName();  
	System.out.println(name + " gained " + event.getXp());  
}
```
-   **Particle Helpers:** Enhance visual elements with particle effects.
```java
Player player = ...;
// f (0.1) =>  Starting radius for the spiral.
// delta (1.5) =>  Max radius for the spiral
MathHelper.spiralParticles(player, 0.1, 1.5, Particle.FLAME);
```
-   **Auto Initializing:** Automatic setup for various modules including mobs, items, commands, and listeners.
-   **OOP-Based Systems:** Object-Oriented Programming based systems for items, mobs, and UIs.
```java
	/* Example Item */
public class LightningChestplate extends SkyblockItem implements IHasAbility, IDyedArmor {  
	public static final String ID = "lightning_chestplate";  
	private static final Map<Stats, Double> stats = new HashMap<>(Map.of(  
		Stats.HEALTH, 30d  
	));  
  
    public LightningChestplate() {  
		super(  
			ID,  
			"Lightning Armor Chestplate",  
			Material.LEATHER_CHESTPLATE,  
			null,  // Static lore
			stats,  
			Rarity.SPECIAL,  
			SkyblockItemType.CHESTPLATE  
		);  
    }  
  
	@Override  
	public List<Ability> getAbilities() {  
		return List.of(AbilityManager.LIGHTNING_ARMOR_ABILITY);  
	}  
  
	@Override  
	public String getHexColor() {  
		return "FFFF00";  
	}  
}
```
-   **UI System:** Robust UI system with callback features for clickable items and static GUIs.
```java
public class TestGUI extends BaseGUI {
	private static final int SIZE = 6 * 9; // 6 rows of 9 slots

	public TestGUI() {
		super(SIZE, "Test GUI");
	}

	@Override
	public void initializeItems(Player player){
		this.fillBorder(BorderType.ALL); // All around border
		ItemStack testItem = new ItemStack(Material.DIAMOND_SWORD);
		this.setItemAt(3, 4, testItem); // At (3,4)
		this.setNextItem(testItem); // Next available slot
	}
}
	
```
</details>

</details>

## Commands

<details>
<summary>Click to expand</summary>

### Admin Commands

-   `/mob <id>`
-   `/sitem <id>`
-   `/stat <stat_id> <amt>`
-   `/upgrade`
-   `/slayer_id`
-   `/?cancel_slayer`
-   `/sbrl`

### Player Commands

-   `/?slayer_gui`
-   `/hub`
-   `/test`

### Utils Commands

-   `/gms`
-   `/gmc`
-   `/gmss`
-   `/fix_inventory`

</details>


## Setup Guide

### Prerequisites

-   Multiverse Core
-   Plugin Manager (Optional: For reloading plugins)

1.  Download and install [IntelliJ IDEA](https://www.jetbrains.com/idea/download).
2.  Obtain Spigot's build tools for version 1.17.1 from [here](https://www.spigotmc.org/wiki/buildtools/).
3.  Edit the code as desired.
4.  Compile the code and place the output in your server's plugins directory.
5. Enjoy!
##### ---or---
1. Download the latest plugin's release from [here](https://github.com/Sweattypalms/skyblock-remake/releases/).
2. Place it in your server's plugin directory.
3. Enjoy!


## Contact or Support

-   For issues, queries, or suggestions, please use the project's [GitHub Issues](https://github.com/Sweattypalms/skyblock-remake/issues).
-   Join our Discord server for active discussions and updates: [Join Discord](https://discord.gg/Ew4u4TRbQ6).
-   For personal queries or urgent matters, you can contact me on Discord: `@sweattypalms`.

## Contribution

Contributions are welcome! If you're passionate about Skyblock and want to contribute to an open-source project, we'd love to have you onboard! Before creating a pull request, please ensure:

-   Your code is tested and working as expected.
-   Adherence to best coding practices and conventions.
-   I will review all code before merging to ensure quality and consistency.


## License

This project is licensed under the [GPLv3 License](LICENSE).
