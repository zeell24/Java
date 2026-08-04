package clg.p3.p3_2;

public class Card {
    private String rank;
    private String suit;

    Card(String rank , String suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    @Override 
    public String toString()
    {
        return rank + " of " + suit ;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
    
        if(obj == null || !(obj instanceof Card)) return false;

        Card p = (Card)obj;

        return this.rank == p.rank && this.suit == p.suit;
    }

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(rank , suit);
    }
}
