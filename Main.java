import Exeptions.StorageDoesNotContainsItemException;
import Items.*;
import Locations.Facility;
import Locations.Factory;
import Locations.Location;
import People.*;
import Classes.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Messager messager = new Messager();
        Person Kozlik = new Person("Козлик");
        Location coast = new Location("берег");
        Kozlik.getStorage().addItem(new Clothes("шляпа"));
        Kozlik.tossUp(Kozlik.getStorage().getItemByClass(Clothes.class), messager);
        Kozlik.setGladness(75);
        if(Kozlik.getGladness() > 70) {
            Kozlik.jump(coast, messager);
        }
        Person znaika = new Person("Знайка");
        Person dkPilyulkin = new Person("доктор Пилюлькин");
        Person vintik = new Person("Винтик");
        Person shpuntik = new Person("Шпунтик");
        Person ponchik = new Person("Пончик");
        Steamer steamer = new Steamer(new Premises("корабля", new Person[] {znaika, dkPilyulkin, vintik, shpuntik, ponchik}));
        Location water = new Location("поверхность воды");
        Location wharf = new Location("пристань");
        steamer.goToThrought(water, wharf, messager);
        Person Neznaika = new Person("Незнайка");
        Person.Hand nh = Neznaika.new Hand();
        Person.Hand kh = Kozlik.new Hand();
        nh.take(kh, messager);
        Group<Person> KozlAndNezn = new Group<>("Козлик и Незнайка", new Person[] {Neznaika, Kozlik});
        KozlAndNezn.goTo(wharf, messager);
        steamer.getPremises().exit(messager, znaika, dkPilyulkin, vintik, shpuntik, ponchik);
        Person.Heart NeznHeart = Neznaika.new Heart();
        Neznaika.setExcitement(80);
        NeznHeart.quake(messager);
        Neznaika.canMove(messager);
        Group<Person> znaikaAndOthers = new Group<>("Группа Знайки и остальных коротышек", new Person[] {znaika, dkPilyulkin, vintik, shpuntik, ponchik});
        znaikaAndOthers.goTo(Neznaika, messager);

// Third lad code
        Chief scoperfild = new Chief("Скоперфильд", new Passion[] {Passion.PASTA_BUSINESS});

        scoperfild.setFund(10, messager);

        Worker[] workers1 = {new Worker(), new Worker()};
        Factory fac1 = new Factory(scoperfild, "Фабрика№1", workers1);
        WorkersGroup wg1 = new WorkersGroup(fac1,"Скуперфильдовские рабочие", workers1);
        wg1.addItem(new Weightlessness(), messager);
        wg1.banish(scoperfild, messager);

        Astronaut astronaut = new Astronaut();
        astronaut.getStorage().addItems(new Weightlessness(), 3);
        Worker[] workers2 = {new Worker(), new Worker(), new Worker()};
        Factory fac2 = new Factory(new Chief(), workers2);
        WorkersGroup wg2 = new WorkersGroup(fac2, workers2);
        wg2.goTo(astronaut, messager);
        try {
            astronaut.give(new Weightlessness(), wg2, messager);
        } catch (StorageDoesNotContainsItemException sdncie) {
            messager.addMessage(astronaut + " не может дать предмет, которого у него нет.");
        }
        wg2.goTo(wg2.getFactory(), messager);
        new Weightlessness().activate(wg2, messager);

        Worker[] workers3 = {new Worker(), new Worker(), new Worker()};
        Factory fac3 = new Factory(new Chief(), workers3);

        WorkersGroup wg3 = new WorkersGroup(fac3, workers3);
        Worker[] workers4 = {new Worker(), new Worker(), new Worker()};
        Factory fac4 = new Factory(new Chief(), workers4);
        WorkersGroup wg4 = new WorkersGroup(fac4, workers4);
        ArrayList<WorkersGroup> groups = new ArrayList<WorkersGroup>(List.of(wg3, wg4));
        WorkersGroup any = WorkersGroup.getAny(groups, messager);
        if(any != null) {
            any.setCourage(90, messager);
            any.banish(any.getFactory().getChief(), messager);
        }

        Policeman pm1 = new Policeman();
        Policeman pm2 = new Policeman();
        pm1.getStorage().addItem(new Gun("ружьё"));
        pm1.getStorage().addItem(new Gun("пистолет"));
        pm2.getStorage().addItem(new Clothes("полицейские мундиры"));
        pm2.getStorage().addItem(new Clothes("каски"));
        PolicemanGroup pg = new PolicemanGroup("Группа полицейских", new Policeman[]{pm1 ,pm2});
        pg.setFear(70, messager);
        if(pg.getFear() >= 50) {
            try {
                pg.throwAway(Gun.class, messager);
            } catch (StorageDoesNotContainsItemException sdncie) {
                messager.addMessage(pg + " не может выбросить предметы, которых у нее нет.");
            }
            Terrain tr = new Terrain();
            try {
                pg.bury(Clothes.class, tr, messager);
            } catch (StorageDoesNotContainsItemException sdncie) {
                messager.addMessage(pg + " не может закопать предметы, которых у нее нет.");
            }
            new Clothes("Одежда рабочих").dress(pg, messager);
            pg.say("Это гораздо приятнее, чем летать сломя голову по воздуху в состоянии невесомости, получая ожоги, ранения и увечья", messager);
        }
        else {
            pg.oppose(wg3, messager);
        }
