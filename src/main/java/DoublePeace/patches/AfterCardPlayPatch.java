package DoublePeace.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.actions.ChaserUtil;
import DoublePeace.actions.TargetAttackAction;

@SpirePatch(
        clz=AbstractPlayer.class,
        method="useCard"
)
public class AfterCardPlayPatch
{

    public static void Postfix(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse)
    {
        System.out.println("AfterCardPlayPatcher");
        ChaserUtil.cardIsUsed(c);
        if(ChaserUtil.isFirstCardPerTurn()) {
            ChaserUtil.setFirstCardIsDone();
        }
        if(c.type == AbstractCard.CardType.ATTACK) {
            AbstractDungeon.actionManager.addToBottom(new TargetAttackAction());
        }
    }
}