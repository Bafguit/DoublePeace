package DoublePeace.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import DoublePeace.util.TextureLoader;

import static DoublePeace.DPMod.makeID;
import static DoublePeace.DPMod.makeRelicPath;

public class BrokenTicker extends CustomRelic {
    public static final String ID = makeID("Broken Ticker");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("tick.png"));
    private static final Texture IMG_OUT = TextureLoader.getTexture(makeRelicPath("outline/tick.png"));

    public BrokenTicker() {
        super(ID, IMG, IMG_OUT, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStartPreDraw() {
        for(AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            m.addPower(new SlowPower(m, 0));
        }
    }

    @Override
    public void onSpawnMonster(AbstractMonster monster) {
        monster.addPower(new SlowPower(monster, 0));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }



    @Override
    public AbstractRelic makeCopy() { // always override this method to return a new instance of your relic
        return new BrokenTicker();
    }

}