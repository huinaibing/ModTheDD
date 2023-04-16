package modthedd.cards.gold;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;
import modthedd.cards.BaseCard;
import modthedd.character.MyCharacter;
import modthedd.util.CardInfo;


import java.util.Iterator;

import static modthedd.BasicMod.makeID;

public class maoleizuiqiang extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "maoleizuiqiang", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            4, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.ALL_ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            MyCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );
    //This is theoretically optional, but you'll want it. The ID is how you refer to the card.
    //For example, to add a card to the starting deck, you need to use its ID.
    //With this, you can just use 'MyCard.ID'. Without it, you'd have to type out
    //'yourModID:MyCard' and make sure you don't make any mistakes, and you'd also have to update it
    //if you decided to change the card's ID.
    public static final String ID = makeID(cardInfo.baseId);

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    private static final int MG = 1;
    private static final int UPG_MG = 1;

    public maoleizuiqiang() {
        super(cardInfo);
        setMagic(MG, UPG_MG);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            if (!mon.isDeadOrEscaped()) {
                this.addToBot(new ApplyPowerAction(mon, p, new WeakPower(mon, magicNumber, false)));
                this.addToBot(new ApplyPowerAction(mon, p, new FrailPower(mon, magicNumber, false)));
                this.addToBot(new ApplyPowerAction(mon, p, new VulnerablePower(mon, magicNumber, false)));
                addToBot(new DamageAction(mon, new DamageInfo(p, 10, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
            }
        }

        addToBot(new GainBlockAction(p, 10));
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new IntangiblePower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new MantraPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new RegenPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new RitualPower(p, magicNumber, false)));
    }


    @Override
    public AbstractCard makeCopy() {
        return new maoleizuiqiang();
    }
}