// Third lab code end

        Worker[] workers5 = new Worker[] {new Worker(), new Worker(), new Worker()};
        WorkersGroup wg5 = new WorkersGroup(new Factory(null, workers5), "Рабочие",workers5);
        if(wg5.getFactory().getChief() == null) {
            messager.addMessage("Так как у рабочих больше нет начальника. ");
            wg5.setSalary(200, messager);
        }

        Market market = Market.getInstance();
        market.setGoodsPrice(5, messager);
        if(market.getDemand() > 50) {
            messager.addMessage("Из-за высокого спроса на товары. ");
            Factory.setProductivity(market, market.getFactoriesProductivity() + 500, messager);
            market.setWorkplaces(market.getWorkplaces() + 100, messager);
        }
        if(market.getLifeQuality() > 60) {
            messager.addMessage("Из-за высокого уровня жизни. ");
            ServantGroup sg = new ServantGroup(new Servant[]{new Servant(ServantSpecialization.MAID), new Servant(ServantSpecialization.HANDMAID), new Servant(ServantSpecialization.LAUNDRESS), new Servant(ServantSpecialization.PORTER), new Servant(ServantSpecialization.POLISHER), new Servant(ServantSpecialization.COOK)}, new Chief());
            sg.getAwayFromChief(messager);
            Facility restaurant = new Facility("Рестораны") {
                @Override
                public void employ(Messager messager, Person... employees) {
                    if(employees.length != 0) {
                        if (messager != null) {
                            messager.addMessage(this + " нанимают");
                        }
                        for(Person employee : employees) {
                            getEmployees().add(employee);
                            if(employee instanceof Servant && messager != null) {
                                messager.addMessage(" " + ((Servant) employee).getSpecialization().getTranslation());
                            } else if(messager != null) {
                                messager.addMessage(" " + employee);
                            }
                        }
                        if(messager != null) messager.addMessage(".\n");
                    }
                }
            };

            restaurant.employ(messager, new Servant(ServantSpecialization.COOK));
            market.setRestaurantsQuantity(60, messager);
        }
        scoperfild.addStatus(Status.CONFUSION, messager);
        Group<Person> scoperfildsFamiliars = new Group<>("Знакомые Скоперфильда", new Person[] {new Person(), new Person()});
        scoperfildsFamiliars.getParticipants().get(0).getStorage().addItem(new Food());
        scoperfild.goTo(scoperfildsFamiliars, messager);
        scoperfildsFamiliars.feed(scoperfild, messager);
        if(scoperfildsFamiliars.getGladness() <= 20) {
            messager.addMessage("Так как " + scoperfildsFamiliars + " не получали от этого особого удовольствия. ");
            Worker[] workers6 = new Worker[] {new Worker(), new Worker()};
            Factory pastaFactory = new Factory(scoperfild, "Макаронная фабрика", workers6);
            pastaFactory.employ(messager, scoperfild);
            WorkersGroup wg6 = new WorkersGroup(pastaFactory, "Рабочие макаронной фабрики", workers6);
            if(scoperfild.hasPassion(Passion.PASTA_BUSINESS, messager)) {
                messager.addMessage(", поэтому ");
                wg6.say("Мы надеемся, что " + scoperfild + " будет работать исправно и добрососвестно", messager);
            }
        }

        System.out.print(messager.getMessages());
    }
}