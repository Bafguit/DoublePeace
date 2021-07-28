package DoublePeace.cards.rare;

import DoublePeace.abstracts.DPCard;
import DoublePeace.characters.DoublePeace;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static DoublePeace.DPMod.makeID;
import static com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import static com.megacrit.cardcrawl.cards.DamageInfo.DamageType;

public class Hardbass extends DPCard {

    public static final String ID = makeID("Hardbass"); //카드 아이디. 띄어쓰기는 안하는게 좋음
    private static final CardType TYPE = CardType.ATTACK; // 카드 타입. ATTACK, SKILL, POWER, CURSE(저주), STATUS(상태이상)
    private static final CardColor COLOR = DoublePeace.Enums.COLOR_DP; //카드 색깔. COLORLESS(무색 카드), CURSE(저주)
    private static final CardRarity RARITY = CardRarity.RARE; //희귀도. BASIC, SPECIAL, COMMON, UNCOMMON, RARE, CURSE
    private static final CardTarget TARGET = CardTarget.ENEMY; //타겟. ENEMY, ALL_ENEMY, SELF, NONE, SELF_AND_ENEMY, ALL

    private static final int COST = 0;
    private static final int DAMAGE = 14;
    private static final int UP_DAMAGE = 5;

    public Hardbass() {
        super(ID, COST, TYPE, COLOR, RARITY, TARGET);

        defineDamage(DAMAGE, UP_DAMAGE);

        this.isEthereal = true; //휘발성
        this.exhaust = true; //소멸
    }

    //사용시 효과
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new DamageAction(m, new DamageInfo(p, this.damage), AttackEffect.SLASH_VERTICAL));

    }

    //강화 효과. 특별한거 없으면 비워놓으셈
    @Override
    public void upgradeCard() {

    }
}
