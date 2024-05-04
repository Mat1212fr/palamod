
package palamod.item;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;

public class RoulettepaladiennediscItem extends RecordItem {
	public RoulettepaladiennediscItem() {
		super(4, () -> ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("palamod:roulette_paladienne")), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 2080);
	}
}
