package alpvax.enchantcolours.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import alpvax.common.core.AlpClassTransformer;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class ECFMLLoadingPlugin implements IFMLLoadingPlugin
{
	public static File location;
	public static Map<String, String> classMap = new HashMap<String, String>();

	@Override
	public String[] getLibraryRequestClass()
	{
		return null;
	}

	@Override
	public String[] getASMTransformerClass()
	{
		return null;//new String[]{ECClassTransformer.class.getName()};
	}

	@Override
	public String getModContainerClass()
	{
		return EnchantColoursMod.class.getName();
	}

	@Override
	public String getSetupClass()
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
		location = (File) data.get("coremodLocation");
		initMap();
	}

	private void initMap()
	{
		AlpClassTransformer.addTransformClass("net/minecraft/client/renderer/entity/RenderItem", "bgw", location);
		AlpClassTransformer.addTransformClass("net/minecraft/client/renderer/ItemRenderer", "bfj", location);
		//classMap.put("net/minecraft/client/renderer/entity/RenderItem", "bgw");
		//classMap.put("net/minecraft/client/renderer/ItemRenderer", "bfj");
	}

}
