package DoublePeace.abstracts;

import DoublePeace.DPMod;
import DoublePeace.util.TextureLoader;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static DoublePeace.DPMod.*;

public abstract class DPRelic extends CustomRelic {

    public AbstractCard.CardColor color;

    public DPRelic(String id, RelicTier tier, LandingSound sfx, AbstractCard.CardColor color) {
        super(id, makeImgPath("relics/" + repID(id) + ".png"), tier, sfx);
        this.color = color;
        this.outlineImg = TextureLoader.getTexture(makeImgPath("relics/outline/" + repID(id) + ".png"));
    }

}
