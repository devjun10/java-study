public class AppleWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple, Apple compare) {
        return apple.getWeight() > compare.getWeight();
    }
}
