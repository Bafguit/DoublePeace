package DoublePeace.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import DoublePeace.actions.TargetAttackAction;
import DoublePeace.patches.interfaces.OnAfterTargetAttackSubscriber;
import DoublePeace.util.TextureLoader;

import java.util.ArrayList;

import static DoublePeace.DPMod.makeID;
import static DoublePeace.DPMod.makeRelicPath;

public class BoxCutter extends CustomRelic implements OnAfterTargetAttackSubscriber {
    public static final String ID = makeID("Box Cutter");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("cutter.png"));
    private static final Texture IMG_OUT = TextureLoader.getTexture(makeRelicPath("outline/cutter.png"));

    public BoxCutter() {
        super(ID, IMG, IMG_OUT, RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    public void atTurnStart() {
        this.beginPulse();
        this.pulse = true;
    }

    @Override
    public void onVictory() {
        this.stopPulse();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new BoxCutter();
    }

    @Override
    public void onAfterTargetAttack(ArrayList<AbstractMonster> mo, int damage) {
        if(this.pulse) {
            this.flash();
            this.stopPulse();
            this.addToBot(new TargetAttackAction());
            this.addToBot(new TargetAttackAction());
        }
    }
}