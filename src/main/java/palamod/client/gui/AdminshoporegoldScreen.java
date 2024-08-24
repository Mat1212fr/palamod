package palamod.client.gui;

import palamod.world.inventory.AdminshoporegoldMenu;

import palamod.procedures.ReturnadminshoporemenuProcedure;
import palamod.procedures.ReturnadminshopmainmenuProcedure;
import palamod.procedures.ClosetheguitransProcedure;
import palamod.procedures.AdshoppreviewamountgoldProcedure;

import palamod.network.AdminshoporegoldButtonMessage;

import palamod.PalamodMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

public class AdminshoporegoldScreen extends AbstractContainerScreen<AdminshoporegoldMenu> {
	private final static HashMap<String, Object> guistate = AdminshoporegoldMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox number_buy;
	Button button_buy;
	Button button_sell;
	ImageButton imagebutton_left_gray_line;
	ImageButton imagebutton_cross_no_button;
	ImageButton imagebutton_arrow_adminshop;
	ImageButton imagebutton_home_pixel_adminshop;

	public AdminshoporegoldScreen(AdminshoporegoldMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 176;
		this.imageHeight = 166;
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(guiGraphics);
		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		number_buy.render(guiGraphics, mouseX, mouseY, partialTicks);
		this.renderTooltip(guiGraphics, mouseX, mouseY);
		if (mouseX > leftPos + 123 && mouseX < leftPos + 135 && mouseY > topPos + 5 && mouseY < topPos + 20)
			guiGraphics.renderTooltip(font, Component.literal(ReturnadminshopmainmenuProcedure.execute()), mouseX, mouseY);
		if (mouseX > leftPos + 138 && mouseX < leftPos + 153 && mouseY > topPos + 6 && mouseY < topPos + 20)
			guiGraphics.renderTooltip(font, Component.literal(ReturnadminshoporemenuProcedure.execute()), mouseX, mouseY);
		if (mouseX > leftPos + 155 && mouseX < leftPos + 171 && mouseY > topPos + 4 && mouseY < topPos + 20)
			guiGraphics.renderTooltip(font, Component.literal(ClosetheguitransProcedure.execute()), mouseX, mouseY);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();

		guiGraphics.blit(new ResourceLocation("palamod:textures/screens/gui176_166.png"), this.leftPos + -1, this.topPos + 0, 0, 0, 176, 166, 176, 166);

		guiGraphics.blit(new ResourceLocation("palamod:textures/screens/right_gray_line.png"), this.leftPos + 75, this.topPos + 0, 0, 0, 100, 24, 100, 24);

		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (number_buy.isFocused())
			return number_buy.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		number_buy.tick();
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		String number_buyValue = number_buy.getValue();
		super.resize(minecraft, width, height);
		number_buy.setValue(number_buyValue);
	}

	@Override
	protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.adminshoporegold.label_gold"), 72, 7, -1, false);
		guiGraphics.drawString(this.font,

				AdshoppreviewamountgoldProcedure.execute(world, entity, guistate), 29, 74, -4671036, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.adminshoporegold.label_sell_price_30"), 41, 27, -4671036, false);
		guiGraphics.drawString(this.font, Component.translatable("gui.palamod.adminshoporegold.label_buy_price_35"), 47, 42, -4671036, false);
	}

	@Override
	public void init() {
		super.init();
		number_buy = new EditBox(this.font, this.leftPos + 27, this.topPos + 88, 118, 18, Component.translatable("gui.palamod.adminshoporegold.number_buy")) {
			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.palamod.adminshoporegold.number_buy").getString());
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion(Component.translatable("gui.palamod.adminshoporegold.number_buy").getString());
				else
					setSuggestion(null);
			}
		};
		number_buy.setSuggestion(Component.translatable("gui.palamod.adminshoporegold.number_buy").getString());
		number_buy.setMaxLength(32767);
		guistate.put("text:number_buy", number_buy);
		this.addWidget(this.number_buy);
		button_buy = Button.builder(Component.translatable("gui.palamod.adminshoporegold.button_buy"), e -> {
			if (true) {
				PalamodMod.PACKET_HANDLER.sendToServer(new AdminshoporegoldButtonMessage(0, x, y, z));
				AdminshoporegoldButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 26, this.topPos + 108, 40, 20).build();
		guistate.put("button:button_buy", button_buy);
		this.addRenderableWidget(button_buy);
		button_sell = Button.builder(Component.translatable("gui.palamod.adminshoporegold.button_sell"), e -> {
			if (true) {
				PalamodMod.PACKET_HANDLER.sendToServer(new AdminshoporegoldButtonMessage(1, x, y, z));
				AdminshoporegoldButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 100, this.topPos + 108, 46, 20).build();
		guistate.put("button:button_sell", button_sell);
		this.addRenderableWidget(button_sell);
		imagebutton_left_gray_line = new ImageButton(this.leftPos + -1, this.topPos + 0, 100, 24, 0, 0, 24, new ResourceLocation("palamod:textures/screens/atlas/imagebutton_left_gray_line.png"), 100, 48, e -> {
		});
		guistate.put("button:imagebutton_left_gray_line", imagebutton_left_gray_line);
		this.addRenderableWidget(imagebutton_left_gray_line);
		imagebutton_cross_no_button = new ImageButton(this.leftPos + 155, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("palamod:textures/screens/atlas/imagebutton_cross_no_button.png"), 16, 32, e -> {
			if (true) {
				PalamodMod.PACKET_HANDLER.sendToServer(new AdminshoporegoldButtonMessage(3, x, y, z));
				AdminshoporegoldButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		});
		guistate.put("button:imagebutton_cross_no_button", imagebutton_cross_no_button);
		this.addRenderableWidget(imagebutton_cross_no_button);
		imagebutton_arrow_adminshop = new ImageButton(this.leftPos + 137, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("palamod:textures/screens/atlas/imagebutton_arrow_adminshop.png"), 16, 32, e -> {
			if (true) {
				PalamodMod.PACKET_HANDLER.sendToServer(new AdminshoporegoldButtonMessage(4, x, y, z));
				AdminshoporegoldButtonMessage.handleButtonAction(entity, 4, x, y, z);
			}
		});
		guistate.put("button:imagebutton_arrow_adminshop", imagebutton_arrow_adminshop);
		this.addRenderableWidget(imagebutton_arrow_adminshop);
		imagebutton_home_pixel_adminshop = new ImageButton(this.leftPos + 121, this.topPos + 4, 16, 16, 0, 0, 16, new ResourceLocation("palamod:textures/screens/atlas/imagebutton_home_pixel_adminshop.png"), 16, 32, e -> {
			if (true) {
				PalamodMod.PACKET_HANDLER.sendToServer(new AdminshoporegoldButtonMessage(5, x, y, z));
				AdminshoporegoldButtonMessage.handleButtonAction(entity, 5, x, y, z);
			}
		});
		guistate.put("button:imagebutton_home_pixel_adminshop", imagebutton_home_pixel_adminshop);
		this.addRenderableWidget(imagebutton_home_pixel_adminshop);
	}
}
