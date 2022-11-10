import java.util.ArrayList;
import java.util.Random;

    public class Round {
        private final ArrayList<Player> players;
        private final ArrayList<Spaces> spaces;
        private final int numPlayers;


        public Round(ArrayList<Player> players, ArrayList<Spaces> spaces) {
            this.players = players;
            this.spaces = spaces;
            this.numPlayers = players.size();
        }

//        //plays a single round
//        public void play() {
//            for(Player p : players) {
//                if (!p.bankrupt) {
//                    int spaces = rollDice();
//                    if(spaces == 0) {
//                        //p goes to jail and BREAK
//                    }
//                    //TODO: handle doubles or triples
//                    p.setCurrentSpace(p.getCurrentSpace() + spaces);
//                    int newSpace = p.getCurrentSpace();
//
//                    //TODO: identify type of space and act accordingly
//
////            if(!p.ownsProperty()) {
////
////            } else {
////
////            }
//                }
//
//            }
//        }
//
//        //TODO: test logic
//        public int rollDice() {
//            Random randGen = new Random();
//            int sum = 0;
//            int rolls = 0;
//            boolean doubles = false;
//
//            do {
//                int die1 = randGen.nextInt(6) + 1;
//                int die2 = randGen.nextInt(6) + 1;
//                sum += die1 + die2;
//                rolls++;
//                if(die1 == die2) {
//                    doubles = true;
//                }
//            } while (doubles && (rolls < 4));
//            if(rolls > 3) {
//                return 0;
//            }
//            return sum;
//        }

    }

