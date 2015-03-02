package code.elix_x.mods.btam.core.horses;

import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.LayeredTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.google.common.collect.Maps;

@SideOnly(Side.CLIENT)
public class NewEntityHorseRenderer extends RenderLiving
{
    private static final Map textures = Maps.newHashMap();
    private static final ResourceLocation whiteHorseTextures = new ResourceLocation("textures/entity/horse/horse_white.png");
    private static final ResourceLocation muleTextures = new ResourceLocation("textures/entity/horse/mule.png");
    private static final ResourceLocation donkeyTextures = new ResourceLocation("textures/entity/horse/donkey.png");
    private static final ResourceLocation zombieHorseTextures = new ResourceLocation("textures/entity/horse/horse_zombie.png");
    private static final ResourceLocation skeletonHorseTextures = new ResourceLocation("textures/entity/horse/horse_skeleton.png");
    private static final String __OBFID = "CL_00001000";

    public NewEntityHorseRenderer(RenderManager p_i46170_1_, NewModelHorse p_i46170_2_, float p_i46170_3_)
    {
        super(p_i46170_1_, p_i46170_2_, p_i46170_3_);
    }

    protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_)
    {
        this.preRenderCallback((NewEntityHorse)p_77041_1_, p_77041_2_);
    }
    
    protected void preRenderCallback(NewEntityHorse p_180580_1_, float p_180580_2_)
    {
        float f1 = 1.0F;
        int i = p_180580_1_.getHorseType();

        if (i == 1)
        {
            f1 *= 0.87F;
        }
        else if (i == 2)
        {
            f1 *= 0.92F;
        }

        GlStateManager.scale(f1, f1, f1);
        super.preRenderCallback(p_180580_1_, p_180580_2_);
    }

    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return this.getEntityTexture((NewEntityHorse)entity);
    }
    
    protected ResourceLocation getEntityTexture(NewEntityHorse horse)
    {
        if (!horse.renderPartially())
        {
            switch (horse.getHorseType())
            {
                case 0:
                default:
                    return whiteHorseTextures;
                case 1:
                    return donkeyTextures;
                case 2:
                    return muleTextures;
                case 3:
                    return zombieHorseTextures;
                case 4:
                    return skeletonHorseTextures;
            }
        }
        else
        {
            return this.getEntityTexturePartial(horse);
        }
    }

    private ResourceLocation getEntityTexturePartial(NewEntityHorse horse)
    {
        String s = horse.getHorseTexture();

        if (!horse.func_175508_bO())
        {
            return null;
        }
        else
        {
            ResourceLocation resourcelocation = (ResourceLocation)textures.get(s);

            if (resourcelocation == null)
            {
                resourcelocation = new ResourceLocation(s);
                Minecraft.getMinecraft().getTextureManager().loadTexture(resourcelocation, new LayeredTexture(horse.getVariantTexturePaths()));
                textures.put(s, resourcelocation);
            }

            return resourcelocation;
        }
    }
}
