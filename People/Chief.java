package People;

import Classes.Messager;
import Classes.Passion;
import com.sun.source.tree.BreakTree;

import java.lang.reflect.Member;

public class Chief extends Person {

    private static int amount = 0;
    private int fund;


    public Chief(String name, Passion[] passions) {
        super(name, passions);
        fund = (int)(Math.random() * 501 + 500);
    }

    public Chief() {
        super("Начальник №" + ++amount);
    }

    public int getFund() {
        return fund;
    }


    public void setFund(int fund, Messager messager) {
        if(fund >= 0) {
            if(messager != null) {
                if(this.fund > fund) messager.addMessage(this + " растерял свои капиталы.\n");
                else messager.addMessage(this + " заработал еще больше денег.\n");
            }
            this.fund = fund;
        }
    }
}
