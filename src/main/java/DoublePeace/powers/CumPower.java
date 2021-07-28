package DoublePeace.powers;

import DoublePeace.abstracts.DPPower;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static DoublePeace.DPMod.*;

public class CumPower extends DPPower {

    //TODO 수정할 부분 =======================
    public static final String POWER_ID = makeID("Cum"); //파워 아이디. 띄어쓰기는 안하는게 좋음
    private static final PowerType TYPE = PowerType.BUFF; //파워 타입. BUFF, DEBUFF
    private static final boolean TURN = true; //턴마다 감소되는 파워는 true, 아니면 false
    //TODO =================================

    /*중첩 안되는 파워는 이거 사용
    public AhegaoPower(AbstractCreature owner, AbstractCreature source) {
        super(POWER_ID, TYPE, TURN, owner, source);
    }
     */

    //중첩 되는 파워는 이거 사용
    public CumPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(POWER_ID, TYPE, TURN, owner, source, amount);
    }  //둘 중에 하나만 선택하셈

    //파워 정보 업데이트. 숫자가 바뀌거나 할때 사용함.
    @Override
    public void updateDesc() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1]; //결과 예시) 턴 종료 시 방어도를 this.amount 얻습니다.
    }

    //TODO ========================= 여기부턴 원하는 효과 추가 =========================
    //TODO ==== AbstractPower 에 들어가서 밑으로 내려보면 사용 가능한 함수가 나옴. 원하는거 골라서 쓰면 됨.

    //플레이어의 턴 종료 시 방어도를 amount 만큼 얻고 중첩이 1 감소한다.
    @Override
    public void atEndOfTurn(boolean isPlayer) { //턴 종료시
        if(isPlayer) { //플레이어의 턴이라면
            addToBot(new GainBlockAction(this.owner, this.amount)); //방어도 획득
            addToBot(new ReducePowerAction(this.owner, this.owner, this, 1)); //중첩 1 감소
        }
    }

}
