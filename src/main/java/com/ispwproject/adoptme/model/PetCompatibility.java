package com.ispwproject.adoptme.model;

public class PetCompatibility {
    private boolean maleDog;
    private boolean femaleDog;
    private boolean maleCat;
    private boolean femaleCat;
    private boolean children;
    private boolean elders;
    private boolean apartmentNoGarden;
    private boolean apartmentNoTerrace;
    private boolean sleepOutside;
    private boolean firstExperience;
    private int hoursAlone;

    public PetCompatibility() {
    }

    public PetCompatibility(boolean maleDog, boolean femaleDog, boolean maleCat, boolean femaleCat, boolean children, boolean elders, boolean apartmentNoGarden, boolean apartmentNoTerrace, boolean sleepOutside, boolean firstExperience, int hoursAlone) {
        setMaleDog(maleDog);
        setFemaleDog(femaleDog);
        setMaleCat(maleCat);
        setFemaleCat(femaleCat);
        setChildren(children);
        setElders(elders);
        setApartmentNoGarden(apartmentNoGarden);
        setApartmentNoTerrace(apartmentNoTerrace);
        setSleepOutside(sleepOutside);
        setFirstExperience(firstExperience);
        setHoursAlone(hoursAlone);
    }

    public boolean isMaleDog() {
        return maleDog;
    }

    public void setMaleDog(boolean maleDog) {
        this.maleDog = maleDog;
    }

    public boolean isFemaleDog() {
        return femaleDog;
    }

    public void setFemaleDog(boolean femaleDog) {
        this.femaleDog = femaleDog;
    }

    public boolean isMaleCat() {
        return maleCat;
    }

    public void setMaleCat(boolean maleCat) {
        this.maleCat = maleCat;
    }

    public boolean isFemaleCat() {
        return femaleCat;
    }

    public void setFemaleCat(boolean femaleCat) {
        this.femaleCat = femaleCat;
    }

    public boolean isChildren() {
        return children;
    }

    public void setChildren(boolean children) {
        this.children = children;
    }

    public boolean isElders() {
        return elders;
    }

    public void setElders(boolean elders) {
        this.elders = elders;
    }

    public boolean isApartmentNoGarden() {
        return apartmentNoGarden;
    }

    public void setApartmentNoGarden(boolean apartmentNoGarden) {
        this.apartmentNoGarden = apartmentNoGarden;
    }

    public boolean isApartmentNoTerrace() {
        return apartmentNoTerrace;
    }

    public void setApartmentNoTerrace(boolean apartmentNoTerrace) {
        this.apartmentNoTerrace = apartmentNoTerrace;
    }

    public boolean isSleepOutside() {
        return sleepOutside;
    }

    public void setSleepOutside(boolean sleepOutside) {
        this.sleepOutside = sleepOutside;
    }

    public boolean isFirstExperience() {
        return firstExperience;
    }

    public void setFirstExperience(boolean firstExperience) {
        this.firstExperience = firstExperience;
    }

    public int getHoursAlone() {
        return hoursAlone;
    }

    public void setHoursAlone(int hoursAlone) {
        this.hoursAlone = hoursAlone;
    }

}