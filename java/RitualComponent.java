package com.megacrit.cardcrawl.cards.colorless;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.*;
import com.megacrit.cardcrawl.core.*;
import basemod.*;
import basemod.abstracts.*;

public class RitualComponent extends CustomCard
{
    public static final String ID = "Ritual Component";
    private static final CardStrings cardStrings;
    public static final String NAME;
    public static final String DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION;
    
    public RitualComponent() {
        super("Ritual Component", RitualComponent.NAME, "cards/replay/crow_ritual.png", 0, RitualComponent.DESCRIPTION, CardType.SKILL, CardColor.COLORLESS, CardRarity.UNCOMMON, CardTarget.SELF, 1);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }
    
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
		if (AbstractDungeon.player.drawPile.isEmpty()) {
            AbstractDungeon.actionManager.addToBottom(new EmptyDeckShuffleAction());
        }
		/*
		if(!this.upgraded) {
			//AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player, new DamageInfo(AbstractDungeon.player, this.magicNumber, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
			AbstractDungeon.actionManager.addToBottom(new RitualComponentAction(this.magicNumber));
		}
		*/
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, 1));
    }
    
    @Override
    public AbstractCard makeCopy() {
        return new RitualComponent();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            //this.upgradeMagicNumber(-1);
            //this.rawDescription = RitualComponent.UPGRADE_DESCRIPTION;
            //this.initializeDescription();
        }
    }
    
    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings("Ritual Component");
        NAME = RitualComponent.cardStrings.NAME;
        DESCRIPTION = RitualComponent.cardStrings.UPGRADE_DESCRIPTION;//DESCRIPTION;
        UPGRADE_DESCRIPTION = RitualComponent.cardStrings.UPGRADE_DESCRIPTION;
    }
}
