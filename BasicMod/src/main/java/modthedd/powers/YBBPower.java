package modthedd.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;


import static modthedd.BasicMod.makeID;

public class YBBPower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("YBBPower");
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public YBBPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_METALLICIZE", 0.05F);
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        this.flash();
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new MetallicizePower(this.owner, this.amount), this.amount));
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new PlatedArmorPower(this.owner, this.amount), this.amount));
        this.addToBot(new ApplyPowerAction(this.owner, this.owner, new VulnerablePower(this.owner, this.amount, false), this.amount));
    }

    @Override
    public AbstractPower makeCopy() {
        return new YBBPower(owner, amount);
    }
}
