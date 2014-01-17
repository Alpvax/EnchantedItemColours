package alpvax.enchantcolours.core;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import alpvax.common.mods.ModData;
import alpvax.enchantcolours.recipes.RecipesEnchantDyesSimple;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.versioning.ArtifactVersion;

public class EnchantColoursMod extends DummyModContainer
{
	public static String BASE_TAG = "EnchantColour";
	public static String RED_TAG = "Red";
	public static String GREEN_TAG = "Green";
	public static String BLUE_TAG = "Blue";
	
	public EnchantColoursMod()
	{
		super(new ModMetadata());
		ModMetadata meta = getMetadata();
		meta.modId = ModInfo.modID;
		meta.name = ModData.getModName(ModInfo.modID);
		meta.version = ModData.getVersionFormatted(ModInfo.modID, ModInfo.version);
		//meta.credits = "";
		meta.authorList = Arrays.asList("Alpvax");
		meta.description = "Mod that allows you to dye the colour of the glint.png texture on items (i.e. enchanted items, potions, signed books)";
		meta.url = "";
        /*Set<ArtifactVersion> requirements = Sets.newHashSet();
        List<ArtifactVersion> dependencies = Lists.newArrayList();
        List<ArtifactVersion> dependants = Lists.newArrayList();
        Loader.instance().computeDependencies("after:"+ModData.coreModID, requirements, dependencies, dependants);
        meta.requiredMods = requirements;
        meta.dependencies = dependencies;
        meta.dependants = dependants;*/
		//meta.updateUrl = "";
		//meta.screenshots = new String[0];
		//meta.logoFile = "";
	}

	@Override
	public boolean registerBus(EventBus bus, LoadController controller)
	{
		bus.register(this);
		return true;
	}
	
	@Subscribe
	public void init(FMLInitializationEvent evt)
	{
		GameRegistry.addRecipe(new RecipesEnchantDyesSimple());
	}

}
