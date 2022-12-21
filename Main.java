import People.*;
import Classes.*;

public class Main {
    public static void main(String[] args) {
        Chief scoperfild = new Chief("Скоперфильд");
        Factory fac1 = new Factory(scoperfild, "Фабрика№1");
        Worker[] workers1 = {new Worker(), new Worker()};
        WorkersGroup wg1 = new WorkersGroup(fac1,"Скуперфильдовские рабочие", workers1);
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
        wg3.banish(wg3.getFactory().getChief());

        Policeman pm = new Policeman();
        pm.getStorage().addItems(new Gun(), 3);
        PolicemanGroup pg = new PolicemanGroup("Группа полицейских", new Policeman[]{pm ,new Policeman()});
        pg.addStatus(Status.FRIGHT);
        pg.throwAway(new Gun(), true);
        Terrain tr = new Terrain();
        pg.bury(new Closes(), tr, true);
        new Closes("Одежда рабочих").cloth(pg, true);
        pg.say("Это гораздо приятнее, чем летать сломя голову по воздуху в состоянии невесомости, получая ожоги, ранения и увечья");
    }
}