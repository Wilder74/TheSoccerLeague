import java.util.ArrayList;
import java.util.Random;

public class Main {
    static void main(String[] args){
        Team t1 = new Team( "Team 1");
        Team t2 = new Team( "Team 2");
        Team t3 = new Team("Team 3");
        Team t4 = new Team("team 4");

        Team[] teams = {t1, t2, t3, t4};
        ArrayList<Game> games_played = new ArrayList<Game>();
        
        // Schedule Start
        Random rand = new Random();
        int winter_weeks = 0;
        int gameID = 1;

        while (winter_weeks < 3) {

            //Generate random temperature
            double temp = rand.nextDouble(100);

            //Decide if game is played or not
            if (temp <= 32) {
                winter_weeks++;
                if (winter_weeks == 3){
                    System.out.println("Too cold to play :( Temp = " + temp);
                    System.out.println("Too cold to play :( Temp = " + temp);
                    System.out.println("Too cold to play :( Temp = " + temp);
                }
                continue;
            }
            else {
                // A Game is played

                //rest winter_weeks
                 winter_weeks = 0;

                //Select Team 4 first
                Team home1 = teams[3];

                //Randomly select another team[T1, T2, T3]
                int awayTeam = rand.nextInt(3);
                Team away1 = teams[awayTeam];

                //Select other 2 teams for game 2
                Team home2 = null;
                Team away2 = null;
                Game game1 = null;
                Game game2 = null;

                switch (awayTeam) {
                    case 0:
                        home2 = teams[1];
                        away2 = teams[2];
                        break;
                    case 1:
                        home2 = teams[0];
                        away2 = teams[2];
                        break;
                    case 2:
                        home2 = teams[0];
                        away2 = teams[1];
                        break;

                }

                //Randomly select scores
                if (temp <= 49) {
                    game1 = new Game(gameID, temp, away1, home1, rand.nextInt(6), rand.nextInt(6));
                    game2 = new Game(gameID + 1, temp, away2, home2, rand.nextInt(6), rand.nextInt(6));
                }
                else if (temp <= 66) {
                    game1 = new Game(gameID, temp, away1, home1, rand.nextInt(11), rand.nextInt(11));
                    game2 = new Game(gameID + 1, temp, away2, home2, rand.nextInt(11), rand.nextInt(11));
                }
                else if (temp <= 83) {
                    game1 = new Game(gameID, temp, away1, home1, rand.nextInt(16), rand.nextInt(16));
                    game2 = new Game(gameID + 1, temp, away2, home2, rand.nextInt(16), rand.nextInt(16));
                }
                else if (temp <= 100) {
                    game1 = new Game(gameID, temp, away1, home1, rand.nextInt(21), rand.nextInt(21));
                    game2 = new Game(gameID + 1, temp, away2, home2, rand.nextInt(21), rand.nextInt(21));
                }
                gameID += 2;

                // Game 1 Total Goals
                away1.setTotal_goals_scorred(away1.getTotal_goals_scorred() + game1.getAway_score());
                away1.setTotal_goals_allowed(away1.getTotal_goals_allowed() + game1.getHome_score());
                away1.setTotal_goals_scorred(home1.getTotal_goals_scorred() + game1.getHome_score());
                away1.setTotal_goals_allowed(home1.getTotal_goals_allowed() + game1.getAway_score());
                // Game 2 Total Goals
                away2.setTotal_goals_scorred(away2.getTotal_goals_scorred() + game2.getAway_score());
                away2.setTotal_goals_allowed(away2.getTotal_goals_allowed() + game2.getHome_score());
                away2.setTotal_goals_scorred(home2.getTotal_goals_scorred() + game2.getHome_score());
                away2.setTotal_goals_allowed(home2.getTotal_goals_allowed() + game2.getAway_score());

                // Games played added to Array
                games_played.add(game1);
                games_played.add(game2);

                // Record Wins, Looses and Ties of games
                record_WLT(game1);
                record_WLT(game2);
            }
        }
        System.out.println("Season is Over :( \n\n");
        System.out.println("*****RESULTS*****\n\n\n");

        for(Team team : teams){
            team.print_stats();
        }

        for (Game game : games_played){
            game.print_stats();
            System.out.println();
        }
    }
    // Game Score Results
    public static void record_WLT(Game game) {
        if (game.getAway_score() > game.getHome_score()) {
            game.getAway().setWin_total(game.getAway().getWin_total() +1);
            game.getHome().setLoss_total(game.getHome().getLoss_total() +1);
        }
        else if (game.getHome_score() > game.getAway_score()){
            game.getHome().setWin_total(game.getHome().getWin_total() +1);
            game.getAway().setLoss_total(game.getAway().getLoss_total() +1);
        }
        else {
            game.getAway().setTie_toal(game.getAway().getTie_toal() + 1);
            game.getHome().setTie_toal(game.getHome().getTie_toal() + 1);
        }
    }
}
