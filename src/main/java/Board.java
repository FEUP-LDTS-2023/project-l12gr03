public class Board  {
    private Player p1;
    private Player p2;

    Integer dummy=0;
    public Board(Player player1, Player player2){
        this.p1 = player1;
        this.p2 = player2;
    }

    public void UpperEntry(){dummy++;}
    public void LowerEntry(){dummy--;}
    public void LeftEntry(){dummy+=10;}
    public void RightEntry(){dummy-=10;}

}
