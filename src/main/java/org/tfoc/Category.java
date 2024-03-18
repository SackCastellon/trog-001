package org.tfoc;

public enum Category {
    REGULAR {
        @Override
        public double calculatePrice(int daysRented) {
            return 2 + Math.max(daysRented - 2, 0) * 1.5;
        }
    },
    CHILDREN {
        @Override
        public double calculatePrice(int daysRented) {
            return 1.5 + Math.max(daysRented - 3, 0) * 1.5;
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
            var basePoints = super.calculatePoints(daysRented);
            return daysRented > 1 ? basePoints + 1 : basePoints;
        }
    };

    public abstract double calculatePrice(int daysRented);

    public int calculatePoints(int daysRented) {
        return 1;
    }
}
