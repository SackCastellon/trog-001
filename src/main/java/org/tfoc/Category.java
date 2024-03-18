package org.tfoc;

public enum Category {
    REGULAR {
        @Override
        public double calculatePrice(int daysRented) {
            if (daysRented > 2) {
                return 2 + (daysRented - 2) * 1.5;
            } else {
                return 2;
            }
        }
    },
    CHILDRENS {
        @Override
        public double calculatePrice(int daysRented) {
            if (daysRented > 3) {
                return 1.5 + (daysRented - 3) * 1.5;
            } else {
                return 1.5;
            }
        }
    },
    NEW_RELEASE {
        @Override
        public double calculatePrice(int daysRented) {
            return daysRented * 3.0;
        }
    };

    public abstract double calculatePrice(int daysRented);

}
