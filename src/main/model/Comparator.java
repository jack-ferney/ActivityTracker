package model;

public interface Comparator {

    // EFFECTS: returns -1 if a1 is ranked less than a2;
    //          returns 0 if a1 is ranked equal to a2;
    //          returns +1 if a1 is ranked greater than a2;
    int compare(Activity a1, Activity a2);
}
