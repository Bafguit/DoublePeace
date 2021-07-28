package DoublePeace.powers;

import DoublePeace.abstracts.DPPower;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.LosePercentHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static DoublePeace.DPMod.makeID;

public class OppaiPower extends DPPower {

    //TODO 수정할 부분 =======================
    public static final String POWER_ID = makeID("Oppai"); //파워 아이디. 띄어쓰기는 안하는게 좋음
    private static final PowerType TYPE = PowerType.DEBUFF; //파워 타입. BUFF, DEBUFF
    private static final boolean TURN = false; //턴마다 감소되는 파워는 true, 아니면 false
    //TODO =================================

    //중첩 안되는 파워는 이거 사용
    public OppaiPower(AbstractCreature owner, AbstractCreature source) {
        super(POWER_ID, TYPE, TURN, owner, source);
    }

    /*중첩 되는 파워는 이거 사용
    public OppaiPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN, owner, source, amount);
    }
     */ //둘 중에 하나만 선택하셈

    //파워 정보 업데이트. 숫자가 바뀌거나 할때 사용함.
    @Override
    public void updateDesc() {
        description = DESCRIPTIONS[0];
    }

    //TODO ========================= 여기부턴 원하는 효과 추가 =========================
    //TODO ==== AbstractPower 에 들어가서 밑으로 내려보면 사용 가능한 함수가 나옴. 원하는거 골라서 쓰면 됨.

    //공격받을 때마다 최대 체력의 5%만큼 체력을 잃는다.
    @Override
    public int onAttacked(DamageInfo info, int damageAmount) { //공격받을 때

        addToTop(new LoseHPAction(this.owner, this.owner, MathUtils.floor(this.owner.maxHealth / 20))); //최대 체력의 5%를 잃는다
        // 슬레이 더 스파이어에서 소수점 이하는 대부분 버린다.

        // Q. addToBot 이 아닌 addToTop 을 쓴 이유는 무엇인가요?
        // 대미지를 받을 때마다 즉각적으로 발동해야 하기 때문에 액션 리스트의 맨 앞으로 보내서 가장 먼저 실행할 필요가 있다.
        // 이 파워처럼 addToTop 이 필요한 상황이 아니고서는 되도록 addToBot 을 사용하자.

        // Q. atDamageReceive, atDamageFinalReceive, onAttackedToChangeDamage, onAttacked 의 차이는 무엇인가요?
        // 함수의 실행 상황과 순서에 차이가 있다. atDamageReceive, atDamageFinalReceive 는 피해를 계산하는 단계에서 적용된다.
        // 이 둘은 적을 조준할 때 피해를 계산하기 때문에 취약 같은 파워에서 쓰이는 것을 알 수 있다.
        // onAttackedToChangeDamage, onAttacked 는 피해를 받기 전에 적용된다. 교살 같은 파워에서 쓰인다.
        // 함수가 실행되는 순서는 atDamageReceive → atDamageFinalReceive / onAttackedToChangeDamage → onAttacked 순이다.
        // 일반적으로 atDamageFinalReceive 와 onAttackedToChangeDamage 는 잘 쓰이지 않는다. 상황에 따라 사용하자.

        return damageAmount;
    }

}
