package fi.dy.masa.malilib.mixin;


import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import fi.dy.masa.malilib.event.RenderEventHandler;

@Mixin(HandledScreen.class)
public class MixinHandledScreen
{
    @Shadow protected Slot focusedSlot;

    @Inject(method = "drawMouseoverTooltip", at = @At(value = "HEAD"))
    private void onRenderTooltip(DrawContext drawContext, int x, int y, CallbackInfo ci)
    {
        if (this.focusedSlot != null && this.focusedSlot.hasStack()) {
            ItemStack itemStack = this.focusedSlot.getStack();
            ((RenderEventHandler) RenderEventHandler.getInstance()).onRenderTooltipLast(drawContext, itemStack, x, y);
        }
    }
}
