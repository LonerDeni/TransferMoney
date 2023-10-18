package com.example.transfermoney;

public enum AllCurrency {
    DOLLAR ("Доллар",1.00),
    EURO ("Евро",0.95),
    RUBLE ("Рубль",97.00),
    IB ("Фунт",1.22),
    YUAN ("Юань",0.14);
    private final String name;
    private final double sum;

    AllCurrency(String name, double sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }
    public Double getSum(){
        return sum;
    }
    public static Integer getSumCurrency(String nameCur){
        for(AllCurrency allCurrency : AllCurrency.values()) {
           if(allCurrency.name.equals(nameCur)){
               return allCurrency.getSumCurrency(nameCur);
           }
        }
        return 1;
    }

    @Override
    public String toString() {
        return name;
    }
}
