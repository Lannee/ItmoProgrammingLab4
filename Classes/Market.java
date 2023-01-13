package Classes;

import java.util.Objects;

public class Market {

    private static Market instance;
    private int goodsQuantity;
    private int goodsPrice;
    private int lifeQuality;
    private int FactoriesProductivity;
    private int demand;
    private int workplaces;
    private int restaurantsQuantity;

    private Market(int goodsQuantity, int goodsPrice, int lifeQuality, int restaurantsQuantity) {
        if(goodsQuantity >= 0) this.goodsQuantity = goodsQuantity;
        if(goodsPrice >= 0) this.goodsPrice = goodsPrice;
        if(lifeQuality >= 0 && lifeQuality <= 100) this.lifeQuality = lifeQuality;
        FactoriesProductivity = (int)(Math.random() * 1000);
        workplaces = goodsQuantity / FactoriesProductivity;
        demand = goodsQuantity / 25000 + goodsPrice * 4 + lifeQuality;
        this.restaurantsQuantity = restaurantsQuantity;
    }

    public static Market getInstance() {
        if(instance == null) return instance = new Market(100000, 10, 40, 50);
        else return instance;
    }

    public void setGoodsPrice(int goodsPrice, Messager messager) {
        if(messager != null) {
            messager.addMessage("Товары ");
            if(this.goodsPrice > goodsPrice) messager.addMessage("подешевели.\nКачество жизни населения возрасло.\n");
            else messager.addMessage("подарожали.\nКачество жизни населения понизилось.\n");
        }
        lifeQuality = Math.min(Math.abs(this.goodsPrice - goodsPrice) / 4 + lifeQuality, 100) + workplaces / 15;
        this.goodsPrice = goodsPrice;
        demand = goodsQuantity * 4 + goodsPrice * 4 + lifeQuality;
    }

    public int getFactoriesProductivity() {
        return FactoriesProductivity;
    }

    public void setFactoriesProductivity(int factoriesProductivity) {
        if(factoriesProductivity > 0) this.FactoriesProductivity = factoriesProductivity;
    }

    public int getGoodsQuantity() {return goodsQuantity;}

    public void setGoodsQuantity(int goodsQuantity, Messager messager) {
        if(messager != null) {
            messager.addMessage("Товаров ");
            if(this.goodsQuantity > goodsQuantity) messager.addMessage("стало меньше.\n");
            else messager.addMessage("стало больше.\n");
        }
        this.goodsQuantity = goodsQuantity;
        demand = goodsQuantity * 4 + goodsPrice * 4 + lifeQuality;
    }

    public int getDemand() {
        return demand;
    }

    public int getLifeQuality() {
        return lifeQuality;
    }

    public void setRestaurantsQuantity(int restaurantsQuantity, Messager messager) {
        if(restaurantsQuantity > 0) {
            if(messager != null) {
                messager.addMessage("Колличесто столовых и ресторанов ");
                if(this.restaurantsQuantity > restaurantsQuantity) messager.addMessage("уменьшилось.\n");
                else messager.addMessage("увеличилось.\n");
            }
            this.restaurantsQuantity = restaurantsQuantity;
        }
    }

    public int getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(int workplaces, Messager messager) {
        if(messager != null) {
            messager.addMessage("Рабочих мест ");
            if(this.workplaces > workplaces) messager.addMessage("стало меньше.\n");
            else messager.addMessage("стало больше.\n");
        }
        lifeQuality += Math.abs((this.workplaces - workplaces) / 7);
        this.workplaces = workplaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Market market = (Market) o;
        return goodsQuantity == market.goodsQuantity &&
                goodsPrice == market.goodsPrice &&
                lifeQuality == market.lifeQuality &&
                FactoriesProductivity == market.FactoriesProductivity &&
                demand == market.demand &&
                workplaces == market.workplaces &&
                restaurantsQuantity == market.restaurantsQuantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Integer.hashCode(goodsQuantity);
        result = prime * result + Integer.hashCode(goodsPrice);
        result = prime * result + Integer.hashCode(lifeQuality);
        result = prime * result + Integer.hashCode(FactoriesProductivity);
        result = prime * result + Integer.hashCode(demand);
        result = prime * result + Integer.hashCode(workplaces);
        result = prime * result + Integer.hashCode(restaurantsQuantity);
        return result;
    }

    @Override
    public String toString() {
        return "Внутренний рынок";
    }
}
