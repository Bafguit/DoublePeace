package DoublePeace.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import DoublePeace.powers.HidePower;
import DoublePeace.util.TextureLoader;

import static DoublePeace.DPMod.makeID;
import static DoublePeace.DPMod.makeRelicPath;

public class CloakOfAssassin extends CustomRelic {
    public static final String ID = makeID("Cloak Of Assassin");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("cloak.png"));
    private static final Texture IMG_OUT = TextureLoader.getTexture(makeRelicPath("outline/cloak.png"));

    public CloakOfAssassin() {
        super(ID, IMG, IMG_OUT, RelicTier.RARE, LandingSound.MAGICAL);

    }

    @Override
    public void atBattleStartPreDraw() {
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new HidePower(AbstractDungeon.player, 2)));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }



    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new CloakOfAssassin();
    }

}