package DoublePeace.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import DoublePeace.powers.UnfortifiedPower;
import DoublePeace.util.TextureLoader;

import static DoublePeace.DPMod.makeID;
import static DoublePeace.DPMod.makeRelicPath;

public class RustyHelmet extends CustomRelic {
    public static final String ID = makeID("Rusty Helmet");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("hel.png"));
    private static final Texture IMG_OUT = TextureLoader.getTexture(makeRelicPath("outline/hel.png"));

    public RustyHelmet() {
        super(ID, IMG, IMG_OUT, RelicTier.RARE, LandingSound.HEAVY);
    }

    public void atTurnStart() {
        this.flash();
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new UnfortifiedPower(m, AbstractDungeon.player, 1)));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new RustyHelmet();
    }
}