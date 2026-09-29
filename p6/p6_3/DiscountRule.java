package clg.p6.p6_3;

@FunctionalInterface
public interface DiscountRule
{
    double apply(double price);
}