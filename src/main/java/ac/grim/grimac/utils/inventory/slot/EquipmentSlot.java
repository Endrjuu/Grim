package ac.grim.grimac.utils.inventory.slot;

import ac.grim.grimac.player.GrimPlayer;
import ac.grim.grimac.utils.inventory.EquipmentType;
import ac.grim.grimac.utils.inventory.InventoryStorage;
import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.item.ItemStack;
import com.github.retrooper.packetevents.protocol.item.enchantment.type.EnchantmentTypes;
import com.github.retrooper.packetevents.protocol.player.GameMode;

public class EquipmentSlot extends Slot {
    EquipmentType type;

    public EquipmentSlot(EquipmentType type, InventoryStorage menu, int slot) {
        super(menu, slot);
        this.type = type;
    }

    @Override
    public int getMaxStackSize() {
        return 1;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return type == EquipmentType.getEquipmentSlotForItem(itemStack);
    }

    public boolean mayPickup(GrimPlayer player) {
        ItemStack itemstack = this.getItem();
        return (itemstack.isEmpty() || player.gamemode == GameMode.CREATIVE || itemstack.getEnchantmentLevel(EnchantmentTypes.BINDING_CURSE, PacketEvents.getAPI().getServerManager().getVersion().toClientVersion()) == 0) && super.mayPickup(player);
    }
}
