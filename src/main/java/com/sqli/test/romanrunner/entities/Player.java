package com.sqli.test.romanrunner.entities;

import com.sqli.test.romanrunner.Circenses;

public class Player extends Component{

    private Circenses       circenses   ;
    private boolean         isInLeft    ;
    private boolean         isInRight   ;
    private boolean         moveToNextSlot;
    private int             indexPlayer ;
    private int             score       ;
    private Component       lastComponent;
    private Slot            lastSlot    ;

    public Player(String range) {
        super(range);
        isInLeft       =    true            ;
        isInRight      =    false           ;
        moveToNextSlot =    true            ;
        score          =    0               ;
        lastComponent  =    new FirstMove() ;
    }

    public void startGame(Circenses circenses) {

        this.circenses =            circenses          ;
        Slot firstSlot =    circenses.getRoute().get(0);
        indexPlayer    =                0              ;
        firstSlot.setLeftComponent(this);

    }

    public Player forward() throws ObstacleHitedException {

        if (lastComponent instanceof DeadPlayer)
            throw new ObstacleHitedException();

        if(moveToNextSlot)
        {
            if (isInLeft)
            {
                Component player = circenses.getRoute().get(indexPlayer).getLeftComponent();
                circenses.getRoute().get(indexPlayer).setLeftComponent(lastComponent);

                indexPlayer++;
                Component compenent = circenses.getRoute().get(indexPlayer).getLeftComponent();
                addScoreAndChangeLastCompenent(compenent , player);
                circenses.getRoute().get(indexPlayer).setLeftComponent(player);
            }
            else if(isInRight)
            {
                Component player = circenses.getRoute().get(indexPlayer).getRightComponent();
                circenses.getRoute().get(indexPlayer).setRightComponent(lastComponent);

                indexPlayer++;
                Component compenent = circenses.getRoute().get(indexPlayer).getRightComponent();
                addScoreAndChangeLastCompenent(compenent , player);
                circenses.getRoute().get(indexPlayer).setRightComponent(player);
            }
        }else
        {
            if (isInLeft)
            {
                Component player = circenses.getRoute().get(indexPlayer).getRightComponent();
                circenses.getRoute().get(indexPlayer).setRightComponent(lastComponent);

                Component compenent = circenses.getRoute().get(indexPlayer).getLeftComponent();
                addScoreAndChangeLastCompenent(compenent , player);
                circenses.getRoute().get(indexPlayer).setLeftComponent(player);
            }
            else if(isInRight)
            {
                Component player = circenses.getRoute().get(indexPlayer).getLeftComponent();
                circenses.getRoute().get(indexPlayer).setLeftComponent(lastComponent);

                Component compenent = circenses.getRoute().get(indexPlayer).getRightComponent();
                addScoreAndChangeLastCompenent(compenent , player);
                circenses.getRoute().get(indexPlayer).setRightComponent(player);
            }
            moveToNextSlot = true ;
        }

        return this;
    }

    public void addScoreAndChangeLastCompenent(Component component , Component player){

        Component compenent = component;

        if (compenent instanceof Obstacle){
            if (player instanceof Charioteer) {
                score -= 5;
                player.range = "D";
                lastComponent = new DeadPlayer();
            }else if (player instanceof Knight) {
                score -= 10;
                lastComponent = component;
            }
        }
        else if (compenent instanceof FinalLine) {
            score += 100;
        }
        else if (compenent instanceof Coin) {
            if (player instanceof Charioteer) {
                score += 10;
            }else if (player instanceof Knight) {
                score += 20;
            }
            lastComponent = new EarnedCoin();
        }
        else lastComponent = new EmptySlot();
    }

    public int score() {
        return score;
    }

    public Player right() throws ObstacleHitedException {
        isInRight       = true  ;
        isInLeft        = false ;
        moveToNextSlot  = false ;
        forward();
        return this;
    }

    public Player left() throws ObstacleHitedException {
        isInRight       = false ;
        isInLeft        = true  ;
        moveToNextSlot  = false ;
        forward();
        return this;
    }
}
