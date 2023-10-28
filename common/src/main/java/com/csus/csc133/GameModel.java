/*
GameModel is an important class to store all game data. It will store the width and height variables of the world.
In A1, you can hardcode the game world size to 1024 x 768 (w x h) area. The Game also contains a vector of multiple instances
of GameObject and provides a set of methods for manipulating game data. It also has a gametime variable to store the
current gaming time
 */

package com.csus.csc133;

import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

import java.util.Observable;
import java.util.Vector;
import java.util.Random;


public class GameModel extends Observable implements ActionListener {

    private double width, height;
    private double gameTime = 0;
    private String latestMessage = "The game has started";
    double elapsedTime = 20;
    double conversion = elapsedTime/1000;


    //int frameCount = 0;

    private Vector<GameObject> objects = new Vector<GameObject>(); // vector to hold all the objects
    GameObjectCollection currentCollection = new GameObjectCollection(); // new way to hold objects (as collection)

    // Create objects
    StudentPlayer player = new StudentPlayer();
    StudentNonstop nonstop = new StudentNonstop();
    StudentConfused confused = new StudentConfused();
    StudentAngry angry = new StudentAngry();
    StudentBiking biking = new StudentBiking();
    StudentCar car = new StudentCar();
    StudentFriendly friendly = new StudentFriendly();
    StudentHappy happy = new StudentHappy();
    StudentRunning running = new StudentRunning();
    LectureHall hall = new LectureHall();
    Restroom bathroom = new Restroom();
    WaterDispenser dispenser = new WaterDispenser();

    Random rand = new Random();

    // Constructor
    GameModel() {
        init();
        currentCollection = new GameObjectCollection();

        this.setChanged();
        this.notifyObservers();
    }

    // Player movements
    void movePlayer() {
        player.setSpeed(Student.DEFAULT_SPEED);
        player.move();
        this.setChanged();
        this.notifyObservers();
        setLatestMessage("StudentPlayer has started moving.");

        IIterator theElements = currentCollection.getIterator();
        while (theElements.hasNext())
        {
            GameObject obj = (GameObject) theElements.getNext();
            System.out.println(obj);
        }
    }
    void stopPlayer() {
        player.stopMoving();
        setLatestMessage("StudentPlayer has stopped moving.");
        this.setChanged();
        this.notifyObservers();
    }
    void goLeft() {
        player.turnLeft();
        setLatestMessage("StudentPlayer turned left.");
        this.setChanged();
        this.notifyObservers();
    }
    void goRight() {
        player.turnRight();
        setLatestMessage("StudentPlayer turned right.");
        this.setChanged();
        this.notifyObservers();
    }

    // Collision handling
    void hallCollision() {
        hall.handleCollide(player);
        setLatestMessage("StudentPlayer collided with LectureHall.");
        this.setChanged();
        this.notifyObservers();
    }
    void restroomCollision() {
        bathroom.handleCollide(player);
        setLatestMessage("StudentPlayer collided with restroom.");
        this.setChanged();
        this.notifyObservers();
    }
    void waterCollision() {
        dispenser.handleCollide(player);
        setLatestMessage("StudentPlayer collided with WaterDispenser.");
        this.setChanged();
        this.notifyObservers();
    }
    void studentCollision() {
        int i = rand.nextInt(objects.size());

        if ((objects.get(i) instanceof Student) && !(objects.get(i) instanceof StudentPlayer))
        {
            objects.get(i).handleCollide(player);
        }
        else
        {
            studentCollision();
        }
        setLatestMessage("StudentPlayer collided with another Student.");
        this.setChanged();
        this.notifyObservers();
    }
    void nextFrame(){

        // Determine if there are any collisions between objects
        // Use square AABB to detect collision
        //      length of AABB must be the size field of the GameObject
        //      AABB must be invisible by default

        gameTime = gameTime + conversion;//( 1 * conversion); // increase gameTime by 1

        if (hall.getLecture().getTime() == 0) // If there is no Lecture holding
        {
            if (rand.nextInt(10) == 0) // 10% chance of generating a new Lecture and assigning to the LectureHall
            {
                Lecture nextLecture = new Lecture();
                hall.setLecture(nextLecture);
            }
        }
        else // If there is a Lecture holding
        {
            hall.getLecture().setTime( hall.getLecture().getTime() - 1 * conversion); // Decrease Lecture time
            if (hall.getLecture().getTime() <= 0) // Check if the time is now <= 0 (end of lecture)
            {
                player.setAbsenceTime( player.getAbsenceTime() + 1 * conversion); // Increase absence count
            }
        }


        // check for collision
        for(GameObject obj: objects)
        {
            obj.move();
        }

        for(int i = 0; i < objects.size(); ++i)
        {
            for(int j = i + 1; j < objects.size(); ++j)
            {
                if((objects.elementAt(i).collidesWith(objects.elementAt(j))) && (objects.elementAt(i).getCooldown() <= 0) && (objects.elementAt(j).getCooldown() <= 0))
                {
                    //objects.elementAt(i).handleCollision(objects.elementAt(j));
                    System.out.println("collision detected");
                }

                if(objects.elementAt(i).getCooldown() > 0)
                {
                    //objects.elementAt(i).setCooldown(objects.elementAt(i).getCooldown() - 1*0.02);
                    System.out.println("reducing cooldown of objectA by 1");
                }

            }
        }





        // Conditions for the game to be over
        if ((player.getAbsenceTime() >= 5) || (player.getWaterIntake() >= 20) || (player.getHydration() <= 0))
        {
            setLatestMessage("Gameover. Time: " + gameTime);
        }
        setLatestMessage("Next Frame...");
        this.setChanged();
        this.notifyObservers();
    }

