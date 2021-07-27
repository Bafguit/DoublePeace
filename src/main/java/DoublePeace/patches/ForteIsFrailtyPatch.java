package DoublePeace.patches;

public class ForteIsFrailtyPatch {
    public ForteIsFrailtyPatch() {
    }
/*
    @SpirePatch(
            clz = ApplyPowerAction.class,
            method = "update"
    )

    public static class BuffToDebuffAction {
        public BuffToDebuffAction() {
        }

        public static ExprEditor Instrument() {
            return new ExprEditor() {
                public void edit(MethodCall m) throws CannotCompileException {
                    if(m.getLineNumber() == 132) {
                        m.replace("$_ = $proceed($$); ");
                    }
                    if (m.getClassName().equals("com.megacrit.cardcrawl.actions.common.ApplyPowerAction") && m.getMethodName().equals("setValues")) {
                        m.replace("$_ = $proceed($$); if(com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hasPower(theChaser.powers.ForteIsFrailtyPower.POWER_ID) && powerToApply.type == com.megacrit.cardcrawl.powers.AbstractPower.PowerType.BUFF) { powerToApply.type = com.megacrit.cardcrawl.powers.AbstractPower.PowerType.DEBUFF; }");
                    }
                }
            };
        }
    }*/
}