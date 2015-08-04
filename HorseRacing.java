package com.manio.horserace;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class HorseRacing {

    public static void main (String args[]) {

        try (Scanner sc = new Scanner(System.in)) {

            Race race = new Race();
            RaceService raceService = new RaceService();

            String hold = "";

            System.out.print("Enter the distance of the race track:");

            while (!Utilities.isInt(hold)) {
                hold = sc.nextLine();
                if (!Utilities.isInt(hold)) {
                    System.out.print("Please enter an integer:");
                }
            }

            race.setDistance(Integer.parseInt(hold));

            System.out.print("Enter the distance of the number of racers:");

            hold = "";

            while (!Utilities.isInt(hold)) {
                hold = sc.nextLine();
                if (!Utilities.isInt(hold)) {
                    System.out.print("Please enter an integer:");
                }
            }

            race.setRacers(Integer.parseInt(hold));

            List<Jockey> jockeyList = raceService.initRacers(race.getRacers());

            race.setJockeyList(jockeyList);

            // raceService.startRace(jockeyList, race); // to the finish line
            
            Thread raceThread = new Thread(new RaceRunnable(race), "Main Race");
            raceThread.start();

            try {
                raceThread.join();
            }
            catch (InterruptedException e) {
                System.out.println("Join " + Constant.INTERRUPT_ERROR);
            }

            Jockey winner = race.getWinner();

            System.out.println();
            System.out.println("Race finished! ");
            raceService.printLastRank(race);
            System.out.println();
            System.out.println("Winner is " + winner.getName() + "!");
            System.out.println(winner.getName() + ": " + raceService.getWarCry());


        }

    }

}