    // new getters for A2
    String getLatestMessage() {
        return this.latestMessage;
    }
    GameObjectCollection getCollection() {
        return currentCollection;
    }
    double returnGameTime() {
        return gameTime;
    }
    double returnHydration() {
        return player.getHydration();
    }
    String returnHallName() {
        return hall.getHallName();
    }
    public double returnWaterIntake(){
        return player.getWaterIntake();
    }
    double getWidth() {
        return this.width;
    }
    double getHeight() {
        return this.height;
    }

    Vector<GameObject> getObjects(){
        return objects;
    }


    // size setters
    void setWidth(double w) {
        width = w;
    }
    void setHeight  (double h) {
        height = h;
    }

    // set the latest message
    void setLatestMessage(String s) {
        //System.out.println("latest message reached===========================");
        this.latestMessage = s;
        this.setChanged();
        this.notifyObservers();
    }

    // Print the status of each object using their toString method
    void printOutput(){
        System.out.println("Time: " + gameTime  + " ==================================================="); // Header
        IIterator theElements = currentCollection.getIterator();
        while (theElements.hasNext())
        {
            GameObject obj = (GameObject) theElements.getNext();
            //System.out.println(obj); //commenting out for debugging
        }
    }

    void invalidInput(){
        System.out.println("\n Undefined or illegal input. Please enter a valid input. \n");
    }

    public void init() {
        // Set the size of the map
        gameTime = 0;
        //width = Display.getInstance().getDisplayWidth();
        //height = Display.getInstance().getDisplayHeight();

        //IIterator theElements = currentCollection.getIterator();
        currentCollection.add(player);
        currentCollection.add(nonstop);
        currentCollection.add(confused);
        currentCollection.add(angry);
        currentCollection.add(biking);
        currentCollection.add(car);
        currentCollection.add(friendly);
        currentCollection.add(happy);
        currentCollection.add(running);
        currentCollection.add(hall);
        currentCollection.add(bathroom);
        currentCollection.add(dispenser);

        objects.add(player);
        objects.add(nonstop);
        objects.add(confused);
        objects.add(angry);
        objects.add(biking);
        objects.add(car);
        objects.add(friendly);
        objects.add(happy);
        objects.add(running);
        objects.add(hall);
        objects.add(bathroom);
        objects.add(dispenser);

        IIterator theElements = currentCollection.getIterator();
        while (theElements.hasNext())
        {
            GameObject obj = (GameObject) theElements.getNext();
            obj.setMax(this.getWidth(), this.getHeight());
            obj.translate((float) (this.getWidth()/2), (float) this.getHeight()/2);
            obj.rotate(45);
            obj.scale(1, 2);
        }



        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}