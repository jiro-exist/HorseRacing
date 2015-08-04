package com.manio.horserace;

public class JockeyRunnable implements Runnable {
    
	private Jockey jockey;
	private Race race;
	private int distance;

	public JockeyRunnable(Jockey jockey, Race race) {

		this.jockey = jockey;
		this.race = race;

	}

    public void run() {

        RaceService raceService = new RaceService();

        //get distance for the gate
        updateDistance();

        //run to the gates
        makeHorseRun(raceService);

        //reset data for the actual race
	    jockey.setCurrDist(0);
	    jockey.setRaceIteration(0);
	    jockey.setJockeyStatus(JockeyStatus.GATE);

	    //get distance for the finish line
        updateDistance();

        //wait for the signal to go
        synchronized(race.getJockeyList()) {
        	waitMethod();
        }

        //run to the finish line
        makeHorseRun(raceService);

    }

    private void makeHorseRun(RaceService raceService) {

        while (jockey.getCurrDist() < distance) {

        	int speed = getSpeed();

	        int ran = raceService.horseRun(jockey, race, speed, distance);

			switch (jockey.getJockeyStatus()) {

				case STABLE :	synchronized (this) {
				        			printRunStatus(ran);
								}
								break;

    			case GATE 	:	synchronized (this) {
		        					printRunStatus(speed, ran);
								}
				
	            				synchronized(this) {
						           finishCheck();
					           	}
					           	break;

           	}

            try {
                Thread.sleep(Constant.RACE_INTERVAL);
            }
            catch (InterruptedException e) {
                System.out.println("TIMER " + Constant.INTERRUPT_ERROR);
            }

	    }
    }

    private void updateDistance() {

		switch (jockey.getJockeyStatus()) {

			case STABLE :	this.distance = Constant.STABLE_GATE_DISTANCE;
							break;

			case GATE 	:	this.distance = race.getDistance();
							break;

			default 	:	this.distance = race.getDistance();
							break;

		}

    }

    private void printRunStatus(int ran) {

        System.out.println(jockey.getName() + " ran " + ran + " meters! ");

        if (jockey.getCurrDist() >= distance) {
    		System.out.println(jockey.getName() + Constant.GATE_WAIT);
    	}
    	else {
        	System.out.println("He is now at " 
        		+ jockey.getCurrDist() + " meters in " + jockey.getRaceIteration() + " secs!" );
    	}
	
	}
	

    private void printRunStatus(int speed, int ran) {

	        if (speed == Constant.BOOST_SPEED) {
        		System.out.print(jockey.getName() + " suddenly boosted! ");
	        }

	        System.out.println(jockey.getName() + " ran " + ran + " meters! He is now at " 
	        		+ jockey.getCurrDist() + " meters in " + jockey.getRaceIteration() + " secs! " + System.currentTimeMillis());
	       

    }
	
    private int getSpeed() {

		switch (jockey.getJockeyStatus()) {

			case STABLE :	return Constant.DEFAULT_SPEED;

			case GATE 	:	if ( (race.getRacers() == jockey.getPosi()) && (jockey.getRaceIteration() > 1) ) {
                                return Constant.BOOST_SPEED;
                            }
                            else {
                                return Constant.DEFAULT_SPEED;
                            }

			default 	:	return Constant.BOOST_SPEED;

		}

    }

    private void finishCheck() {
		if (jockey.getCurrDist() >= distance) {
			System.out.println(jockey.getName() + " crossed the finish line!");
		}

        if (jockey.getCurrDist() >= distance ) {
        	race.addFinalRanking(jockey);

        	if (!race.getIsFinished()) {
	        	race.setIsFinished(true);
	        	race.setWinner(jockey);
        	}
        }
	}

	private void waitMethod() {
        try {
        	if (!race.getRaceStart()) {
                race.getJockeyList().wait(); 
        	}
        }
        catch (InterruptedException e) {
            System.out.println("TIMER " + Constant.INTERRUPT_ERROR);
        }
	}

}