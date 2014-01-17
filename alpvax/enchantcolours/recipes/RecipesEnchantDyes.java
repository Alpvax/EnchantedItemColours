package alpvax.enchantcolours.recipes;

import static alpvax.enchantcolours.core.EnchantColoursMod.BASE_TAG;
import static alpvax.enchantcolours.core.EnchantColoursMod.BLUE_TAG;
import static alpvax.enchantcolours.core.EnchantColoursMod.GREEN_TAG;
import static alpvax.enchantcolours.core.EnchantColoursMod.RED_TAG;

import java.util.ArrayList;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

public class RecipesEnchantDyes implements IRecipe
{
    /**
     * Used to check if a recipe matches current crafting inventory
     */
    public boolean matches(InventoryCrafting par1InventoryCrafting, World par2World)
    {
        ItemStack itemstack = null;
        ArrayList arraylist = new ArrayList();

        for(int i = 0; i < par1InventoryCrafting.getSizeInventory(); ++i)
        {
            ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(i);

            if(itemstack1 != null)
            {
                if(itemstack1.isItemEnchanted())
                {
                    itemstack = itemstack1;
                }
                else
                {
                    if(itemstack1.itemID != Item.dyePowder.itemID)//!OreDictionary.itemMatches(new ItemStack(Item.dyePowder), itemstack1, false))
                    {
                        return false;
                    }
                    arraylist.add(itemstack1);
                }
            }
        }

        return itemstack != null && !arraylist.isEmpty();
    }

    /**
     * Returns an Item that is the result of this recipe
     */
    public ItemStack getCraftingResult(InventoryCrafting par1InventoryCrafting)
    {
        ItemStack itemstack = null;
        int[] aint = new int[3];
        int i = 0;
        int j = 0;
        Item item = null;
        //int k;
        //int l;
        //float f;
        //float f1;
        //int i1;
        //float[] colour = new float[3];
        //float red;
        //float green;
        //float blue;

        for(int k = 0; k < par1InventoryCrafting.getSizeInventory(); ++k)
        {
            ItemStack itemstack1 = par1InventoryCrafting.getStackInSlot(k);

            if(itemstack1 != null)
            {
                if(itemstack1.isItemEnchanted())
                {
                    item = itemstack1.getItem();

                    if (itemstack != null)
                    {
                        return null;
                    }

                    itemstack = itemstack1.copy();
                    itemstack.stackSize = 1;

                    if(itemstack.getTagCompound().hasKey(BASE_TAG))
                    {
                        NBTTagCompound nbt = itemstack.getTagCompound().getCompoundTag(BASE_TAG);
                        float red = nbt.getFloat(RED_TAG) / 255.0F;
                        float green = nbt.getFloat(GREEN_TAG) / 255.0F;
                        float blue = nbt.getFloat(BLUE_TAG) / 255.0F;
                        /*f = (float)(l >> 16 & 255) / 255.0F;
                        f1 = (float)(l >> 8 & 255) / 255.0F;
                        float f2 = (float)(l & 255) / 255.0F;*/
                        i = (int)((float)i + Math.max(red, Math.max(green, blue)) * 255.0F);
                        aint[0] = (int)((float)aint[0] + red * 255.0F);
                        aint[1] = (int)((float)aint[1] + green * 255.0F);
                        aint[2] = (int)((float)aint[2] + blue * 255.0F);
                        /*colour[0] = (colour[0] + red * 255.0F);
                        colour[1] = (colour[1] + green * 255.0F);
                        colour[2] = (colour[2] + blue * 255.0F);*/
                        ++j;
                    }
                }
                else
                {
                    if(itemstack1.itemID != Item.dyePowder.itemID)//!OreDictionary.itemMatches(new ItemStack(Item.dyePowder), itemstack1, false))
                    {
                        return null;
                    }

                    float[] afloat = EntitySheep.fleeceColorTable[BlockColored.getBlockFromDye(itemstack1.getItemDamage())];
                    int i1 = (int)(afloat[0] * 255.0F);
                    int j1 = (int)(afloat[1] * 255.0F);
                    int k1 = (int)(afloat[2] * 255.0F);
                    i += Math.max(j1, Math.max(k1, i1));
                    aint[0] += j1;
                    aint[1] += k1;
                    aint[2] += i1;
                    /*colour[0] += (afloat[0] * 255.0F);
                    colour[1] += (afloat[1] * 255.0F);
                    colour[2] += (afloat[2] * 255.0F);*/
                    ++j;
                }
            }
        }

        if(item == null)
        {
            return null;
        }
        else
        {
        	int k0 = aint[0] / j;
            int k1 = aint[1] / j;
            int k2 = aint[2] / j;
            float f = (float)i / (float)j;
            float f1 = (float)Math.max(k0, Math.max(k1, k2));
            k0 = (int)((float)k0 * f / f1);
            k1 = (int)((float)k1 * f / f1);
            k2 = (int)((float)k2 * f / f1);
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setInteger(RED_TAG, k0);
            nbt.setInteger(GREEN_TAG, k1);
            nbt.setInteger(BLUE_TAG, k2);
            System.out.println("RGB: "+k0+", "+k1+", "+k2);
            itemstack.stackTagCompound.setCompoundTag(BASE_TAG, nbt);
            return itemstack;
        }
    }

    /**
     * Returns the size of the recipe area
     */
    public int getRecipeSize()
    {
        return 10;
    }

    public ItemStack getRecipeOutput()
    {
        return null;
    }
}
