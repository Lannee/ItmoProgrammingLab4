import People.*;
import Classes.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Chief scoperfild = new Chief("Скоперфильд");
        Factory fac1 = new Factory(scoperfild, "Фабрика№1");
        Worker[] workers1 = {new Worker(), new Worker()};
        WorkersGroup wg1 = new WorkersGroup(fac1,"Скуперфильдовские рабочие", workers1);
        wg1.addItem(new Weightlessness());
        wg1.banish(scoperfild);

        Astronaut astronaut = new Astronaut();
        astronaut.getStorage().addItems(new Weightlessness(), 3);
        Factory fac2 = new Factory(new Chief());
        Worker[] workers2 = {new Worker(), new Worker(), new Worker()};
        WorkersGroup wg2 = new WorkersGroup(fac2, workers2);
        wg2.goTo(astronaut, true);
        astronaut.give(new Weightlessness(), wg2, true);
        wg2.goTo(wg2.getFactory(), true);
        new Weightlessness().activate(wg2, true);

        Factory fac3 = new Factory(new Chief());
        Worker[] workers3 = {new Worker(), new Worker(), new Worker()};
        WorkersGroup wg3 = new WorkersGroup(fac3, workers3);
        Factory fac4 = new Factory(new Chief());
        Worker[] workers4 = {new Worker(), new Worker(), new Worker()};
        WorkersGroup wg4 = new WorkersGroup(fac4, workers4);
        ArrayList<WorkersGroup> groups = new ArrayList<WorkersGroup>(List.of(wg3, wg4));
        WorkersGroup any = WorkersGroup.getAny(groups);
        any.setCourage(90);
        any.banish(wg3.getFactory().getChief());

        Policeman pm1 = new Policeman();
        Policeman pm2 = new Policeman();
        pm1.getStorage().addItem(new Gun("ружьё"));
        pm1.getStorage().addItem(new Gun("пистолет"));
        pm2.getStorage().addItem(new Clothes("полицейские мундиры"));
        pm2.getStorage().addItem(new Clothes("каски"));
        PolicemanGroup pg = new PolicemanGroup("Группа полицейских", new Policeman[]{pm1 ,pm2});
        pg.setFear(70);
        if(pg.getFear() >= 50) {
            pg.throwAway(ItemType.GUN, true);
            Terrain tr = new Terrain();
            pg.bury(ItemType.CLOTHES, tr, true);
            new Clothes("Одежда рабочих").cloth(pg, true);
            pg.say("Это гораздо приятнее, чем летать сломя голову по воздуху в состоянии невесомости, получая ожоги, ранения и увечья");
        }
        else {
            pg.oppose(wg3);
        }
    }
}