public class Board  {
    final private Player p1;
    final private Player p2;

    Integer dummy=0;
    public Board(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public void UpperEntry(){dummy++;}
    public void LowerEntry(){dummy--;}
    public void LeftEntry(){dummy+=10;}
    public void RightEntry(){dummy-=10;}
}
