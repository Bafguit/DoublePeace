package DoublePeace.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import DoublePeace.util.TextureLoader;

import static DoublePeace.DPMod.makeID;
import static DoublePeace.DPMod.makeRelicPath;

public class ShadowInNecklace extends CustomRelic {
    public static final String ID = makeID("Shadow In Necklace");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("neck.png"));
    private static final Texture IMG_OUT = TextureLoader.getTexture(makeRelicPath("outline/neck.png"));

    public ShadowInNecklace() {
        super(ID, IMG, IMG_OUT, RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public void atBattleStartPreDraw() {
        AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
        AbstractPower p = new TargetPower(m, 2);
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(m, AbstractDungeon.player, p));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }



    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new ShadowInNecklace();
    }

}