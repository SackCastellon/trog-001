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
    CHILDREN {
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

        @Override
        public int calculatePoints(int daysRented) {
            // add bonus for a two-day new release rental
            if (daysRented > 1) {
                return super.calculatePoints(daysRented) + 1;
            } else {
                return super.calculatePoints(daysRented);
            }
        }
    };

    public abstract double calculatePrice(int daysRented);

    public int calculatePoints(int daysRented) {
        return 1;
    }
}
