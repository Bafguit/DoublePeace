package DoublePeace.abstracts;

import DoublePeace.util.TextureLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import sun.awt.www.content.image.png;

import static DoublePeace.DPMod.*;

public abstract class DPPower extends AbstractPower {

    public AbstractCreature source;

    public DPPower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, AbstractCreature source, int amount) {
        this(id, powerType, isTurnBased, owner, source);
        this.amount = amount;
    }

    public DPPower(String id, PowerType powerType, boolean isTurnBased, AbstractCreature owner, AbstractCreature source) {
        String temp = repID(id);

        PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(id);
        this.ID = id;
        this.isTurnBased = isTurnBased;

        this.name = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;

        this.owner = owner;
        this.source = source;
        this.type = powerType;

        Texture normalTexture = TextureLoader.getTexture(makeImgPath("powers/" + temp + "32.png"));
        Texture hiDefImage = TextureLoader.getTexture(makeImgPath("powers/" + temp + "84.png"));
        if (hiDefImage != null) {
            region128 = new TextureAtlas.AtlasRegion(hiDefImage, 0, 0, hiDefImage.getWidth(), hiDefImage.getHeight());
            if (normalTexture != null)
                region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        } else if (normalTexture != null) {
            this.img = normalTexture;
            region48 = new TextureAtlas.AtlasRegion(normalTexture, 0, 0, normalTexture.getWidth(), normalTexture.getHeight());
        }

        this.updateDescription();
    }

    @Override
    public final void updateDescription() {
        updateDesc();
    }

    public abstract void updateDesc();
}