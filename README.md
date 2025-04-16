# TANTooltips

Since the original mod WaterSkin has a lot of bugs, like doubling the amount of water restored after drinking, and it's practically abandoned. I've created a mod that adds just the thirst tooltips to the game. 
Since this mod only changes ui and gets all of the necessary values from the config file you don't even need Tough As Nails fot it to work. (But the mod itself is pretty pointless without is)

You can assign a tooltip to any iten by adding it in the config file.

Example:

minecraft:item|T20|H20 // Assigns thirst 20 and hydration 20 to minecraft:item with metadata == 0

minecraft:item:0|T20|H20 // Same result as in the first example

minecraft:another_item:5|T20|H20 // You also can spesify metadata in this case it's five

minecraft:another_item:*|T20|H20 // Or assign a Tooltip to all metadata using the symbol "*"
