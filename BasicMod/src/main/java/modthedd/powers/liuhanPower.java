package modthedd.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;


import java.util.Objects;

import static modthedd.BasicMod.makeID;

public class liuhanPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("liuhanPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private int flag_tafei = 0;
    private int flag_maolei = 0;
    private int flag_dongxueliang = 0;

    public liuhanPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_METALLICIZE", 0.05F);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (Objects.equals(card.cardID, "tafeiliuhan")) {
            flag_tafei = 1;
            this.flash(); // 这个是干啥的？
        }
        if (Objects.equals(card.cardID, "dongxuelianliuhan")) {
            flag_dongxueliang = 1;
            this.flash();
        }
        if (Objects.equals(card.cardID, "maoleiliuhan")) {
            flag_maolei = 1;
            this.flash();
        }
        if (flag_tafei == 1 && flag_maolei == 1 && flag_dongxueliang == 1){
            // 添加目标卡到手中，移除这个能力
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new liuhanPower(owner, amount);
    }
}
