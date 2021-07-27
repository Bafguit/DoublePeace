package DoublePeace.abstracts;

import DoublePeace.DPMod;
import DoublePeace.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class DPRelic extends CustomRelic {

    public AbstractCard.CardColor color;

    public DPRelic(String setId, RelicTier tier, LandingSound sfx, AbstractCard.CardColor color) {
        super(DPMod.makeID(setId), DPMod.makeImgPath("relics/" + setId + ".png"), tier, sfx);
        this.color = color;
        this.outlineImg = TextureLoader.getTexture(DPMod.makeImgPath("relics/outline/" + setId + ".png"));
    }

}
