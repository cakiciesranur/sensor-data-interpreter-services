package com.eny.sensordataconsumer.producer;

public enum DeviceType {
    DEVICE1("DEVICE1"),
    DEVICE2("DEVICE2"),
    DEVICE3("DEVICE3"),
    DEVICE4("DEVICE4"),
    DEVICE5("DEVICE5");

    private final String name;

    DeviceType(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }

    public static String getEnumByString(String code){
        for(DeviceType e : DeviceType.values()){
            if(e.name.equals(code)) return e.name();
        }
        return null;
    }
}