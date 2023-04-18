package modthedd.cards.gold;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import modthedd.cards.BaseCard;
import modthedd.character.MyCharacter;
import modthedd.powers.liuhanPower;
import modthedd.util.CardInfo;


import java.util.Iterator;

import static modthedd.BasicMod.makeID;

public class liuhan extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "liuhan", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            1, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.POWER, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
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

    private static final int UPG_COST = 0;
    private AbstractCard[] liuhan_s = new AbstractCard[3];


    public liuhan() {
        super(cardInfo);
        this.liuhan_s[0] = new maoleiliuhan();
        this.liuhan_s[1] = new tafeiliuhan();
        this.liuhan_s[2] = new dongxueliangliuhan();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
            this.addToBot(new MakeTempCardInDrawPileAction(this.liuhan_s[i].makeStatEquivalentCopy(), 1, true, true));
        }
        this.addToBot(new ApplyPowerAction(p, p, new liuhanPower(p, 1), 1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPG_COST);
            for (int i = 0; i < 3; i++) {
                liuhan_s[i].upgrade();
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new liuhan();
    }
}
